package com.deloitte.nextgen.appreg.web.utils;

import com.deloitte.nextgen.appreg.client.response.*;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.request.AppIndvListRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ConflictPanelUtil {

    static RestTemplate restTemplate=new RestTemplate();

    public enum excludeFieldList{
        INDVID,INDVSTATUSSW,SPECIFICPRIMARYLANGUAGE,APPNUM,PRIMPHONESRCTYP,HASAUTHREPSW,WRKPHONESRCTYP,ALTPHONESRCTYP,PRIMPHNTYPECD,WRKPHNTYPECD,ALTPHNTYPECD;
    };

    public enum applicantsIncludeList{
        FIRSTNAME,LASTNAME,AGE,GENDER,SSN
    }


    private static final String WEEK_DAY_PREFERENCE = "WeekDayPreference";
    private static final String AUTH_INDIVIDUAL_INFORMATION = "AuthIndividualInformation";
    private static final String AUTH_PHONE_CONFLICTS = "AuthPhoneConflicts";
    private static final String UNIQUE_ID = "UNIQUE_ID";
    private static final String PANEL_NAME = "PanelName";
    private static final String PANEL_NUMBER = "PanelNumber";

    public static List<ApplicantsResponse> fetchARApplicants(String appNum) {
        List<ApplicantsResponse> finalResponse=null;
        try
        {
            String url=Constants.fetchSSPApplicantsURL;
            AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
            appIndvListRequest.setAppNum(appNum);
            ResponseEntity<ApplicantsResponse[]> responseEntity=restTemplate.postForEntity(url,appIndvListRequest,ApplicantsResponse[].class);
            finalResponse= Arrays.asList(responseEntity.getBody());
        }catch (Exception e)
        {
            log.error("Error while fetching ArApplicants ", e);
            throw new AppRegCustomException(e.toString());
        }
        return finalResponse;
    }

    public static ContactConflictsResponse findSSPContactConflicts(String appNum) {
        ContactConflictsResponse finalResponse=null;
        try
        {
            AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
            appIndvListRequest.setAppNum(appNum);
            String url=Constants.fetchSSPContactConflictsURL;
            ResponseEntity<ContactConflictsResponse>responseEntity=
                    restTemplate.postForEntity(url,appIndvListRequest,ContactConflictsResponse.class);
            finalResponse=responseEntity.getBody();

        }catch (Exception e)
        {
            log.error("Error inside findSSPContactConflicts ", e);
            throw new AppRegCustomException(e.toString());
        }
        return finalResponse;
    }

    public static AuthRepConflictsResponse findSSPAuthRepConflicts(String appNum) {
        AuthRepConflictsResponse authRepResponseVO=new AuthRepConflictsResponse();
        try
        {
            AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
            appIndvListRequest.setAppNum(appNum);
            String url=Constants.fetchSSPAuthRepConflictsURL;
            ResponseEntity<AuthRepConflictsResponse>responseEntity=
                    restTemplate.postForEntity(url,appIndvListRequest,AuthRepConflictsResponse.class);
            authRepResponseVO=responseEntity.getBody();

        }catch (Exception e)
        {
            log.error("Error inside findSSPAuthRepConflicts ", e);
            throw new AppRegCustomException(e.toString());
        }
        return authRepResponseVO;
    }

    public static Map<String, Map<String,Object>> fetchConflicts(Object s1, Object s2, String source) throws IllegalAccessException
    {
        Map<String,Map<String,Object>>changedProperties=new LinkedHashMap<>();
        Map<String,Object>dcModel=new HashMap<>();
        Map<String,Object>sspModel=new HashMap<>();
        Map<String,Object>panelHeaders=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        List<Field>fieldList=new ArrayList<>();
        if(source.equalsIgnoreCase("ApplicantsResponseVO"))
            {fieldList=Arrays.asList(ApplicantsResponse.class.getDeclaredFields());}
        if(source.equalsIgnoreCase(WEEK_DAY_PREFERENCE))
            {fieldList=Arrays.asList(WeekDayPreferenceConflictsResponse.class.getDeclaredFields());}
        if(source.equalsIgnoreCase("DCPhoneConflicts"))
            {fieldList=Arrays.asList(PhoneConflictsResponse.class.getDeclaredFields());}
        if(source.equalsIgnoreCase("DCAddrConflicts"))
            {fieldList=Arrays.asList(AddrConflictsResponse.class.getDeclaredFields());}
        if(source.equalsIgnoreCase(AUTH_INDIVIDUAL_INFORMATION))
            {fieldList=Arrays.asList(AuthIndividualInformationResponse.class.getDeclaredFields());}
        if(source.equalsIgnoreCase(AUTH_PHONE_CONFLICTS))
            {fieldList=Arrays.asList(AuthPhnDetailsResponse.class.getDeclaredFields());}


        for (Field field : fieldList) {
            field.setAccessible(true);
            Object value1 = field.get(s1);
            Object value2 = field.get(s2);

            boolean ignoreFields = Arrays.stream(ConflictPanelUtil.excludeFieldList.values()).anyMatch(i -> i.name().equalsIgnoreCase(field.getName()));
            if (!ignoreFields) {

                if (source.equalsIgnoreCase("ApplicantsResponseVO")) {

                    if (field.getName().equalsIgnoreCase("ssn")) {
                        panelHeaders.put(UNIQUE_ID, value1);
                    }

                    boolean includeFlag = Arrays.stream(ConflictPanelUtil.applicantsIncludeList.values()).anyMatch(i -> i.name().equalsIgnoreCase(field.getName()));
                    if (includeFlag) {
                        panelHeaders.put(field.getName(), value1);
                    }

                    if (field.getName().equalsIgnoreCase("dob")) {
                        value1 = sdf.format(value1);
                        value2 = sdf.format(value2);
                    }
                }

                if (source.equalsIgnoreCase(WEEK_DAY_PREFERENCE) && field.getName().equalsIgnoreCase(PANEL_NAME)) {
                    panelHeaders.put(field.getName(), value1);
                    panelHeaders.put(PANEL_NUMBER, "PANEL_1");
                    panelHeaders.put(UNIQUE_ID, WEEK_DAY_PREFERENCE);
                }

                if (source.equalsIgnoreCase("DCPhoneConflicts") && field.getName().equalsIgnoreCase(PANEL_NAME)) {
                    panelHeaders.put(field.getName(), value1);
                    panelHeaders.put(PANEL_NUMBER, "PANEL_2");
                    panelHeaders.put(UNIQUE_ID, "PhoneDetails");
                }

                if (source.equalsIgnoreCase("DcAddrConflicts") && field.getName().equalsIgnoreCase(PANEL_NAME)) {
                    panelHeaders.put(field.getName(), value1);
                    panelHeaders.put(PANEL_NUMBER, "PANEL_3");
                    panelHeaders.put(UNIQUE_ID, "AddressDetails");
                }

                if (source.equalsIgnoreCase(AUTH_INDIVIDUAL_INFORMATION) && field.getName().equalsIgnoreCase(PANEL_NAME)) {
                    panelHeaders.put(field.getName(), value1);
                    panelHeaders.put(PANEL_NUMBER, "PANEL_1");
                    panelHeaders.put(UNIQUE_ID, AUTH_INDIVIDUAL_INFORMATION);
                }

                if (source.equalsIgnoreCase(AUTH_PHONE_CONFLICTS) && field.getName().equalsIgnoreCase(PANEL_NAME)) {
                    panelHeaders.put(field.getName(), value1);
                    panelHeaders.put(PANEL_NUMBER, "PANEL_2");
                    panelHeaders.put(UNIQUE_ID, AUTH_PHONE_CONFLICTS);
                }

                if ((Objects.equals(value1, value2)) || ((value1 == null) && (value2 == null)) ||
                        ((value1 != null) && (value2 != null) &&
                                ((value1.equals("Y") || value1.equals("Yes")) && (value2.equals("Y") || value2.equals("Yes"))))) {
                    continue;
                } else if (!Objects.equals(value1, value2)) {
                    sspModel.put(field.getName(), value1 == null ? "No Value" : value1);
                    dcModel.put(field.getName(), value2 == null ? "No Value" : value2);
                }
            }
            }
        if(!dcModel.isEmpty() && !sspModel.isEmpty())
        {
            changedProperties.put("PANEL_HEADERS", panelHeaders);
            changedProperties.put("DC_MODEL", dcModel);
            changedProperties.put("SSP_MODEL", sspModel);
        }
        return changedProperties;
    }

    public static AddrConflictsResponse toResponseEntityAddr(ArAppAddrDto arAppAddrPADto, ArAppAddrDto arAppAddrMADto) {
        AddrConflictsResponse addrConflictsResponseVO =new AddrConflictsResponse();
        addrConflictsResponseVO.setAppNum(arAppAddrPADto.getAppNum());
        addrConflictsResponseVO.setPanelName("Address Details");
        addrConflictsResponseVO.setResAddrFormatCd(arAppAddrPADto.getAddrFormatCd());
        addrConflictsResponseVO.setResAddrLine(arAppAddrPADto.getAddrLine());
        addrConflictsResponseVO.setResAddrLine1(arAppAddrPADto.getAddrLine1());
        addrConflictsResponseVO.setResAddrCountyCd(arAppAddrPADto.getAddrCountyCd());
        addrConflictsResponseVO.setResAddrCity(arAppAddrPADto.getAddrCity());
        addrConflictsResponseVO.setResAddrStateCd(arAppAddrPADto.getAddrStateCd());
        addrConflictsResponseVO.setResAddrZip5(arAppAddrPADto.getAddrZip5());
        addrConflictsResponseVO.setResAddrTypeCd(arAppAddrPADto.getAddrTypeCd());
        addrConflictsResponseVO.setMailAddrFormatCd(arAppAddrPADto.getAddrFormatCd());
        addrConflictsResponseVO.setMailAddrLine(arAppAddrMADto.getAddrLine());
        addrConflictsResponseVO.setMailAddrLine1(arAppAddrMADto.getAddrLine1());
        addrConflictsResponseVO.setMailAddrCountyCd(arAppAddrMADto.getAddrCountyCd());
        addrConflictsResponseVO.setMailAddrCity(arAppAddrMADto.getAddrCity());
        addrConflictsResponseVO.setMailAddrStateCd(arAppAddrMADto.getAddrStateCd());
        addrConflictsResponseVO.setMailAddrZip5(arAppAddrMADto.getAddrZip5());
        addrConflictsResponseVO.setMailAddrTypeCd(arAppAddrMADto.getAddrTypeCd());
        addrConflictsResponseVO.setResAddrSw(arAppAddrPADto.getResAddrSw());
        return addrConflictsResponseVO;
    }

    public static PhoneConflictsResponse toResponseEntityPhone(List<ArPhnDetailsDto>arPhnDetails){
        PhoneConflictsResponse phoneConflictsResponseVO =new PhoneConflictsResponse();
        arPhnDetails.forEach(i->{
            phoneConflictsResponseVO.setAppNum(i.getAppNum());
            if(i.getPhnTypeCd().equalsIgnoreCase("PRP"))
            {

                phoneConflictsResponseVO.setPrimPhnTypeCd(i.getPhnTypeCd());
                phoneConflictsResponseVO.setPrimPhnNum(i.getPhnNum());
                phoneConflictsResponseVO.setPrimPhnComments(i.getPhnComments());
                phoneConflictsResponseVO.setPrimPhoneExtn(i.getPhoneExtn());
                phoneConflictsResponseVO.setPrimPhoneSrcTyp(i.getPhoneSrcTyp());
            }
            if(i.getPhnTypeCd().equalsIgnoreCase("WOP"))
            {
                phoneConflictsResponseVO.setWrkPhnTypeCd(i.getPhnTypeCd());
                phoneConflictsResponseVO.setWrkPhnNum(i.getPhnNum());
                phoneConflictsResponseVO.setWrkPhnComments(i.getPhnComments());
                phoneConflictsResponseVO.setWrkPhoneExtn(i.getPhoneExtn());
                phoneConflictsResponseVO.setWrkPhoneSrcTyp(i.getPhoneSrcTyp());
            }
            if(i.getPhnTypeCd().equalsIgnoreCase("ALP"))
            {
                phoneConflictsResponseVO.setPrimPhnTypeCd(i.getPhnTypeCd());
                phoneConflictsResponseVO.setAltPhnNum(i.getPhnNum());
                phoneConflictsResponseVO.setAltPhnComments(i.getPhnComments());
                phoneConflictsResponseVO.setAltPhoneExtn(i.getPhoneExtn());
                phoneConflictsResponseVO.setAltPhoneSrcTyp(i.getPhoneSrcTyp());
            }
        });

        return phoneConflictsResponseVO;
    }

}
