package com.deloitte.nextgen.ha.create;


import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;

import com.deloitte.nextgen.framework.commons.exceptions.ResourceNotFoundException;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.common.repository.*;
import com.deloitte.nextgen.ha.create.dto.*;
import com.deloitte.nextgen.ha.create.mappers.*;
import com.deloitte.nextgen.ha.entity.*;

import com.deloitte.nextgen.ha.individuals.service.IndividualService;
import com.deloitte.nextgen.ha.common.repository.AmAppealInfoRepository;
import com.deloitte.nextgen.ha.search.CaseSearchService;

import com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigInteger;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class CreateAppealService {

    private final AmAddressMapping amAddressMapping;
    private final AmRequestDetailsMapping amRequestDetailsMapping;
    private final CreateAppealDtoMapper createAppealDtoMapper;
    private final AmRequestProgDetailsMapping amRequestProgDetailsMapping;
    private final AmRepresentativeDetailsMapping amRepresentativeDetailsMapping;
    private final FilingCreateAppealResponseDtoMapper filingCreateAppealResponseDtoMapper;

    private final AmRequestDetailsRepository amRequestDetailsRepository;
    private final AmAddressRepository amAddressRepository;
    private final AmAplStatLogRepository amAplStatLogRepository;
    private final AmAppealInfoRepository amAppealInfoRepository;
    private final AmAplGrpRepository amAplGrpRepository;
    private final AmRequestProgDetailsRepository amRequestProgDetailsRepository;

    private final AmAplAssociationRepository amAplAssociationRepository;
    private final AmRepresentativeDetailsRepository amRepresentativeDetailsRepository;
    private final AmAppealNotesRepository amAppealNotesRepository;
    private final AmAppealNotesAmendRepository amAppealNotesAmendRepository;

    private final IndividualService individualService;
    private final CaseSearchService caseSearchService;


    public static final String APL_STATUS  = "FIP";

    public CreateAppealResonseDto createAppeal(FilingCreateAppealDto filingCreateAppealDto){

       BigInteger primaryIndvId = filingCreateAppealDto.getPrimaryAppellant();
       List<BigInteger> secondaryIndvIds =filingCreateAppealDto.getSecondaryAppellants();
       BigInteger appealNum = filingCreateAppealDto.getPrimaryAppealNum();
       BigInteger caseNum = filingCreateAppealDto.getCaseNum();
       AuthRepDto authRepDetails = new AuthRepDto();

        if(appealNum!= null && appealNum.longValue()>1){
           updateExistingCreatedAppeal(appealNum,filingCreateAppealDto);
        }else{
            // get the group number
            List<Object[]> groupList = amAplGrpRepository.findByNextGroupSeq();
            Object[] objItem = (Object[]) groupList.get(0);
            BigInteger groupNum = new BigInteger(String.valueOf(objItem[0]));
            AmRequestDetails amRequestDetails = individualProcessing(filingCreateAppealDto,primaryIndvId,groupNum,'Y');
            secondaryIndvIds.forEach(secondaryIndvId -> individualProcessing(filingCreateAppealDto,secondaryIndvId,groupNum,'N'));
            appealNum = amRequestDetails.getAplNum();
        }
        if(caseNum!=null && caseNum.longValue()>0){
            //get the AuthRep details if there is case number
            CaseSearchResponseDto caseSearchResponse = caseSearchService.getCaseDetails(caseNum.longValue());
            if(null != caseSearchResponse && null != caseSearchResponse.getAuthRep()){
                authRepDetails =caseSearchResponse.getAuthRep();
            }
        }
       return createAppealDtoMapper.map(appealNum,authRepDetails);
    }

    public void createAuthRepData(FilingUpdateAppealDto filingUpdateAppealDto){

        BigInteger appealNum = filingUpdateAppealDto.getAppealNum();
        //AmRequestProgDetails //
        List<AmRequestProgDetails> amRequestProgDetailsdb = amRequestProgDetailsRepository.findByAplNum(appealNum);
        AmRequestProgDetails  amRequestProgDetailsUI = amRequestProgDetailsMapping.getEntityFromDto(filingUpdateAppealDto);
        if(!amRequestProgDetailsdb.isEmpty()){
            //update amAddress
            AmRequestProgDetails amRequestProgDetailsUpdate = amRequestProgDetailsdb.get(0);
            amRequestProgDetailsMapping.mergeEntity(filingUpdateAppealDto,amRequestProgDetailsUpdate);
            amRequestProgDetailsRepository.save(amRequestProgDetailsUpdate);
        }else{
            amRequestProgDetailsRepository.save(amRequestProgDetailsUI);
        }

        Boolean caseAuthRepFlag = filingUpdateAppealDto.getCaseAuthRep();
        AmAppealInfo amAppealInfo = amAppealInfoRepository.findByAplNum(appealNum);
        //Updating the case level in am_appeal_info table in new column CASE_AUTHREP_INCLUDED.
        if(amAppealInfo == null){
            throw new IllegalStateException("There is no address details number with " + appealNum);
        }else{
           if(caseAuthRepFlag){
                //TODO update the appeal_info table
                amAppealInfo.setCaseAuthrepIncluded('Y');
            }else{
                amAppealInfo.setCaseAuthrepIncluded('N');
            }
            amAppealInfoRepository.save(amAppealInfo);
        }

        List<AuthRepDto> authRepList = filingUpdateAppealDto.getAssociatedAuthReps();
        List<AmRepresentativeDetailsDto> amRepresentativeDetailsdb = getRepresentativeDetailsDtos(appealNum);
        //comparing with existing db records
        if(null != amRepresentativeDetailsdb && !amRepresentativeDetailsdb.isEmpty() && null!= authRepList && !authRepList.isEmpty()) {

            final List<Long> authRepIdsFromDataBase = amRepresentativeDetailsdb
                    .stream()
                    .map(AmRepresentativeDetailsDto::getAuthRepId)
                    .collect(Collectors.toList());

            List<Long> authRepIdsUIList = authRepList.stream().map(AuthRepDto::getAuthRepId).collect(Collectors.toList());

            //delete authrep:
            List<Long> deleteAuthRep = authRepIdsFromDataBase.stream()
                    .filter(element -> !authRepIdsUIList.contains(element))
                    .collect(Collectors.toList());

            if (!deleteAuthRep.isEmpty()) {
                deleteAuthRep.forEach(item -> deleteAuthRepRecords(item)); //TODO delete from record table
                for (AuthRepDto authRepDto : authRepList) {
                    if (!deleteAuthRep.contains(authRepDto.getAuthRepId())) {
                         setAndSaveAmRepresentativeDetailsRecords(authRepDto,appealNum);
                    }
                }
            }else{

                authRepList.forEach(item -> setAndSaveAmRepresentativeDetailsRecords(item,appealNum));

            }

            //treat as new records
        }else{
                if(null!= authRepList && authRepList.size()>0){
                    authRepList.forEach(item -> setAndSaveAmRepresentativeDetailsRecords(item,appealNum));
                }
        }

      //  saveAppealNotes();
        String appealNotes = filingUpdateAppealDto.getFillingNotes().trim();
        if(appealNotes!= null && appealNotes.isEmpty()){
            saveAppealNotesDetails(appealNum, appealNotes);
        }


    }


    /**
     *
     */
    private void saveAppealNotesDetails(BigInteger appealNum, String appealNotes) {
        if(null!= appealNotes && !appealNotes.isEmpty()){
            LocalDate currentDate = LocalDate.now();
            List<AmAppealNotes> amAppealNotesExisting =  amAppealNotesRepository.findByAplNum(appealNum); //amAppealInfoRepository.findByPageId(appealNum,"HA");
            if(null!= amAppealNotesExisting && !amAppealNotesExisting.isEmpty()){
                AmAppealNotesAmend amAppealAmendNotes = new AmAppealNotesAmend();
                amAppealAmendNotes.setAplNum(appealNum);
                amAppealAmendNotes.setAplNotesSeqNum(amAppealNotesExisting.get(0).getAplNotesSeqNum());
                amAppealAmendNotes.setNotesTxt(appealNotes.trim());
                amAppealAmendNotes.setAmendUserId("HA");
                amAppealAmendNotes.setAmendDt(currentDate);
                amAppealNotesAmendRepository.save(amAppealAmendNotes);
            }else{
                AmAppealNotes amAppealNotes = new AmAppealNotes();
                amAppealNotes.setAplNum(appealNum);
                amAppealNotes.setCategoryCd("HA"); //current system will based on pageId
                amAppealNotes.setDescriptionCd("Filing Create appeal");
                amAppealNotes.setNotesTxt(appealNotes.trim()); //currently it using textdocument
                amAppealNotes.setPageId("HA");
                amAppealNotes.setNotesTxt(appealNotes.trim());
                amAppealNotesRepository.save(amAppealNotes);
            }
        }
    }

    public void deleteAuthRepRecords(Long authRepId){
        try {
            log.info("Delete Auth Representative data");
            Optional<AmRepresentativeDetails> amRepresentativeDetails = amRepresentativeDetailsRepository.findById(BigInteger.valueOf(authRepId));
            Optional<AmAplAssociation> amAplAssociation = amAplAssociationRepository.findById(BigInteger.valueOf(authRepId));//TODO change the query to find records
            if (amRepresentativeDetails.isPresent()){
                amRepresentativeDetailsRepository.delete(amRepresentativeDetails.get());
            }else{
                throw new ResourceNotFoundException(404, "AmRepresentativeDetails", amRepresentativeDetails.toString(), null);
            }
            if (amAplAssociation.isPresent()) {amAplAssociationRepository.delete(amAplAssociation.get());}
            else{throw new ResourceNotFoundException(404, "amAplAssociation", amAplAssociation.toString(), null);}

        }catch (Exception e)
        {
            log.error("Error inside deleteAuthRepData ", e);
        }

    }


    public FilingCreateAppealResponseDto retrieveAppeal(BigInteger appealNum){


        Optional<AmRequestDetails> amRequestDetails = amRequestDetailsRepository.findById(appealNum);
        amRequestDetails.orElseThrow(() -> new IllegalStateException("There is no appeal number with " + appealNum));
        Optional<AmAddress> amAddressData=  amAddressRepository.findById(amRequestDetails.get().getAddressId());
        amAddressData.orElseThrow(() -> new IllegalStateException("There is no address details number with " + appealNum));

        // get the group number based on appeal number selected
        BigInteger groupNum = findGroupNumberWithApplNum(appealNum);
        // Get the List of individual associated with group number
        List<AmAplGrpDto> amAplGrpDto = findActiveByGroup(String.valueOf(groupNum));
        String caseNumStr = amRequestDetails.get().getNonIesCaseNum();
        Long caseNum = 0L;
        if(caseNumStr!= null){
            caseNum = Long.valueOf(caseNumStr);
        }

        //List of appeal Number based on group number.
        final List<BigInteger> listOfAplNum = amAplGrpDto
                .stream()
                .map(AmAplGrpDto::getAplNum)
                .collect(Collectors.toList());

        final List<AmRequestDetails> amRequestDetailAplNums = amRequestDetailsRepository.findByAplNumIn(listOfAplNum);

        final List<BigInteger> listOfIndvs = amRequestDetailAplNums
                .stream()
                .map(AmRequestDetails::getIndvId)
                .collect(Collectors.toList());

        //TODO get the individual details based on case number of individual ids
        Map<Long, IndividualDto> individualDetails = getIndividualDetails(caseNum,listOfIndvs);


        Map<BigInteger, Character> primaryAplNum = amAplGrpDto
                .stream()
                .filter(e -> "Y".equalsIgnoreCase(String.valueOf(e.getPrimarySw())))
                .collect(Collectors.toMap(AmAplGrpDto::getAplNum, AmAplGrpDto::getPrimarySw));
        List<AmRequestDetails> primaryAmRequestDetails = amRequestDetailAplNums
                .stream()
                .filter(item -> "Y".equalsIgnoreCase(String.valueOf(primaryAplNum.get(item.getAplNum()))))
                .collect(Collectors.toList());

        Map<BigInteger, Character> secondaryAplNums = amAplGrpDto
                .stream()
                .filter(e -> "N".equalsIgnoreCase(String.valueOf(e.getPrimarySw())))
                .collect(Collectors.toMap(AmAplGrpDto::getAplNum, AmAplGrpDto::getPrimarySw));
        List<AmRequestDetails> secondaryAmRequestDetails = amRequestDetailAplNums
                .stream().filter(item -> "N".equalsIgnoreCase(String.valueOf(secondaryAplNums.get(item.getAplNum()))))
                .collect(Collectors.toList());

        BigInteger primaryIndv = primaryAmRequestDetails.get(0).getIndvId();
        List<BigInteger> secondaryIndv = secondaryAmRequestDetails.stream().map(AmRequestDetails::getIndvId).collect(Collectors.toList());



        IndividualDto primaryIndvDetails = individualDetails.get(primaryIndv.longValue());

        List<IndividualDto> secondaryIndvDetails = individualDetails.values().stream().filter(e -> secondaryIndv.contains(BigInteger.valueOf(e.getIndividualId())))
                                                                                      .collect(Collectors.toList());

        List<IndividualDto> unSelectedIndvDetails = individualDetails.values().stream().filter(e -> (!secondaryIndv.contains(BigInteger.valueOf(e.getIndividualId()))
                                                                                                     && !primaryIndv.equals(BigInteger.valueOf(e.getIndividualId())) )  )
                                                                                    .collect(Collectors.toList());


       /* Optional<IndividualDto> primaryIndvDetails = individualDetails.stream().filter(item -> BigInteger.valueOf(item.getIndividualId()).equals(primaryIndv)).findFirst();


       List<IndividualDto>  secondaryIndvDetails =  individualDetails.stream().filter(item ->
                                                     (secondaryIndv.stream().filter(secondItem -> secondItem.equals(BigInteger.valueOf(item.getIndividualId())))).count()<1)
                                                      .collect(Collectors.toList());

        List<IndividualDto>  unSelectedIndvDetails = individualDetails.stream().filter(item ->(listOfIndvs.stream().filter(secondItem->
                                                                   !secondItem.equals(BigInteger.valueOf(item.getIndividualId())))).count()<1).collect(Collectors.toList());*/

        AddressDto addressDto = amAddressMapping.entityToDto(amAddressData.get());

        return filingCreateAppealResponseDtoMapper.map(amRequestDetails.get(),addressDto,primaryIndvDetails,secondaryIndvDetails,
                                                      unSelectedIndvDetails);
    }




    public FilingCreateAppealResponseDto retrieveAuthRepInfo(BigInteger appealNum){

        List<AmRequestProgDetails> amRequestProgDetails = amRequestProgDetailsRepository.findByAplNum(appealNum);
        Optional<AmRequestDetails> amRequestDetails = amRequestDetailsRepository.findById(appealNum);

        /*AmRepresentativeDetails*/
       // List<AmRepresentativeDetailsDto> amRepresentativeDetailsDto = null;
        List<AmRepresentativeDetailsDto> amRepresentativeDetailsDto = getRepresentativeDetailsDtos(appealNum);
        // return amRepresentativeDetailsDto;

        List<AmAddress> amRepresentativeAddressList = new ArrayList<>();
        if(null != amRepresentativeDetailsDto && amRepresentativeDetailsDto.size()>0 ){
            amRepresentativeDetailsDto.forEach(item -> amRepresentativeAddressList.add( amAddressRepository.findById(item.getAddressId()).get() ));
        }
        AmAppealInfo amAppealInfo = amAppealInfoRepository.findByAplNum(appealNum);
        AuthRepDto authRepDetails = new AuthRepDto();
        Boolean caseAuthRep =false ;

        if( null != amAppealInfo.getCaseAuthrepIncluded() && "Y".equals(String.valueOf(amAppealInfo.getCaseAuthrepIncluded()))){
            String caseNumStr = amRequestDetails.get().getNonIesCaseNum();
            Long caseNum = Long.valueOf(caseNumStr);
            caseAuthRep = true;
            CaseSearchResponseDto caseSearchResponse = caseSearchService.getCaseDetails(caseNum);
            if(null != caseSearchResponse && null != caseSearchResponse.getAuthRep()){
                authRepDetails =caseSearchResponse.getAuthRep();
            }
        }

        return filingCreateAppealResponseDtoMapper.mapAuthRep(amRequestProgDetails.isEmpty()? null: amRequestProgDetails.get(0),
                                              caseAuthRep,authRepDetails,amRepresentativeDetailsDto,amRepresentativeAddressList);
    }

    private List<AmRepresentativeDetailsDto> getRepresentativeDetailsDtos(BigInteger appealNum) {
        List<Object[]>  amRepresentativeDetails = amRepresentativeDetailsRepository.findByAplNumAssociation(String.valueOf(appealNum));
        List<AmRepresentativeDetailsDto> amRepresentativeDetailsDto = null;
        if(null!= amRepresentativeDetails && amRepresentativeDetails.size()>0) {

            amRepresentativeDetailsDto = amRepresentativeDetails.stream().map(i -> {
                AmRepresentativeDetailsDto dto = new AmRepresentativeDetailsDto();
                dto.setFirstName((String.valueOf(i[0])));
                dto.setLastName((String.valueOf(i[1])));
                dto.setMidName((String.valueOf(i[2])));
                dto.setSuffixName((String.valueOf(i[3])));
                dto.setAddressId(new BigInteger(String.valueOf(i[4])));
                dto.setPhoneNum1((String.valueOf(i[5])));
                dto.setPhoneNum2((String.valueOf(i[6])));
                dto.setPhoneExt((String.valueOf(i[7])));
                dto.setEmail((String.valueOf(i[8])));
                dto.setAuthRepId(Long.valueOf(String.valueOf(i[9])));
                return dto;
            }).collect(Collectors.toList());
            return amRepresentativeDetailsDto;
        }

        return null;
    }

    private Map<Long, IndividualDto> getIndividualDetails( Long caseNum,List<BigInteger> listOfIndvs) {
        Map<Long, IndividualDto> individualDtoMap = new HashMap<>();
        if(null != caseNum && caseNum >0 && listOfIndvs!= null && !listOfIndvs.isEmpty()){
            CaseSearchResponseDto caseSearchResponse = caseSearchService.getCaseDetails(caseNum);
            individualDtoMap.put(caseSearchResponse.getHeadOfHouseHold().getIndividualId(),caseSearchResponse.getHeadOfHouseHold());
            List<IndividualDto> members = caseSearchResponse.getMembers();
            if(!members.isEmpty()){
                for (IndividualDto member : members){
                    individualDtoMap.put(member.getIndividualId(),member);
                }
            }
        }else{
            // code for individual list
            Set<Long> indvSet = new HashSet<>();
          for (BigInteger bigInteger : listOfIndvs) { indvSet.add(bigInteger.longValue());}
              individualDtoMap = individualService.getIndividuals(indvSet);
        }

        return individualDtoMap;
    }


    private void setAndSaveAmRepresentativeDetailsRecords(AuthRepDto authRepDto,BigInteger appealNum) {

       Optional<AmRepresentativeDetails> amRepresentativeDetailsData =  amRepresentativeDetailsRepository.findById(BigInteger.valueOf(authRepDto.getAuthRepId()));
        //update amAddress
        AmAddress amAddressDb = null;
        if(amRepresentativeDetailsData.isPresent()){

                  List<AmAddress> amAddressesDb = amAddressRepository.findByAddressId(amRepresentativeDetailsData.get().getAddressId());
                  if( null != amAddressesDb && !amAddressesDb.isEmpty()){
                      AddressDto  amAddressUI =  authRepDto.getAddress();
                      AmAddress amAddressToUpdate = amAddressesDb.get(0);
                      amAddressMapping.mergeEntity(amAddressUI,amAddressToUpdate);
                      amAddressRepository.save(amAddressToUpdate);
                  }

            AmRepresentativeDetails amRepresentativeDetailsUpdate = amRepresentativeDetailsData.get();
            amRepresentativeDetailsMapping.mergeEntity(authRepDto,authRepDto.getName(),amRepresentativeDetailsUpdate);
            amRepresentativeDetailsRepository.save(amRepresentativeDetailsUpdate);

        }else{

            AmAddress amAddressEntity = amAddressMapping.mapDtoToEntity(authRepDto.getAddress());
            amAddressDb = amAddressRepository.save(amAddressEntity);

            AmRepresentativeDetails  amRepresentativeDetailsUI = amRepresentativeDetailsMapping.getEntityFromDto(authRepDto,authRepDto.getName());
            amRepresentativeDetailsUI.setAddressId(amAddressDb.getAddressId());
            AmRepresentativeDetails amRepresentativeDetailsDb = amRepresentativeDetailsRepository.save(amRepresentativeDetailsUI);

            AmAplAssociation amAplAssociationEntity= new AmAplAssociation();
            amAplAssociationEntity.setAplNum(appealNum);
            amAplAssociationEntity.setClientRepId(amRepresentativeDetailsDb.getClientRepId());
            amAplAssociationRepository.save(amAplAssociationEntity);
        }
    }


    private AmRequestDetails individualProcessing(FilingCreateAppealDto filingCreateAppealDto,BigInteger indvId,BigInteger groupNum,char primarySwitch) {

        ContactInfoDto contactInfoDto = filingCreateAppealDto.getContactInfo();
        AmAddress  amAddress =  amAddressMapping.mapDtoToEntity(contactInfoDto.getAddress());
        AmAddress amAddressData = amAddressRepository.save(amAddress);

        AmRequestDetails amRequestDtEntity = amRequestDetailsMapping.mapDtoToEntity(filingCreateAppealDto,contactInfoDto);
        AmRequestDetails amRequestDetailsData = saveAmRequestDetails(amRequestDtEntity,indvId,amAddressData);

        //crete AmAplGroup
        saveAplGroupIndv(amRequestDetailsData, groupNum, primarySwitch);
        // Update Appeal Status Log & Appeal Info
        updateStatusInfo(amRequestDetailsData);

        return amRequestDetailsData;
    }


    private void individualUpdateProcessing(FilingCreateAppealDto fillingCreateAppealDto,BigInteger appealNum,BigInteger groupNum) {

        List<AmRequestDetails> amRequestDetails = amRequestDetailsRepository.findByAppealNum(appealNum);
        List<AmAddress> amAddressesDb = amAddressRepository.findByAddressId(amRequestDetails.get(0).getAddressId());

        //update amAddress
        AddressDto  amAddressUI =  fillingCreateAppealDto.getContactInfo().getAddress();
        AmAddress amAddressToUpdate = amAddressesDb.get(0);
        amAddressMapping.mergeEntity(amAddressUI,amAddressToUpdate);
        amAddressRepository.save(amAddressToUpdate);

        //update amrequestdetails
         AmRequestDetails amRequestDetailsUpdate = amRequestDetails.get(0);
        amRequestDetailsMapping.mergeEntity(fillingCreateAppealDto,fillingCreateAppealDto.getContactInfo(),amRequestDetailsUpdate);
        AmRequestDetails amRequestDetailsData = amRequestDetailsRepository.save(amRequestDetailsUpdate);



        //update amAplGroup table.
        //updating the existing inactive to active and active to inactive
        List<AmAplGrp> amAplGrpNonActive = amAplGrpRepository.findByAplNumInActive(String.valueOf(appealNum),String.valueOf(groupNum));
        if(!amAplGrpNonActive.isEmpty()){
            AmAplGrp amAplGrpNonActiveToUpdate = amAplGrpNonActive.get(0);
            amAplGrpNonActiveToUpdate.setActiveInGroupSw('Y');
            amAplGrpRepository.save(amAplGrpNonActiveToUpdate);
        }

        // List<AmAplGrp> amAplGrpActive = amAplGrpRepository.findByAplNumActiveDel(String.valueOf(groupNum),String.valueOf(amRequestDetailsData.getAplNum()));

        List<AmAplGrp> amAplGrpCreate = amAplGrpRepository.findByAplNum(appealNum);
        if(!amAplGrpCreate.isEmpty() & amAplGrpCreate.size()>1){
            for(AmAplGrp item:amAplGrpCreate){
                if(item.getAplGroupNum().compareTo(groupNum)!= 0  && item.getActiveInGroupSw() == 'Y' ){
                    AmAplGrp amAplGrpToUpdate = item;
                    amAplGrpToUpdate.setActiveInGroupSw('N');
                    amAplGrpRepository.save(amAplGrpToUpdate);

                }
            }

        }

        // Update Appeal Status Log & Appeal Info
        //TODO should i update the statusinfo table while updates
        //  updateStatusInfo(amRequestDetailsData);


    }



    private void updateExistingCreatedAppeal(BigInteger appealNum, FilingCreateAppealDto filingCreateAppealDto) {

       // TODO make some common method
        BigInteger groupNum = findGroupNumberWithApplNum(appealNum);
        List<AmAplGrpDto> amAplGrpDto = findActiveByGroup(String.valueOf(groupNum));
        final List<BigInteger> listOfAplNum = amAplGrpDto
                .stream()
                .map(AmAplGrpDto::getAplNum)
                .collect(Collectors.toList());
        final List<AmRequestDetails> amRequestDetailAplNums = amRequestDetailsRepository.findByAplNumIn(listOfAplNum);
        final List<BigInteger> indvsFromDataBase = amRequestDetailAplNums
                .stream()
                .map(AmRequestDetails::getIndvId)
                .collect(Collectors.toList());

        final Map<BigInteger,BigInteger> indvsWithAppealNums = amRequestDetailAplNums.stream().collect(Collectors.toMap(AmRequestDetails::getIndvId,AmRequestDetails::getAplNum));
        final Map<BigInteger,AmRequestDetails> indvsWithAmRequestDetails = amRequestDetailAplNums.stream().collect(Collectors.toMap(AmRequestDetails::getIndvId,e->e));

        BigInteger updatedPrimaryIndv = filingCreateAppealDto.getPrimaryAppellant();
        //process first primary individual:
        individualUpdateProcessing(filingCreateAppealDto,appealNum,groupNum);

        //process secondary indvs
        List<BigInteger>  updatedSecondaryIndvs = filingCreateAppealDto.getSecondaryAppellants();

        if(null!=updatedSecondaryIndvs && !updatedSecondaryIndvs.isEmpty()){

            //update indvs:
            List<BigInteger> updateIndvs = indvsFromDataBase.stream()
                    .filter(element -> updatedSecondaryIndvs.contains(element))
                    .collect(Collectors.toList());

           if( !updateIndvs.isEmpty()) {
               updateIndvs.forEach(item -> individualUpdateProcessing(filingCreateAppealDto,indvsWithAppealNums.get(item),groupNum));
           }

            //create  or insert indvs:
            List<BigInteger> insertIndvs = updatedSecondaryIndvs.stream()
                    .filter(element -> !indvsFromDataBase.contains(element))
                    .collect(Collectors.toList());

            if( !insertIndvs.isEmpty()) {
                insertIndvs.forEach(item -> individualProcessing(filingCreateAppealDto,item,groupNum,'N'));
            }

            //delete indvs:
            List<BigInteger> deleteIndvs = indvsFromDataBase.stream()
                    .filter(element -> ( !updatedSecondaryIndvs.contains(element) && (element.compareTo(updatedPrimaryIndv) != 0 ) ) )
                    .collect(Collectors.toList());
            if( !deleteIndvs.isEmpty() ) {
                //  deleteIndvs.forEach(item -> deleteIndividual(item)); //TODO work on this

                // create a indiviudal in new
                for (BigInteger indv : deleteIndvs)
                {
                    //Create a new record in AmAplGroup with primary switch and ActiveGroupSw as
                    AmRequestDetails amRequestDtls = indvsWithAmRequestDetails.get(indv);
                    List<Object[]> groupList = amAplGrpRepository.findByNextGroupSeq();
                    Object[] objItem = (Object[]) groupList.get(0);
                    BigInteger newGroupNum = new BigInteger(String.valueOf(objItem[0]));
                    AmAplGrp amAplGroupEntity = new AmAplGrp();
                    amAplGroupEntity.setAplGroupNum(newGroupNum);
                    amAplGroupEntity.setAplNum(amRequestDtls.getAplNum());
                    amAplGroupEntity.setPrimarySw('Y');
                    amAplGroupEntity.setActiveInGroupSw('Y');
                    amAplGrpRepository.save(amAplGroupEntity);

                    List<AmAplGrp> existingAmAplGrp = amAplGrpRepository.findByAplNumActiveDel(String.valueOf(amRequestDtls.getAplNum()),String.valueOf(groupNum));
                    if (existingAmAplGrp!= null && existingAmAplGrp.get(0) != null){

                              AmAplGrp amAplGrpToUpdate = existingAmAplGrp.get(0);
                              amAplGrpToUpdate.setPrimarySw('N');
                              amAplGrpToUpdate.setActiveInGroupSw('N');
                              amAplGrpRepository.save(amAplGrpToUpdate);

                     }
                }
            }

        }
    }

    /**
     * Save record in AmRequestDetails table
     * @param amRequestDetails
     * @param indvId
     * @param amAddressData
     * @return
     */
    private AmRequestDetails saveAmRequestDetails(AmRequestDetails amRequestDetails, BigInteger indvId, AmAddress amAddressData) {

        AmRequestDetails amRequestDetailsData = null;
        amRequestDetails.setAddressId(amAddressData.getAddressId());
        amRequestDetails.setIndvId(indvId);
        amRequestDetailsData = amRequestDetailsRepository.save(amRequestDetails);
        return amRequestDetailsData;
    }


    /**
     * Save record in AmGroup table
     * @param amRequestDetailsData
     * @param aplGroupNum
     * @param primarySwitch
     *
     */
    private void saveAplGroupIndv(AmRequestDetails amRequestDetailsData, BigInteger aplGroupNum, char primarySwitch) {

        AmAplGrp amAplGroupEntity = new AmAplGrp();
        amAplGroupEntity.setAplGroupNum(aplGroupNum);
        amAplGroupEntity.setAplNum(amRequestDetailsData.getAplNum());
        amAplGroupEntity.setPrimarySw(primarySwitch);
        amAplGroupEntity.setActiveInGroupSw('Y');
        amAplGrpRepository.save(amAplGroupEntity);
    }


    private List<AmAplGrpDto> findActiveByGroup(String groupNum) {
        List<Object[]> amAplGrpsList = amAplGrpRepository.findActiveByGrpNum(String.valueOf(groupNum));
        List<AmAplGrpDto> amAplGrpDto = amAplGrpsList.stream().map(i -> {
                AmAplGrpDto dto = new AmAplGrpDto();
                dto.setAplGroupNum(new BigInteger(String.valueOf(i[0])));
                dto.setAplNum((new BigInteger(String.valueOf(i[1]))));
                dto.setPrimarySw((Character) i[2]);
                return dto;
            }).collect(Collectors.toList());
        return amAplGrpDto;
    }

    private void updateStatusInfo(AmRequestDetails amRequestItem) {
        AmAppealInfo aplInfo = amAppealInfoRepository.findByAplNum(amRequestItem.getAplNum());
        if(aplInfo != null) {
            if( !APL_STATUS.equals(aplInfo.getLastStatus())){
                insertStatus(amRequestItem.getAplNum(), APL_STATUS);
            }
        }else{
            insertStatus(amRequestItem.getAplNum(), APL_STATUS);
        }
    }

    public void insertStatus (BigInteger aplNum,String latestStatus){

        AmAplStatLog amAplStatLogdb =  null;  //amAplStatLogRepository.findByAplNumActive(aplNum.longValue());
        String comments ="" ;

        insertAmAplStatLogStatus(aplNum, latestStatus, comments);
        //TODO: need to check how to insert this procedure call
        insertAmAppealInfoStatus(aplNum,latestStatus);
    }

    public void insertAmAplStatLogStatus (BigInteger aplNum,String latestStatus, String comments){

        AmAplStatLog amAplStatLog = new AmAplStatLog();
        amAplStatLog.setAplNum(aplNum);
        amAplStatLog.setAplStatus(latestStatus);
        amAplStatLog.setStatusEffDt(LocalDate.now());
        amAplStatLog.setStatusCreateDt(LocalDate.now());
        amAplStatLog.setDeleteInd("N");
        amAplStatLog.setComments(comments);
        amAplStatLogRepository.save(amAplStatLog);

    }

    public void insertAmAppealInfoStatus (BigInteger aplNum,String latestStatus){

        AmAppealInfo amAplStatLog = new AmAppealInfo();
        amAplStatLog.setAplNum(aplNum);
        amAplStatLog.setLastStatus(latestStatus);
        amAppealInfoRepository.save(amAplStatLog);

    }


    private BigInteger findGroupNumberWithApplNum(BigInteger appealNum) {
        List<Object[]> amAplGrpList = amAplGrpRepository.findGrpNumByAppNum(appealNum);
        if(amAplGrpList.isEmpty()){
            throw new IllegalStateException("not active group number exits for this appeal number ");
        }
        Object[] objItem = (Object[]) amAplGrpList.get(0);
        return new BigInteger(String.valueOf(objItem[0]));

    }
}
