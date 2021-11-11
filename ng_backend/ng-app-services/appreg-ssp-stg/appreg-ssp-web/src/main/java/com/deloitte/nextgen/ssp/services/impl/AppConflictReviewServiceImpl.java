package com.deloitte.nextgen.ssp.services.impl;

import com.deloitte.nextgen.ssp.responses.AuthPhnDetailsResponse;
import com.deloitte.nextgen.ssp.responses.AddrConflictsResponse;
import com.deloitte.nextgen.ssp.responses.AuthIndividualInformationResponse;
import com.deloitte.nextgen.ssp.responses.PhoneConflictsResponse;
import com.deloitte.nextgen.ssp.responses.WeekDayPreferenceConflictsResponse;
import com.deloitte.nextgen.ssp.responses.*;
import com.deloitte.nextgen.ssp.repo.AppSearchSSPCustomRepository;
import com.deloitte.nextgen.ssp.services.AppConflictReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppConflictReviewServiceImpl implements AppConflictReviewService {

    @Autowired
    private AppSearchSSPCustomRepository repo;

    @Override
    public List<ApplicantsResponse> findByAppNum(String appNum) {
        List<Object[]> responseList = new ArrayList<>();
        List<ApplicantsResponse> output=null;
        if(appNum!=null)
        {
            responseList.addAll(repo.findConflictApps(appNum));
        }
        if(!responseList.isEmpty())
        {
            output=responseList.stream().map(index->
            {
                ApplicantsResponse indvConflictPanel=new ApplicantsResponse();
                indvConflictPanel.setIndvId((Long.valueOf(index[0].toString())));

                String obj = (null !=index[1] && index[1].toString().equals("Y"))? "Yes"  : "No";
                indvConflictPanel.setPrimaryApplicantSw(obj);

                indvConflictPanel.setIncludeApplicantSw(null !=index[2]  ? (String) index[2].toString() : null);
                indvConflictPanel.setFirstName((String) index[3]);
                indvConflictPanel.setMiddleName((String) index[4]);
                indvConflictPanel.setLastName((String) index[5]);
                indvConflictPanel.setSufxName((String) index[6]);
                indvConflictPanel.setGender(null !=index[7] ? (Character)index[7].toString().charAt(0) : null );
                indvConflictPanel.setAge(Integer.valueOf(null != index[8] ? index[8].toString() : "0"));
                indvConflictPanel.setDob((Timestamp) index[9]);
                indvConflictPanel.setSsn(null != index[10] ? (Long.valueOf(index[10].toString())) : null);
                indvConflictPanel.setRace((String) index[11]);
                indvConflictPanel.setEthnicity((String) index[12]);
                indvConflictPanel.setAliasSw(null != index[13] ? (String) index[13].toString() : null);
                indvConflictPanel.setAliasFirstName((String) index[14]);
                indvConflictPanel.setAliasMiddleName((String) index[15]);
                indvConflictPanel.setAliasLastName((String) index[16]);
                indvConflictPanel.setAliasSuffix((String) index[17]);
                indvConflictPanel.setAliasGender(null !=index[18] ? (Character) index[18].toString().charAt(0) : null);
                indvConflictPanel.setInterpreterSw(null !=index[19] ? (String) index[19].toString() : null);
                indvConflictPanel.setPrimaryLanguage((String) index[20]);
                indvConflictPanel.setSpecificPrimaryLanguage((String) index[21]);
                indvConflictPanel.setAccommodationSw(null != index[22] ? (String) index[22].toString() : null);
                indvConflictPanel.setTypeAccommodation((String) index[23]);
                indvConflictPanel.setAuthRepresentativeSw(null !=index[24] ? (Character) index[24].toString().charAt(0) : null);
                indvConflictPanel.setEbtcardSw(null != index[25] ? (String) index[25].toString() : null);
                indvConflictPanel.setRegistervoteSw(null !=index[26] ? (Character) index[26].toString().charAt(0) : null);
                indvConflictPanel.setIndvStatusSw(null != index[27] ? (Character) index[27].toString().charAt(0) : null);


                return indvConflictPanel;
            }).collect(Collectors.toList());
        }
        return output;
    }

    @Override
    public ContactConflictsResponse findContactDtl(String appNum) {
        ContactConflictsResponse response=new ContactConflictsResponse();
        WeekDayPreferenceConflictsResponse weekDayPreferenceConflictsVO =new WeekDayPreferenceConflictsResponse();
        PhoneConflictsResponse phoneConflictsResponseResponseVO =new PhoneConflictsResponse();
        AddrConflictsResponse addrVO=new AddrConflictsResponse();

        List<Object[]> weekdayResponse=repo.findWeekDayContactDtl(appNum);
        if(weekdayResponse!=null && !weekdayResponse.isEmpty() ) {
            weekDayPreferenceConflictsVO.setPanelName("WeekDay Preference");
            for (Object[] weekDayDtls : weekdayResponse) {
                weekDayPreferenceConflictsVO.setWeekdayContMethSw((String) weekDayDtls[0]);
                weekDayPreferenceConflictsVO.setWeekdayContTime((String) weekDayDtls[1]);
            }
            response.setWeekDayPreferenceConflictsResponse(weekDayPreferenceConflictsVO);
        }


        List<Object[]> phnDtlResponse=repo.findPhnDtl(appNum);
        if(phnDtlResponse!=null && !phnDtlResponse.isEmpty()) {
            phoneConflictsResponseResponseVO.setPanelName("Phone Details");
            for (Object[] phnDtls : phnDtlResponse) {
                phoneConflictsResponseResponseVO.setAppNum((String) phnDtls[0]);
                phoneConflictsResponseResponseVO.setPrimPhnTypeCd((String) phnDtls[1]);
                phoneConflictsResponseResponseVO.setPrimPhnNum((String) phnDtls[2]);
                phoneConflictsResponseResponseVO.setPrimPhnComments((String) phnDtls[3]);
                phoneConflictsResponseResponseVO.setPrimPhoneExtn((String) phnDtls[4]);
                phoneConflictsResponseResponseVO.setPrimPhoneSrcTyp((String) phnDtls[5]);
                phoneConflictsResponseResponseVO.setWrkPhnTypeCd((String) phnDtls[6]);
                phoneConflictsResponseResponseVO.setWrkPhnNum((String) phnDtls[7]);
                phoneConflictsResponseResponseVO.setWrkPhnComments((String) phnDtls[8]);
                phoneConflictsResponseResponseVO.setWrkPhoneExtn((String) phnDtls[9]);
                phoneConflictsResponseResponseVO.setWrkPhoneSrcTyp((String) phnDtls[10]);
                phoneConflictsResponseResponseVO.setAltPhnTypeCd((String) phnDtls[11]);
                phoneConflictsResponseResponseVO.setAltPhnNum((String) phnDtls[12]);
                phoneConflictsResponseResponseVO.setAltPhnComments((String) phnDtls[13]);
                phoneConflictsResponseResponseVO.setAltPhoneExtn((String) phnDtls[14]);
                phoneConflictsResponseResponseVO.setAltPhoneSrcTyp((String) phnDtls[15]);
            }
            response.setPhoneConflictsResponse(phoneConflictsResponseResponseVO);
        }

        List<Object[]>addrDtl=repo.findAddrDtl(appNum);
        if(addrDtl!=null && !addrDtl.isEmpty()) {
            for (Object[] addr : addrDtl) {
                addrVO.setPanelName("Address Details");
                addrVO.setAppNum((String) addr[0]);
                addrVO.setResAddrFormatCd((String) addr[1]);
                addrVO.setResAddrLine((String) addr[2]);
                addrVO.setResAddrLine1((String) addr[3]);
                addrVO.setResAddrCountyCd((String) addr[4]);
                addrVO.setResAddrCity((String) addr[5]);
                addrVO.setResAddrStateCd((String) addr[6]);
                addrVO.setResAddrZip5((String) addr[7]);
                addrVO.setResAddrTypeCd((String) addr[8]);
                addrVO.setMailAddrFormatCd((String) addr[9]);
                addrVO.setMailAddrLine((String) addr[10]);
                addrVO.setMailAddrLine1((String) addr[11]);
                addrVO.setMailAddrCountyCd((String) addr[12]);
                addrVO.setMailAddrCity((String) addr[13]);
                addrVO.setMailAddrStateCd((String) addr[14]);
                addrVO.setMailAddrZip5((String) addr[15]);
                addrVO.setMailAddrTypeCd((String) addr[16]);
                addrVO.setResAddrSw(addr[17].toString().charAt(0));
                addrVO.setEmailAdr((String) addr[18]);
            }
            response.setAddrConflictsResponse(addrVO);
        }
        return response;
    }

    @Override
    public AppContactDetailsResponse fetchContactFromSSP(String appNum) {

        AppContactDetailsResponse appContactDetailsVO = new AppContactDetailsResponse();
        ArApplicationForAidResponse arApplicationForAidDto = new ArApplicationForAidResponse();
        ArAppAddrResponse arAppAddrDtoPA = new ArAppAddrResponse();
        ArAppAddrResponse arAppAddrDtoMA = new ArAppAddrResponse();
        List<ArEmailDetailsResponse> arEmailList = new ArrayList<>();
        arApplicationForAidDto.setAppNum(appNum);

        List<Object[]> weekdayResponse=repo.findWeekDayContactDtl(appNum);
        for(Object[] weekDayDtls: weekdayResponse)
        {
            arApplicationForAidDto.setWeekdayContMethSw((String)weekDayDtls[0]);
            arApplicationForAidDto.setWeekdayContTime((String)weekDayDtls[1]);
        }
        appContactDetailsVO.setArApplicationForAid(arApplicationForAidDto);

        appContactDetailsVO.setArPhoneList(fetchPhnDetailsFrmSsp(appNum));

        List<Object[]>mailDtlResponse=repo.findEmailDtl(appNum);
        ArEmailDetailsResponse arEmailDetailsDto=new ArEmailDetailsResponse();
        if(!mailDtlResponse.isEmpty())
        {
            for(Object[] emlDtls:mailDtlResponse)
            {
                arEmailDetailsDto.setAppNum(appNum);
                arEmailDetailsDto.setEmail((String)emlDtls[1]);
                arEmailDetailsDto.setEmailSrcTyp("PA");
            }
            arEmailList.add(arEmailDetailsDto);
            appContactDetailsVO.setArEmailList(arEmailList);
        }


        List<Object[]>addrDtl=repo.findAddrDtl(appNum);
        if(addrDtl!=null && !addrDtl.isEmpty()) {
            for (Object[] addr : addrDtl) {
                arAppAddrDtoPA.setAppNum(appNum);
                arAppAddrDtoPA.setAddrFormatCd((String) addr[1]);
                arAppAddrDtoPA.setAddrLine((String) addr[2]);
                arAppAddrDtoPA.setAddrLine1((String) addr[3]);
                arAppAddrDtoPA.setAddrCountyCd((String) addr[4]);
                arAppAddrDtoPA.setAddrCity((String) addr[5]);
                arAppAddrDtoPA.setAddrStateCd((String) addr[6]);
                arAppAddrDtoPA.setAddrZip5((String) addr[7]);
                arAppAddrDtoPA.setAddrTypeCd((String) addr[8]);

                if(addr[10]!=null||addr[11]!=null)
                {
                    arAppAddrDtoMA.setAppNum(appNum);
                    arAppAddrDtoMA.setAddrFormatCd((String) addr[9]);
                    arAppAddrDtoMA.setAddrLine((String) addr[10]);
                    arAppAddrDtoMA.setAddrLine1((String) addr[11]);
                    arAppAddrDtoMA.setAddrCountyCd((String) addr[12]);
                    arAppAddrDtoMA.setAddrCity((String) addr[13]);
                    arAppAddrDtoMA.setAddrStateCd((String) addr[14]);
                    arAppAddrDtoMA.setAddrZip5((String) addr[15]);
                    arAppAddrDtoMA.setAddrTypeCd((String) addr[16]);
                }
                arAppAddrDtoPA.setResAddrSw(addr[17].toString().charAt(0));
            }
        }
        appContactDetailsVO.setArAppAddrPA(arAppAddrDtoPA);
        appContactDetailsVO.setArAppAddrMA(arAppAddrDtoMA);
        return appContactDetailsVO;
    }

    public List<ArPhnDetailsResponse> fetchPhnDetailsFrmSsp(String appNum){

        List<ArPhnDetailsResponse> arPhoneList = new ArrayList<>();
        List<Object[]> phnDtlResponse=repo.findPhnDtl(appNum);
        ArPhnDetailsResponse arPhnDetailsDto = null;
        for(Object[] phnDtls:phnDtlResponse)
        {
            if(null !=(String)phnDtls[1]){
                arPhnDetailsDto= new ArPhnDetailsResponse();
                arPhnDetailsDto.setAppNum(appNum);
                arPhnDetailsDto.setPhnTypeCd((String)phnDtls[1]);
                arPhnDetailsDto.setPhnNum((String)phnDtls[2]);
                arPhoneList.add(arPhnDetailsDto);
            }
            if(null !=(String)phnDtls[7]){
                arPhnDetailsDto= new ArPhnDetailsResponse();
                arPhnDetailsDto.setAppNum(appNum);
                arPhnDetailsDto.setPhnTypeCd((String)phnDtls[6]);
                arPhnDetailsDto.setPhnNum((String)phnDtls[7]);
                arPhoneList.add(arPhnDetailsDto);
            }
            if(null !=(String)phnDtls[12]){
                arPhnDetailsDto= new ArPhnDetailsResponse();
                arPhnDetailsDto.setAppNum(appNum);
                arPhnDetailsDto.setPhnTypeCd((String)phnDtls[11]);
                arPhnDetailsDto.setPhnNum((String)phnDtls[12]);
                arPhoneList.add(arPhnDetailsDto);
            }

        }
        return arPhoneList;
    }

    @Override
    public AuthRepConflictsResponse findAuthRepDtl(String appNum) {
        List<Object[]> responseList = new ArrayList<>();
        AuthIndividualInformationResponse indv = new AuthIndividualInformationResponse();
        AuthPhnDetailsResponse phnDtl = new AuthPhnDetailsResponse();

        AuthRepConflictsResponse output=new AuthRepConflictsResponse();
        if(appNum!=null)
        {
            responseList.addAll(repo.findAuthRepAddrDtl(appNum));
        }
        if(!responseList.isEmpty()) {
            indv.setPanelName("Individual Information");
            phnDtl.setPanelName("Phone Details");
            for (Object[] authRepDtls : responseList) {
                indv.setAppNum((String) authRepDtls[0]);
                phnDtl.setAppNum((String) authRepDtls[0]);
                if(authRepDtls[1]!=null)
                {
                indv.setHasAuthRepSw(authRepDtls[1].toString().charAt(0));
                phnDtl.setHasAuthRepSw(authRepDtls[1].toString().charAt(0));
                }
                indv.setAuthRepFirstName((String)authRepDtls[2]);
                indv.setAuthRepLastName((String)authRepDtls[3]);
                indv.setL1Adr((String) authRepDtls[4]);
                indv.setL2Adr((String) authRepDtls[5]);
                indv.setCityAdr((String) authRepDtls[6]);
                indv.setStaAdr((String) authRepDtls[7]);
                indv.setZipAdr((String) authRepDtls[8]);
                phnDtl.setPhnNum((String) authRepDtls[9]);
                phnDtl.setEmailAdr((String) authRepDtls[10]);
                indv.setAuthRepMiddleName((String) authRepDtls[11]);
            }
            output.setAuthIndividualInformationResponse(indv);
            output.setAuthPhnDetailsResponse(phnDtl);
        }
        return output;
    }

    @Override
    public AuthRepResponse findAuthRepSSP(String appNum) {
        List<Object[]> responseList = new ArrayList<>();
        AuthRepResponse output = new AuthRepResponse();
        if(appNum!=null)
        {
            responseList.addAll(repo.findAuthRepAddrDtl(appNum));
        }
        ArApplicationForAidResponse arApplicationForAidDto = new ArApplicationForAidResponse();
        ArAppAddrResponse arAppAddrDtoAA = new ArAppAddrResponse();
        ArPhnDetailsResponse phnDetailsDto=new ArPhnDetailsResponse();
        ArEmailDetailsResponse emailDetailsDto=new ArEmailDetailsResponse();
        List<ArPhnDetailsResponse> arPhoneList = new ArrayList<>();
        List<ArEmailDetailsResponse> arEmailList = new ArrayList<>();
        if(!responseList.isEmpty()) {
            for (Object[] authRepDtls : responseList) {
                arApplicationForAidDto.setAppNum((String) authRepDtls[0]);
                arApplicationForAidDto.setAuthrepFirstName((String)authRepDtls[2]);
                arApplicationForAidDto.setAuthrepLastName((String)authRepDtls[3]);
                arApplicationForAidDto.setAuthrepMidName((String)authRepDtls[11]);
                arApplicationForAidDto.setAuthrepSufxName((String)authRepDtls[12]);

                arAppAddrDtoAA.setAppNum((String) authRepDtls[0]);
                arAppAddrDtoAA.setAddrLine((String) authRepDtls[4]);
                arAppAddrDtoAA.setAddrLine1((String) authRepDtls[5]);
                arAppAddrDtoAA.setAddrCity((String) authRepDtls[6]);
                arAppAddrDtoAA.setAddrStateCd((String) authRepDtls[7]);
                arAppAddrDtoAA.setAddrZip5((String) authRepDtls[8]);

                phnDetailsDto.setAppNum((String) authRepDtls[0]);
                phnDetailsDto.setPhnNum((String) authRepDtls[9]);

                emailDetailsDto.setAppNum((String) authRepDtls[0]);
                emailDetailsDto.setEmail((String)authRepDtls[10]);

            }

            arApplicationForAidDto.setAuthRepSw('Y');
            output.setArApplicationForAid(arApplicationForAidDto);

            output.setArAppAddrAA(arAppAddrDtoAA);

            if(phnDetailsDto.getPhnNum()!=null)
            {
                phnDetailsDto.setPhnTypeCd("PRP");
                phnDetailsDto.setPhoneSrcTyp("AA");
                arPhoneList.add(phnDetailsDto);
                if(!arPhoneList.isEmpty()) {
                    output.setArPhoneList(arPhoneList);
                }
            }
            if(emailDetailsDto.getEmail()!=null)
            {
                emailDetailsDto.setEmailSrcTyp("AA");
                arEmailList.add(emailDetailsDto);
                output.setArEmailList(arEmailList);
            }
        }
        return output;
    }
}
