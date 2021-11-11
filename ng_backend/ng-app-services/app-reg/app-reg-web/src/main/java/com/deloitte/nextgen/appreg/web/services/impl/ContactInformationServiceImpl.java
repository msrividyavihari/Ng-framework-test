package com.deloitte.nextgen.appreg.web.services.impl;

import com.deloitte.nextgen.appreg.web.entities.*;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.response.AppContactDetailsResponse;
import com.deloitte.nextgen.appreg.web.mappings.*;
import com.deloitte.nextgen.appreg.web.repositories.*;
import com.deloitte.nextgen.appreg.web.services.ContactInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContactInformationServiceImpl implements ContactInformationService {

    private final ArPhnDetailsRepository arPhnDetailsRepository;
    private final ArEmailDetailsRepository arEmailDetailsRepository;
    private final ArApplicationForAidRepository arApplicationForAidRepository;
    private final ArAppAddrRepository arAppAddrRepository;
    private final ArEmailDetailsCustomRepository arEmailDetailsCustomRepository;
    private final ArPhnDetailsCustomRepository arPhnDetailsCustomRepository;
    private final DcCaseAddressesRepository dcCaseAddressesRepository;

    private final ArPhnDetailsMapping arPhnDetailsMapping;
    private final ArEmailDetailsMapping arEmailDetailsMapping;
    private final ArApplicationForAidMapping arApplicationForAidMapping;
    private final ArAppAddrMapping arAppAddrMapping;
    private final DcCaseAddressesCustomMapping dcCaseAddressesCustomMapping;

    @Override
    public ArApplicationForAidReqAndResp findAppDetails(String appNum) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndRespFinal =null;
        try
        {
            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            arApplicationForAidReqAndRespFinal =arApplicationForAidMapping.toDto(arApplicationForAid);
        }catch (Exception e)
        {
            log.error("Error inside findAppDetails ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arApplicationForAidReqAndRespFinal;
    }

    @Override
    public List<ArPhnDetails> findPhoneByAppNumAndPhoneSrcTyp(String appNum,String phoneSrcTyp) {
        List <ArPhnDetails> arPhnDetailsList =new ArrayList<>();
        try
        {
            arPhnDetailsList = arPhnDetailsRepository.findByAppNumAndPhoneSrcTyp(appNum,phoneSrcTyp);

        }catch (Exception e)
        {
            log.error("Error inside findPhoneByAppNumAndPhoneSrcTyp ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arPhnDetailsList;
    }

    @Override
    public List<ArEmailDetails> findEmailByAppNumAndEmailSrcTyp(String appNum,String emailSrcType) {
        List <ArEmailDetails> arEmailDetailsList =new ArrayList<>();
        try
        {
            arEmailDetailsList = arEmailDetailsRepository.findByAppNumAndEmailSrcTyp(appNum,emailSrcType);

        }catch (Exception e)
        {
            log.error("Error inside findEmailByAppNumAndEmailSrcTyp ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arEmailDetailsList;
    }

    @Override
    public ArAppAddr findAppAddrDetailsAndAddrTypeCd(String appNum, String addrTypeCd) {
        ArAppAddr arAppAddr = null;
        try
        {
            arAppAddr = arAppAddrRepository.findByAppNumAndAddrTypeCd(appNum,addrTypeCd);

        }catch (Exception e)
        {
            log.error("Error inside findAppAddrDetailsAndAddrTypeCd ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppAddr;
    }


    @Override
    public List<ArPhnDetailsDto> findPhoneByCaseNumAndPhoneSrcTyp(String appNum, Long caseNum, String phoneSrcTyp) {
        List<ArPhnDetailsDto> phnDetailsDtoList =new ArrayList<>();
        try
        {
            phnDetailsDtoList = arPhnDetailsCustomRepository.findPhnDetailsForCaseAndPhoneSrcTyp(caseNum,phoneSrcTyp);
            phnDetailsDtoList = phnDetailsDtoList.stream().map(i -> {i.setAppNum(appNum); return i;}).collect(Collectors.toList()) ;

        }catch (Exception e)
        {
            log.error("Error inside findPhoneByCaseNumAndPhoneSrcTyp ", e);
            throw new AppRegCustomException(e.toString());
        }
        return phnDetailsDtoList;
    }


    @Override
    public List<ArEmailDetailsDto> findEmailByCaseNumAndEmailSrcTyp(String appNum, Long caseNum, String emailSrcTyp) {
        List<ArEmailDetailsDto> emailDetailsDtoList =new ArrayList<>();
        try
        {
            emailDetailsDtoList = arEmailDetailsCustomRepository.findEmailDetailsForCaseAndEmailScrTyp(caseNum,emailSrcTyp);
            emailDetailsDtoList=emailDetailsDtoList.stream().map(i -> {i.setAppNum(appNum); return i;}).collect(Collectors.toList()) ;

        }catch (Exception e)
        {
            log.error("Error inside findEmailByCaseNumAndEmailSrcTyp ", e);
            throw new AppRegCustomException(e.toString());
        }
        return emailDetailsDtoList;
    }

    @Override
    public ArAppAddrDto findDcAddrByCaseNumAndAddrTypeCd(String appNum, Long caseNum, String addrTypeCd ) {
        ArAppAddrDto arAppAddrDto = new ArAppAddrDto();
        try
        {
            DcCaseAddresses dcCaseAddrList  = dcCaseAddressesRepository.findByCaseNumAndAddrTypeCd(caseNum,addrTypeCd);

            if (dcCaseAddrList != null) {
                    arAppAddrDto = dcCaseAddressesCustomMapping.toDto(dcCaseAddrList);
                    arAppAddrDto.setAppNum(appNum);

            }
        }catch (Exception e)
        {
            log.error("Error inside findDcAddrByCaseNumAndAddrTypeCd ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arAppAddrDto;
    }


    public AppContactDetailsResponse insertContactDetails(AppContactDetailsResponse appContactDetailsResponse) {
        AppContactDetailsResponse dbResponse= appContactDetailsResponse;
        try {

            log.info("Persist AR Application for aid");
            //Persist ArApplicationForAid Preferred Method and Time
            if (appContactDetailsResponse.getArApplicationForAid() != null) {
                ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = appContactDetailsResponse.getArApplicationForAid();
                ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appContactDetailsResponse.getAppNum());
                arApplicationForAid.setWeekdayContMethSw(arApplicationForAidReqAndResp.getWeekdayContMethSw());
                arApplicationForAid.setWeekdayContTime(arApplicationForAidReqAndResp.getWeekdayContTime());
                arApplicationForAidRepository.save(arApplicationForAid);
            }

            log.info("Persist AR Phone");
            dbResponse.setArPhoneList(persisteArPhoneDetails(appContactDetailsResponse));


            log.info("Persist AR Email");
            //Persist Emails Array
            persistArEmailDetails(appContactDetailsResponse);

            log.info("Persist AR Permanent Address");

            //Persist Permanent Address
            persistPermanentAddress(appContactDetailsResponse);

            //Persist Mailing Address
            persistMailingAddress(appContactDetailsResponse);

            //Setting the final email values so that UI can have the updated details in response.
            dbResponse.setArEmailList(arEmailDetailsMapping.toDto(findEmailByAppNumAndEmailSrcTyp(appContactDetailsResponse.getAppNum(),"PA")));
        } catch (Exception e) {
            log.error("Error while inserting Contact Details ", e);
            throw new AppRegCustomException(e.toString());
        }
        return dbResponse;
    }

    public void deleteArData(String appNum) {

        log.info("Delete AR data");
        String phoneSrcTyp = "PA";
        String emailSrcTyp = "PA";

        try{
            List<ArPhnDetails> arPhnDetails = arPhnDetailsRepository.findByAppNumAndPhoneSrcTyp(appNum, phoneSrcTyp);
            arPhnDetailsRepository.deleteAll(arPhnDetails);

            List<ArEmailDetails> emailDetails = arEmailDetailsRepository.findByAppNumAndEmailSrcTyp(appNum, emailSrcTyp);
            arEmailDetailsRepository.deleteAll(emailDetails);

            ArAppAddr arAppAddrPA = arAppAddrRepository.findByAppNumAndAddrTypeCd(appNum, "PA");
            if(null != arAppAddrPA) {
                arAppAddrRepository.delete(arAppAddrPA);
            }

            ArAppAddr arAppAddrMA = arAppAddrRepository.findByAppNumAndAddrTypeCd(appNum, "MA");
            if(null != arAppAddrMA) {
                arAppAddrRepository.delete(arAppAddrMA);
            }

            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            if(null != arApplicationForAid) {
                arApplicationForAid.setWeekdayContMethSw(null);
                arApplicationForAid.setWeekdayContTime(null);
                arApplicationForAidRepository.save(arApplicationForAid);
            }

        } catch (Exception e) {
            log.error("Error while deleting ArData ", e);
            throw new AppRegCustomException(e.toString());
        }
    }

    public String deletePhoneData(String appNum, List<String> delPhoneCodeList)
    {
        String respone="";
        try
        {
            ArPhnDetails arPhnDetails = null;
                if(!delPhoneCodeList.isEmpty()) {
                    for (String del : delPhoneCodeList) {
                        arPhnDetails = arPhnDetailsRepository.findByAppNumAndPhoneSrcTypAndPhnTypeCd(
                                appNum, "PA", del
                        );
                        arPhnDetailsRepository.delete(arPhnDetails);
                    }
                }
                respone="Success";
        } catch (Exception e)
                {
                    log.error("Error while deleting Phone Data ", e);
                    throw new AppRegCustomException(e.toString());
                }
        return respone;
    }

    public String deleteEmailData(String appNum, List<Long> delEmailList) {
        String response="";
        try
        {
            ArEmailDetails arEmailDetails = null;
                for (Long del : delEmailList) {
                    arEmailDetails = arEmailDetailsRepository.findByAppNumAndEmailSrcTypAndEmailSeqNum(
                            appNum, "PA", del
                    );
                    arEmailDetailsRepository.delete(arEmailDetails);
                }
            response="Success";
        } catch (Exception e) {
            log.error("Error while deleting Email Data ", e);
            throw new AppRegCustomException(e.toString());
        }
        return response;
    }

    private List<ArPhnDetailsDto> persisteArPhoneDetails(AppContactDetailsResponse appContactDetailsResponse){
        List<ArPhnDetailsDto> arPhnDetailsDtoUIList = appContactDetailsResponse.getArPhoneList();
        List<ArPhnDetails> arPhnDetailsDtoDBList =
                findPhoneByAppNumAndPhoneSrcTyp
                        (appContactDetailsResponse.getAppNum(),"PA");
        List<String>arPhnCdDBList=
                arPhnDetailsDtoDBList.stream().map(ArPhnDetails::getPhnTypeCd).collect(Collectors.toList());
        List<String>arPhnCdUIList=
                arPhnDetailsDtoUIList.stream().map(ArPhnDetailsDto::getPhnTypeCd).collect(Collectors.toList());
        //Save or Update the incoming values start
        ArPhnDetails finalDto=null;
        List<ArPhnDetailsDto> phnFinaResp = new ArrayList<>();
        if(!arPhnDetailsDtoDBList.isEmpty())
        {
            for(ArPhnDetailsDto ui:arPhnDetailsDtoUIList)
            {
                for(ArPhnDetails db:arPhnDetailsDtoDBList)
                {

                    if(ui.getPhnTypeCd().equalsIgnoreCase(db.getPhnTypeCd()))
                    {
                        arPhnDetailsMapping.mergeEntity(ui,db);
                        finalDto=arPhnDetailsRepository.save(db);
                        phnFinaResp.add(arPhnDetailsMapping.toDto(finalDto));
                    }
                }
            }
        }
        else {
            for(ArPhnDetailsDto uiNewRecord:arPhnDetailsDtoUIList)
            {
                finalDto=arPhnDetailsRepository.save(
                        arPhnDetailsMapping.toEntity(uiNewRecord));
                phnFinaResp.add(arPhnDetailsMapping.toDto(finalDto));
            }
        }
        //Save or Update the incoming values end

        phnFinaResp.addAll(insertNewArPhoneRecords(arPhnCdDBList,arPhnCdUIList,arPhnDetailsDtoDBList,arPhnDetailsDtoUIList,finalDto));

        //delete newly received values start
        List<String>delProgramList=new ArrayList<>();
        delProgramList.addAll(arPhnCdDBList);
        delProgramList.removeAll(arPhnCdUIList);
        deletePhoneData(appContactDetailsResponse.getAppNum(),delProgramList);
        return phnFinaResp;

    }

    private List<ArPhnDetailsDto> insertNewArPhoneRecords(List<String> arPhnCdDBList,List<String> arPhnCdUIList,List<ArPhnDetails> arPhnDetailsDtoDBList,List<ArPhnDetailsDto> arPhnDetailsDtoUIList,ArPhnDetails finalDto){
        //Insert newly received values start
        List<String>newPhnCdList=new ArrayList<>();
        List<ArPhnDetailsDto> phnFinaResp = new ArrayList<>();
        newPhnCdList.addAll(arPhnCdUIList);
        newPhnCdList.removeAll(arPhnCdDBList);
        if(!newPhnCdList.isEmpty() && !arPhnDetailsDtoDBList.isEmpty())
        {
            for(String uiInsertNew : newPhnCdList)
            {
                for(ArPhnDetailsDto uiRecord:arPhnDetailsDtoUIList)
                {
                    if(uiRecord.getPhnTypeCd().equalsIgnoreCase(uiInsertNew))
                    {
                        finalDto=arPhnDetailsRepository.save(
                                arPhnDetailsMapping.toEntity(uiRecord));
                        phnFinaResp.add(arPhnDetailsMapping.toDto(finalDto));
                    }
                }
            }
        }
        //Insert newly received values end
        return phnFinaResp;
    }

    private void persistArEmailDetails(AppContactDetailsResponse appContactDetailsResponse){
        boolean completeNewRecord=false;
        List<ArEmailDetailsDto> arEmailDetailsDtoUIList = appContactDetailsResponse.getArEmailList();
        List<ArEmailDetails> arEmailDetailsDtoDBList =
                findEmailByAppNumAndEmailSrcTyp(appContactDetailsResponse.getAppNum(),"PA");
        if(!arEmailDetailsDtoDBList.isEmpty())
        {
            processExistingArEmailRecords(arEmailDetailsDtoUIList,arEmailDetailsDtoDBList);
        }
        else {
            for(ArEmailDetailsDto uiNewRecord:arEmailDetailsDtoUIList)
            {
                arEmailDetailsRepository.save(
                        arEmailDetailsMapping.toEntity(uiNewRecord));
                completeNewRecord=true;
            }
        }
        if(!arEmailDetailsDtoUIList.isEmpty() && !completeNewRecord)
        {
            for(ArEmailDetailsDto uiNewRecord:arEmailDetailsDtoUIList)
            {
                if(uiNewRecord.getEmailSeqNum()==null)
                {
                    arEmailDetailsRepository.save(
                            arEmailDetailsMapping.toEntity(uiNewRecord));
                }
            }
        }
    }

    private void persistPermanentAddress(AppContactDetailsResponse appContactDetailsResponse){
        if (appContactDetailsResponse.getArAppAddrPA() != null &&
                appContactDetailsResponse.getArAppAddrPA().getAppNum() != null &&
                appContactDetailsResponse.getArAppAddrPA().getAddrLine() != null)
        {
            ArAppAddrDto arAppAddrUIDto= appContactDetailsResponse.getArAppAddrPA();
            if(arAppAddrUIDto.getAddrCountyCd()!=null && arAppAddrUIDto.getAddrCountyCd().equalsIgnoreCase(""))
            {
                arAppAddrUIDto.setAddrCountyCd(null);
            }
            ArAppAddr arAppAddrdbVO=
                    findAppAddrDetailsAndAddrTypeCd(
                            appContactDetailsResponse.getArAppAddrPA().getAppNum(),
                            appContactDetailsResponse.getArAppAddrPA().getAddrTypeCd()
                    );

            if(arAppAddrdbVO!=null)
            {
                arAppAddrMapping.mergeEntity(arAppAddrUIDto,arAppAddrdbVO);
                arAppAddrRepository.save(arAppAddrdbVO);
            }
            else {
                arAppAddrRepository.save(arAppAddrMapping.toEntity(arAppAddrUIDto));
            }
        }
        log.info("Persist AR Mailing Address");
    }

    private void persistMailingAddress(AppContactDetailsResponse appContactDetailsResponse){
        if (appContactDetailsResponse.getArAppAddrMA() != null && appContactDetailsResponse.getArAppAddrMA().getAppNum() != null &&
                appContactDetailsResponse.getArAppAddrMA().getAddrLine() != null && ObjectUtils.isNotEmpty(appContactDetailsResponse.getLivingResSw()) && appContactDetailsResponse.getLivingResSw().equals('Y')) {
            ArAppAddrDto arAppAddrMAFromUI= appContactDetailsResponse.getArAppAddrMA();
            if(arAppAddrMAFromUI.getAddrCountyCd()!=null && arAppAddrMAFromUI.getAddrCountyCd().equalsIgnoreCase(""))
            {
                arAppAddrMAFromUI.setAddrCountyCd(null);
            }
            if(arAppAddrMAFromUI.getAddrLine1()!=null && arAppAddrMAFromUI.getAddrLine1().equalsIgnoreCase(""))
            {
                arAppAddrMAFromUI.setAddrLine1(null);
            }
            ArAppAddr arAppAddrMAFromDB=
                    findAppAddrDetailsAndAddrTypeCd(
                            appContactDetailsResponse.getArAppAddrMA().getAppNum(),
                            appContactDetailsResponse.getArAppAddrMA().getAddrTypeCd()
                    );
            if(arAppAddrMAFromDB!=null)
            {
                arAppAddrMapping.mergeEntity(arAppAddrMAFromUI,arAppAddrMAFromDB);
                arAppAddrRepository.save(arAppAddrMAFromDB);
            }
            else {
                arAppAddrRepository.save(arAppAddrMapping.toEntity(arAppAddrMAFromUI));
            }
        }else{
            ArAppAddr arMaAddr =
                    findAppAddrDetailsAndAddrTypeCd(appContactDetailsResponse.getAppNum(),"MA");
            if(ObjectUtils.isNotEmpty(arMaAddr)){
                arAppAddrRepository.delete(arMaAddr);
            }
        }
    }

    private void processExistingArEmailRecords(List<ArEmailDetailsDto> arEmailDetailsDtoUIList,List<ArEmailDetails> arEmailDetailsDtoDBList){
        for(ArEmailDetailsDto ui:arEmailDetailsDtoUIList)
        {
            for(ArEmailDetails db:arEmailDetailsDtoDBList)
            {

                if(ui.getEmailSeqNum()!=null && ui.getEmailSeqNum().equals(db.getEmailSeqNum()))
                {
                    arEmailDetailsMapping.mergeEntity(ui,db);
                    if(null != db.getEmail() && db.getEmail().equalsIgnoreCase(""))
                    {
                        arEmailDetailsRepository.delete(db);
                    }
                    else
                    {
                        arEmailDetailsRepository.save(db);
                    }
                    break;
                }
            }
        }
    }
}
