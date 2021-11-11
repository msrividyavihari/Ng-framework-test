package com.deloitte.nextgen.appreg.web.services.impl;

import com.deloitte.nextgen.appreg.web.entities.*;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.response.AuthRepResponse;
import com.deloitte.nextgen.appreg.web.mappings.*;
import com.deloitte.nextgen.appreg.web.repositories.*;
import com.deloitte.nextgen.appreg.web.services.AuthRepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthRepServiceImpl implements AuthRepService {

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
    private final DcAuthRepCustomRepository dcAuthRepCustomRepository;

    @Override
    public ArApplicationForAidReqAndResp findAppDetails(String appNum) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndRespFinal =null;
        try
        {
          ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
          arApplicationForAidReqAndRespFinal = arApplicationForAidMapping.toDto(arApplicationForAid);
        }
        catch (Exception e)
        {
            log.error("Error inside findAppDetails ", e);
            throw new AppRegCustomException(e.toString());
        }
        return arApplicationForAidReqAndRespFinal;
    }

    @Override
    public List<ArPhnDetails> findPhoneByAppNumAndPhoneSrcTyp(String appNum,String phoneSrcTyp) {
        List <ArPhnDetails> arPhnDetailsList = new ArrayList<>();
        try
        {
            arPhnDetailsList = arPhnDetailsRepository.findByAppNumAndPhoneSrcTyp(appNum,phoneSrcTyp);
        }
        catch (Exception e)
        {
            log.error("Error inside findPhoneByAppNumAndPhoneSrcTyp ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arPhnDetailsList;
    }

    @Override
    public List<ArEmailDetails> findEmailByAppNumAndEmailSrcTyp(String appNum,String emailSrcType) {

        List <ArEmailDetails> arEmailDetailsList = new ArrayList<>();
        try
        {
            arEmailDetailsList = arEmailDetailsRepository.findByAppNumAndEmailSrcTyp(appNum,emailSrcType);
        }
        catch (Exception e)
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
        List<ArPhnDetailsDto> phnDetailsDtoList = new ArrayList<>();
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

        } catch (Exception e)
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
        DcCaseAddresses dcCaseAddrList = dcCaseAddressesRepository.findByCaseNumAndAddrTypeCd(caseNum,addrTypeCd);

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


    @Override
    public ArApplicationForAidReqAndResp findDcAuthRepForCase(String appNum, Long caseNum) {

        ArApplicationForAidReqAndResp arDto = new ArApplicationForAidReqAndResp();
        try
        {
        arDto = dcAuthRepCustomRepository.findDcAuthRepForCase(caseNum);
        if(null!=arDto){
            arDto.setAppNum(appNum);
        }

        }catch (Exception e)
        {
            log.error("Error inside findDcAuthRepForCase ", e);
            throw new AppRegCustomException(e.toString());
        }

        return arDto;
    }


    public AuthRepResponse insertAuthRepDetails(AuthRepResponse authRepResponse) {
        AuthRepResponse dbResponse= authRepResponse;
        try {

            log.info("Persist AR Application for aid");

            //Persist ArApplicationForAid Auth rep info
            if (authRepResponse.getArApplicationForAid() != null) {
                ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = authRepResponse.getArApplicationForAid();
                ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(authRepResponse.getAppNum());
                arApplicationForAid.setAuthrepFirstName(arApplicationForAidReqAndResp.getAuthrepFirstName());
                arApplicationForAid.setAuthrepLastName(arApplicationForAidReqAndResp.getAuthrepLastName());
                arApplicationForAid.setAuthrepMidName(arApplicationForAidReqAndResp.getAuthrepMidName());
                arApplicationForAid.setAuthrepSufxName(arApplicationForAidReqAndResp.getAuthrepSufxName());
                arApplicationForAid.setRelCd(arApplicationForAidReqAndResp.getRelCd());
                arApplicationForAid.setAuthRepSw(arApplicationForAidReqAndResp.getAuthRepSw());
                arApplicationForAidRepository.save(arApplicationForAid);
            }

            log.info("Persist AR Phone");
            persistArPhoneDetails(authRepResponse);


            log.info("Persist AR Email");
            persistArEmailDetails(authRepResponse);


            //Persist Auth Rep Address
            if (authRepResponse.getArAppAddrAA() != null && authRepResponse.getArAppAddrAA().getAppNum() != null &&
                    authRepResponse.getArAppAddrAA().getAddrLine() != null) {
                ArAppAddrDto arAppAddrUIDto= authRepResponse.getArAppAddrAA();
                if(arAppAddrUIDto.getAddrStateCd()!=null && arAppAddrUIDto.getAddrStateCd().equalsIgnoreCase(""))
                {
                    arAppAddrUIDto.setAddrStateCd(null);
                }
                ArAppAddr arAppAddrdbVO=
                        findAppAddrDetailsAndAddrTypeCd(
                                authRepResponse.getArAppAddrAA().getAppNum(),
                                authRepResponse.getArAppAddrAA().getAddrTypeCd()

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

            //Setting the final email values so that UI can have the updated details in response.
            dbResponse.setArEmailList(arEmailDetailsMapping.toDto(findEmailByAppNumAndEmailSrcTyp(authRepResponse.getAppNum(),"AA")));
        } catch (Exception e) {
            log.error("Error inside insertAuthRepDetails ", e);
            throw new AppRegCustomException(e.toString());
        }
        return authRepResponse;
    }
    public void deleteAuthRepData(String appNum) {
        try
        {
            log.info("Delete Auth Rep data");

            ArApplicationForAid arApplicationForAid = arApplicationForAidRepository.findByAppNum(appNum);
            if(null != arApplicationForAid.getAuthRepSw() &&  arApplicationForAid.getAuthRepSw() == Active.YES) {
                arApplicationForAid.setAuthrepFirstName(null);
                arApplicationForAid.setAuthrepMidName(null);
                arApplicationForAid.setAuthrepLastName(null);
                arApplicationForAid.setAuthrepSufxName(null);
                arApplicationForAid.setRelCd(null);
                arApplicationForAid.setAuthRepSw(Active.NO);
                arApplicationForAidRepository.save(arApplicationForAid);
            }

            List<ArPhnDetails> arPhnDetails = arPhnDetailsRepository.findByAppNumAndPhoneSrcTyp(appNum, "AA");
            arPhnDetailsRepository.deleteAll(arPhnDetails);

            List<ArEmailDetails> emailDetails = arEmailDetailsRepository.findByAppNumAndEmailSrcTyp(appNum,"AA");
            arEmailDetailsRepository.deleteAll(emailDetails);

            ArAppAddr arAppAddr = arAppAddrRepository.findByAppNumAndAddrTypeCd(appNum, "AA");
            if(null != arAppAddr) {
                arAppAddrRepository.delete(arAppAddr);
            }
        } catch (Exception e)
        {
            log.error("Error inside deleteAuthRepData ", e);
            throw new AppRegCustomException(e.toString());
        }
    }

    public String deleteEmailData(String appNum, List<Long> delEmailList) {
        String response="";
        try
        {
            ArEmailDetails arEmailDetails = null;
            for (Long del : delEmailList) {
                arEmailDetails = arEmailDetailsRepository.findByAppNumAndEmailSrcTypAndEmailSeqNum(
                        appNum, "AA", del
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

    public String deletePhoneData(String appNum, List<String> delPhoneCodeList)
    {
        String respone="";
        try
        {
            ArPhnDetails arPhnDetails = null;
            if(!delPhoneCodeList.isEmpty()) {
                for (String del : delPhoneCodeList) {
                    arPhnDetails = arPhnDetailsRepository.findByAppNumAndPhoneSrcTypAndPhnTypeCd(
                            appNum, "AA", del
                    );
                    arPhnDetailsRepository.delete(arPhnDetails);
                }
            }

            respone="Success";
        }
        catch (Exception e)
        {
            log.error("Error while deleting Phone Data ", e);
            throw new AppRegCustomException(e.toString());
        }
        return respone;
    }

    private void persistArPhoneDetails(AuthRepResponse authRepResponse){
        //Persist ArPhone Auth rep info
        List<ArPhnDetailsDto> arPhnDetailsDtoUIList = authRepResponse.getArPhoneList();
        List<ArPhnDetails> arPhnDetailsDtoDBList =
                findPhoneByAppNumAndPhoneSrcTyp
                        (authRepResponse.getAppNum(),"AA");
        List<String>arPhnCdDBList=
                arPhnDetailsDtoDBList.stream().map(ArPhnDetails::getPhnTypeCd).collect(Collectors.toList());
        List<String>arPhnCdUIList=
                arPhnDetailsDtoUIList.stream().map(ArPhnDetailsDto::getPhnTypeCd).collect(Collectors.toList());
        //Save or Update the incoming values start
        if(!arPhnDetailsDtoDBList.isEmpty())
        {
            for(ArPhnDetailsDto ui:arPhnDetailsDtoUIList)
            {
                for(ArPhnDetails db:arPhnDetailsDtoDBList)
                {

                    if(ui.getPhnTypeCd().equalsIgnoreCase(db.getPhnTypeCd()))
                    {
                        arPhnDetailsMapping.mergeEntity(ui,db);
                        arPhnDetailsRepository.save(db);
                    }
                }
            }
        }
        else {
            for(ArPhnDetailsDto uiNewRecord:arPhnDetailsDtoUIList)
            {
                arPhnDetailsRepository.save(
                        arPhnDetailsMapping.toEntity(uiNewRecord));
            }
        }
        //Save or Update the incoming values end

        insertNewArPhoneRecords(arPhnCdDBList,arPhnCdUIList,arPhnDetailsDtoDBList,arPhnDetailsDtoUIList);

        //delete newly received values start
        List<String>delProgramList=new ArrayList<>();
        delProgramList.addAll(arPhnCdDBList);
        delProgramList.removeAll(arPhnCdUIList);
        deletePhoneData(authRepResponse.getAppNum(),delProgramList);
        //delete newly received values end
    }

    private void insertNewArPhoneRecords(List<String> arPhnCdDBList,List<String> arPhnCdUIList,List<ArPhnDetails> arPhnDetailsDtoDBList,List<ArPhnDetailsDto> arPhnDetailsDtoUIList){
        //Insert newly received values start
        List<String>newPhnCdList=new ArrayList<>();
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
                        arPhnDetailsRepository.save(
                                arPhnDetailsMapping.toEntity(uiRecord));
                    }
                }
            }
        }
        //Insert newly received values end
    }

    private void persistArEmailDetails(AuthRepResponse authRepResponse){
        //Persist Emails Array
        boolean completeNewRecord=false;
        List<ArEmailDetailsDto> arEmailDetailsDtoUIList = authRepResponse.getArEmailList();
        List<ArEmailDetails> arEmailDetailsDtoDBList =
                findEmailByAppNumAndEmailSrcTyp(authRepResponse.getAppNum(),"AA");
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

    private void processExistingArEmailRecords(List<ArEmailDetailsDto> arEmailDetailsDtoUIList,List<ArEmailDetails> arEmailDetailsDtoDBList ){
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
