package com.deloitte.nextgen.controllers;


import com.deloitte.nextgen.framework.web.response.ApiResponse;
import com.deloitte.nextgen.persistence.dto.entities.ArAppIndvDto;
import com.deloitte.nextgen.persistence.dto.entities.ArAppProgramDto;
import com.deloitte.nextgen.persistence.dto.entities.ArApplicationForAidDto;
import com.deloitte.nextgen.persistence.dto.entities.ArExpScreenRespDto;
import com.deloitte.nextgen.persistence.dto.entities.*;
import com.deloitte.nextgen.persistence.dto.vo.*;
import com.deloitte.nextgen.persistence.dto.vo.ArAppProgramInputVo;
import com.deloitte.nextgen.persistence.dto.vo.AssociateCaseVO;
import com.deloitte.nextgen.services.ApplicationRegistrationService;
import com.deloitte.nextgen.services.AuthRepService;
import com.deloitte.nextgen.services.ContactInformationService;
import com.deloitte.nextgen.utils.ConflictPanelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/application")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class RegisterApplicationController {
    private final ApplicationRegistrationService applicationRegistrationService;
    private final ContactInformationService contactInformationService;
    private final AuthRepService authRepService;

    RestTemplate restTemplate=new RestTemplate();

    @PostMapping("/associateCase/{appNum}/{caseNum}")
    public ResponseEntity<ApiResponse<ArApplicationForAidDto>> associateCase(@PathVariable String appNum, @PathVariable Long caseNum)  {
        ArApplicationForAidDto arApplicationForAidDto= applicationRegistrationService.associateCase(appNum,caseNum);
        return ApiResponse.success(100).data(arApplicationForAidDto);
    }

//    @PostMapping("/associateCasetest")
//    public ResponseEntity associateCase( )  {
//        Long caseNum = Double.doubleToLongBits(Math.random()*100000);
//        return new ResponseEntity(applicationRegistrationService.associateCase("T22256135", caseNum), HttpStatus.CREATED);
//
//    }

    @PostMapping("/fetchAssociateCases/{indvId}/{appNum}")
    public ResponseEntity<ApiResponse<List<AssociateCaseVO>>> fetchAssociateCases(@PathVariable Long indvId, @PathVariable String appNum){
        List<AssociateCaseVO> caseList= applicationRegistrationService.fetchAssociateCases(indvId, appNum);
        return ApiResponse.success(100).data(caseList);
    }

    @PostMapping("/fetchAssociateCaseForAppNum")
    public ResponseEntity<ApiResponse<String>> fetchAssociateCaseForAppNum(@RequestBody ArApplicationForAidDto arApplicationForAidDto){
        String caseNum= applicationRegistrationService.fetchAssociateCaseForAppNum(arApplicationForAidDto.getAppNum());
        return ApiResponse.success(100).data(caseNum);
    }

    @RequestMapping("/validateAddress")
    public ResponseEntity addressValidate(@RequestParam("Street") String Street1,
                                          @RequestParam("Street2") String Street2,
                                          @RequestParam("City") String city,
                                          @RequestParam("State") String state,
                                          @RequestParam("ZipCode") String zipCode,
                                          @RequestParam("Urbanization") String urbanization)  {
        return new ResponseEntity(applicationRegistrationService.validateAddress(Street1,Street2,city,state,zipCode),HttpStatus.OK);

    }

    @PostMapping("/createApp")
    public ResponseEntity<ApiResponse<ArApplicationForAidDto>>  insertIntoArAppFrAid(@RequestBody ArApplicationForAidDto arApplicationForAidDto){

        ArApplicationForAidDto arApplicationForAidDto1 = applicationRegistrationService.insertIntoArAppFrAid(arApplicationForAidDto);
        return ApiResponse.success(100).data(arApplicationForAidDto1);
    }

    @PostMapping("/insertArAppIndv")
    public ResponseEntity<ApiResponse<ArAppIndvDto>> insertArAppIndv(@RequestBody ArAppIndvDto arAppIndvDto){
        ArAppIndvDto arAppIndvDto1 = applicationRegistrationService.insertArAppIndv(arAppIndvDto);
        return ApiResponse.success(100).data(arAppIndvDto1);
    }

    @PostMapping("/findByAppNum/{appNum}")
    public  ResponseEntity<ApiResponse<ArApplicationForAidDto>> findByAppNum(@PathVariable String appNum){
        ArApplicationForAidDto arAppIndvDto1 = applicationRegistrationService.findByAppNum(appNum);
        return ApiResponse.success(100).data(arAppIndvDto1);
    }


    @PostMapping("/fetchApplicantsInfo")
    public ResponseEntity<ApiResponse<List<ApplicantsResponseVO>>> fetchApplicantsInfo(@RequestBody ApplicantsRequestVO applicantsRequestVO){
        List<ApplicantsResponseVO>  applicantsResponseVO = applicationRegistrationService.fetchApplicantsInfo(applicantsRequestVO);
        return ApiResponse.success(100).data(applicantsResponseVO);
    }

    @GetMapping("/fetchApplInfoPanelDetails/{appNum}")
    public ResponseEntity<ApiResponse<List<ApplicantsResponseVO>>> fetchApplInfoPanelDetails(@PathVariable String appNum){
        List<ApplicantsResponseVO>  applicantsResponseVO = applicationRegistrationService.fetchApplInfoPanelDetails(appNum);
        return ApiResponse.success(100).data(applicantsResponseVO);
    }

    @PostMapping("/insertUpdateApplicants")
    public ResponseEntity<ApiResponse<List<ApplicantsResponseVO>>> insertUpdateApplicants(@RequestBody AppNumApplicantsRequestVO appNumApplicantsRequestVO){
          String apiResponse=null;
       //apiResponse=fetchSSPAppNum(appNumApplicantsRequestVO.getAppNum());
        if(apiResponse!=null && apiResponse.equalsIgnoreCase("NEW_RECORD"))
        {
           appNumApplicantsRequestVO.getApplicants().forEach(f-> f.setIndvId(null));
        }
        List<ApplicantsResponseVO>  applicantsResponseVO = applicationRegistrationService.insertUpdateApplicants(
                appNumApplicantsRequestVO.getAppNum(), appNumApplicantsRequestVO.getApplicants());
        return ApiResponse.success(100).data(applicantsResponseVO);
    }

    public String fetchSSPAppNum(String appNum)
    {
        String finalResponse=null;
        //AWS URL
        //String url="http://15.207.142.199/ar-ssp-api/appsearch/findApps/"+appNum;
        //Local URL
        String url="http://localhost:8085/ar-ssp-api/appsearch/findApps/"+appNum;
        ResponseEntity<String>responseEntity=restTemplate.getForEntity(url,String.class);
        finalResponse= responseEntity.getBody();
        return finalResponse;
    }


    @PostMapping("/insertArExpResp/{appNum}")
    public ResponseEntity<ApiResponse<List<ArExpScreenRespDto>>> insertArExpResp(@RequestBody ArExpScreenRespVO arExpScreenRespVO, @PathVariable String appNum){
        List<ArExpScreenRespDto> arExpResp = applicationRegistrationService.insertArExpResp(arExpScreenRespVO.getArExpScreenRespDtoList(), appNum);
        return ApiResponse.success(100).data(arExpResp);
    }

    @GetMapping("/fetchExpInfo/{appNum}")
    public ResponseEntity<ApiResponse<List<ArExpScreenRespDto>>> fetchExpInfo(@PathVariable String appNum){
        List<ArExpScreenRespDto> arExpScreenRespDto = applicationRegistrationService.fetchExpInfo(appNum);
        return ApiResponse.success(100).data(arExpScreenRespDto);
    }

    @PostMapping("/insertPriorityAppStatus")
    public ResponseEntity<ApiResponse<ArApplicationForAidDto>> insertPriorityAppStatus(@RequestBody PriorityStatusVO priorityStatusVO) {
        ArApplicationForAidDto arApplicationForAidDto = applicationRegistrationService.insertPriorityAppStatus(priorityStatusVO);
        return ApiResponse.success(100).data(arApplicationForAidDto);
    }

    @PostMapping("/insertArAppProgram")
    public ResponseEntity<ApiResponse<List<ArAppProgramDto>>> insertArAppProgram(@RequestBody ArAppProgramInputVo arAppProgramInputVo){
        List<ArAppProgramDto> arAppProgramDto1 = applicationRegistrationService.insertArAppProgram(arAppProgramInputVo);
        return ApiResponse.success(100).data(arAppProgramDto1);
    }

    @PostMapping("/fetchByAppNum/{appNum}")
    public  ResponseEntity<ApiResponse<List<ArAppProgramDto>>> fetchByAppNum(@PathVariable String appNum){
        List<ArAppProgramDto>arAppProgramDtoList = applicationRegistrationService.fetchArAppProgramByAppNum(appNum);
        return ApiResponse.success(100).data(arAppProgramDtoList);
    }


    @PostMapping("/insertArAppProgramIndv")
    public ResponseEntity<ApiResponse<List<ArAppProgramIndvDto>>> insertArAppProgramIndv(@RequestBody List<ArAppProgramIndvInputVo> arAppProgramIndvInputVo){
        List<ArAppProgramIndvDto> arAppProgramIndvDto1 = applicationRegistrationService.insertArAppProgramIndv(arAppProgramIndvInputVo);
        return ApiResponse.success(100).data(arAppProgramIndvDto1);
    }

    @GetMapping("/fetchArAppProgramIndv/{appNum}")
    public  ResponseEntity<ApiResponse<List<ArAppProgramIndvDto>>> fetchArAppProgramIndv(@PathVariable String appNum){
        List<ArAppProgramIndvDto> arAppProgramIndvDtoList = applicationRegistrationService.findArAppProgramIndvByAppNum(appNum);
        return ApiResponse.success(100).data(arAppProgramIndvDtoList);
    }

    @PostMapping("/fetchProgress/{appNum}")
    public  ResponseEntity<ApiResponse<Integer>> fetchProgressForApp(@PathVariable String appNum){
        Integer completionPercent = applicationRegistrationService.fetchProgressForApp(appNum);
        return ApiResponse.success(100).data(completionPercent);
    }

    @PostMapping("/submitApplication/{appNum}")
    public  ResponseEntity<ApiResponse<Boolean>> submitApplication(@PathVariable String appNum){
        Boolean isSubmitted = applicationRegistrationService.submitApplication(appNum);
        return ApiResponse.success(100).data(isSubmitted);
    }

    @PostMapping("/fetchConflictApplicants/{appNum}")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictApplicants(@PathVariable String appNum,@RequestBody ApplicantsRequestVO applicantsRequestVO) throws IllegalAccessException {
        List<ApplicantsResponseVO>  arApplicantsResponseVO = applicationRegistrationService.fetchApplicantsInfo(applicantsRequestVO);
        List<ApplicantsResponseVO>  sspApplicantsResponseVO = ConflictPanelUtil.fetchARApplicants(appNum);
        List<Map<String,Map<String,Object>>> response=new ArrayList<>();
        for(ApplicantsResponseVO DC: arApplicantsResponseVO)
        {
            ApplicantsResponseVO SSP=sspApplicantsResponseVO.stream().
                    filter(temp -> temp.getSsn().equals(DC.getSsn())).
                    findAny().orElse(null);
            if (SSP != null) {
                response.add(ConflictPanelUtil.fetchConflicts(SSP,DC,"ApplicantsResponseVO"));
            }
        }
        return ApiResponse.success(100).data(response);
    }

    @PostMapping("/fetchConflictContacts/{appNum}")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictContacts(@PathVariable String appNum,@RequestBody AppContactDetailsVO appContactDetailsVO) throws IllegalAccessException {
        ContactConflictsResponseVO sspResponse= ConflictPanelUtil.findSSPContactConflicts(appNum);
        ContactConflictsResponseVO dcResponse=new ContactConflictsResponseVO();

        List<Map<String,Map<String,Object>>> response=new ArrayList<>();

        WeekDayPreferenceConflictsResponseVO dcWeekDayPreferenceConflictsResponseVO =new WeekDayPreferenceConflictsResponseVO();
        dcWeekDayPreferenceConflictsResponseVO.setPanelName("WeekDay Preference");
        dcWeekDayPreferenceConflictsResponseVO.setWeekdayContMethSw(appContactDetailsVO.getArApplicationForAid().getWeekdayContMethSw());
        dcWeekDayPreferenceConflictsResponseVO.setWeekdayContTime(appContactDetailsVO.getArApplicationForAid().getWeekdayContTime());
        dcResponse.setWeekDayPreferenceConflictsResponseVO(dcWeekDayPreferenceConflictsResponseVO);

        if(sspResponse.getWeekDayPreferenceConflictsResponseVO()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getWeekDayPreferenceConflictsResponseVO(),dcResponse.getWeekDayPreferenceConflictsResponseVO(),"WeekDayPreference");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        PhoneConflictsResponseVO phoneConflictsResponseVO = ConflictPanelUtil.toResponseEntityPhone(appContactDetailsVO.getArPhoneList());
        phoneConflictsResponseVO.setPanelName("Phone Details");
        dcResponse.setPhoneConflictsResponseVO(phoneConflictsResponseVO);
        if(sspResponse.getPhoneConflictsResponseVO()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getPhoneConflictsResponseVO(),dcResponse.getPhoneConflictsResponseVO(),"DCPhoneConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        AddrConflictsResponseVO addrConflictsResponseVO = ConflictPanelUtil.toResponseEntityAddr(appContactDetailsVO.getArAppAddrPA(),appContactDetailsVO.getArAppAddrMA());
        dcResponse.setAddrConflictsResponseVO(addrConflictsResponseVO);
        if(sspResponse.getAddrConflictsResponseVO()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAddrConflictsResponseVO(),dcResponse.getAddrConflictsResponseVO(),"DCAddrConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        return ApiResponse.success(100).data(response);
    }

    @PostMapping("/fetchConflictAuthRep/{appNum}")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictAuthRep(@PathVariable String appNum,@RequestBody AuthRepVO authRepVO) throws IllegalAccessException {
        AuthRepConflictsResponseVO sspResponse= ConflictPanelUtil.findSSPAuthRepConflicts(appNum);
        AuthRepConflictsResponseVO dcResponse = new AuthRepConflictsResponseVO();

        List<Map<String,Map<String,Object>>> response=new ArrayList<>();

        AuthIndividualInformationVO authIndividualInformationVO = new AuthIndividualInformationVO();
        authIndividualInformationVO.setPanelName("Individual Information");
        authIndividualInformationVO.setAppNum(authRepVO.getAppNum());
        authIndividualInformationVO.setAuthRepFirstName(authRepVO.getArApplicationForAid().getAuthrepFirstName());
        authIndividualInformationVO.setAuthRepMiddleName(authRepVO.getArApplicationForAid().getAuthrepMidName());
        authIndividualInformationVO.setAuthRepLastName(authRepVO.getArApplicationForAid().getAuthrepLastName());
        authIndividualInformationVO.setL1Adr(authRepVO.getArAppAddrAA().getAddrLine());
        authIndividualInformationVO.setL2Adr(authRepVO.getArAppAddrAA().getAddrLine1());
        authIndividualInformationVO.setCityAdr(authRepVO.getArAppAddrAA().getAddrCity());
        authIndividualInformationVO.setStaAdr(authRepVO.getArAppAddrAA().getAddrStateCd());
        authIndividualInformationVO.setZipAdr(authRepVO.getArAppAddrAA().getAddrZip5());
        if(authIndividualInformationVO != null) {
            dcResponse.setAuthIndividualInformationVO(authIndividualInformationVO);
        }

        if(sspResponse.getAuthIndividualInformationVO()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAuthIndividualInformationVO(),dcResponse.getAuthIndividualInformationVO(), "AuthIndividualInformation");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }


        AuthPhnDetailsVO authPhnDetails = new AuthPhnDetailsVO();
        authPhnDetails.setPanelName("Phone Details");
        authPhnDetails.setAppNum(authRepVO.getAppNum());
        ArPhnDetailsDto arPhnDetailsDto=authRepVO.getArPhoneList().stream().filter(i->i.getPhoneSrcTyp().equalsIgnoreCase("AA")).findFirst().orElse(null);
        authPhnDetails.setPhnNum(arPhnDetailsDto.getPhnNum());

        if (authRepVO.getArAppAddrAA() != null) {
            authPhnDetails.setHasAuthRepSw('Y');
        } else {
            authPhnDetails.setHasAuthRepSw('N');
        }

        if(authPhnDetails != null) {
            dcResponse.setAuthPhnDetailsVO(authPhnDetails);
        }

        if(sspResponse.getAuthPhnDetailsVO()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAuthPhnDetailsVO(), dcResponse.getAuthPhnDetailsVO(),"AuthPhoneConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        return ApiResponse.success(100).data(response);
    }
}

