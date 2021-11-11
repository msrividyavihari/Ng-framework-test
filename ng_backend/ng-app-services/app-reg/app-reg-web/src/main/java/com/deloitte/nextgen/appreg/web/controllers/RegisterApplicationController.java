package com.deloitte.nextgen.appreg.web.controllers;

import com.deloitte.nextgen.appreg.client.request.*;
import com.deloitte.nextgen.appreg.client.response.*;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.appreg.client.entities.ArExpScreenRespDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.web.services.ApplicationRegistrationService;
import com.deloitte.nextgen.appreg.web.services.AuthRepService;
import com.deloitte.nextgen.appreg.web.services.ContactInformationService;
import com.deloitte.nextgen.appreg.web.utils.ConflictPanelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/application-registration")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class RegisterApplicationController {
    private final ApplicationRegistrationService applicationRegistrationService;
    private final ContactInformationService contactInformationService;
    private final AuthRepService authRepService;

    RestTemplate restTemplate=new RestTemplate();


    @PostMapping("/v1/associate-case/caseNum")
    public ResponseEntity<ApiResponse<ArApplicationForAidReqAndResp>> associateCase(@Valid @RequestBody CaseLinkRequest caseLinkRequest)  {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp =
                applicationRegistrationService.associateCase(caseLinkRequest.getAppNum(),caseLinkRequest.getCaseNum());
        return ApiResponse.success(100).data(arApplicationForAidReqAndResp);
    }

    @PostMapping("/v1/associated-cases/indvid/appnum")
    public ResponseEntity<ApiResponse<List<AssociateCaseResponse>>> fetchAssociateCases(@Valid @RequestBody CaseDetailsRequest caseDetailsRequest) {
        List<AssociateCaseResponse> caseList= applicationRegistrationService.fetchAssociateCases
                                        (caseDetailsRequest.getIndvId(), caseDetailsRequest.getAppNum());
        return ApiResponse.success(100).data(caseList);
    }

    @RequestMapping("/v1/validateAddress")
    public ResponseEntity addressValidate(@RequestParam("Street") String street1,
                                          @RequestParam("Street2") String street2,
                                          @RequestParam("City") String city,
                                          @RequestParam("State") String state,
                                          @RequestParam("ZipCode") String zipCode,
                                          @RequestParam("Urbanization") String urbanization)  {
        return new ResponseEntity(applicationRegistrationService.validateAddress(street1,street2,city,state,zipCode),HttpStatus.OK);

    }

    @PostMapping("/v1/ArApplicationForAid")
    public ResponseEntity<ApiResponse<ArApplicationForAidReqAndResp>>  insertIntoArAppFrAid(@RequestBody ArApplicationForAidReqAndResp arApplicationForAidReqAndResp){

        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp1 = applicationRegistrationService.insertIntoArAppFrAid(arApplicationForAidReqAndResp);
        return ApiResponse.success(100).data(arApplicationForAidReqAndResp1);
    }

    @PostMapping("/v1/application-individuals")
    public ResponseEntity<ApiResponse<ArAppIndvReqAndResp>> insertArAppIndv(@RequestBody ArAppIndvReqAndResp arAppIndvReqAndResp){
        ArAppIndvReqAndResp arAppIndvReqAndResp1 = applicationRegistrationService.insertArAppIndv(arAppIndvReqAndResp);
        return ApiResponse.success(100).data(arAppIndvReqAndResp1);
    }

    @PostMapping("/v1/application-details/appNum")
    public  ResponseEntity<ApiResponse<ArApplicationForAidReqAndResp>> findByAppNum(@Valid @RequestBody AppIndvListRequest appIndvListRequest){
        ArApplicationForAidReqAndResp arAppIndvDto1 = applicationRegistrationService.findByAppNum(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(arAppIndvDto1);
    }


    @PostMapping("/v1/applicants-details")
    public ResponseEntity<ApiResponse<List<ApplicantsResponse>>> fetchApplicantsInfo(@RequestBody ApplicantsRequest applicantsRequest){
        List<ApplicantsResponse> applicantsResponse = applicationRegistrationService.fetchApplicantsInfo(applicantsRequest);
        return ApiResponse.success(100).data(applicantsResponse);
    }


    @PostMapping("/v1/applicants-details/appNum")
    public ResponseEntity<ApiResponse<List<ApplicantsResponse>>> fetchApplInfoPanelDetails(@RequestBody AppIndvListRequest appIndvListRequest){
        List<ApplicantsResponse> applicantsResponse = applicationRegistrationService.fetchApplInfoPanelDetails(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(applicantsResponse);
    }

    @PostMapping("/v1/applicants")
    public ResponseEntity<ApiResponse<List<ApplicantsResponse>>> insertUpdateApplicants(@Valid @RequestBody AppNumApplicantsRequest appNumApplicantsRequest){
//          String apiResponse=null;
//        if(apiResponse!=null && apiResponse.equalsIgnoreCase("NEW_RECORD"))
//        {
//           appNumApplicantsRequest.getApplicants().forEach(f-> f.setIndvId(null));
//        }
        List<ApplicantsResponse> applicantsResponse = applicationRegistrationService.insertUpdateApplicants(
                appNumApplicantsRequest.getAppNum(), appNumApplicantsRequest.getApplicants());
        return ApiResponse.success(100).data(applicantsResponse);
    }

    @PostMapping("/v1/ArExpScreenResp")
    public ResponseEntity<ApiResponse<List<ArExpScreenRespDto>>> insertArExpResp(@Valid @RequestBody ArExpResponse arExpScreenResp){
        List<ArExpScreenRespDto> arExpResp = applicationRegistrationService.insertArExpResp(arExpScreenResp.getArExpScreenRespDtoList(), arExpScreenResp.getAppNum());
        return ApiResponse.success(100).data(arExpResp);
    }

    @PostMapping("/v1/ArExpScreenResp/appNum")
    public ResponseEntity<ApiResponse<List<ArExpScreenRespDto>>> fetchExpInfo(@Valid @RequestBody AppIndvListRequest appIndvListRequest){
        List<ArExpScreenRespDto> arExpScreenResponse = applicationRegistrationService.fetchExpInfo(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(arExpScreenResponse);
    }

    @PostMapping("/v1/PriorityAppStatus")
    public ResponseEntity<ApiResponse<ArApplicationForAidReqAndResp>> insertPriorityAppStatus(@Valid @RequestBody PriorityStatusRequest priorityStatusRequest) {
        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = applicationRegistrationService.insertPriorityAppStatus(priorityStatusRequest);
        return ApiResponse.success(100).data(arApplicationForAidReqAndResp);
    }

    @PostMapping("/v1/ArAppProgram")
    public ResponseEntity<ApiResponse<List<ArAppProgramResponse>>> insertArAppProgram(@Valid @RequestBody ArAppProgramRequest arAppProgramRequest){
        List<ArAppProgramResponse> arAppProgramResponse1 = applicationRegistrationService.insertArAppProgram(arAppProgramRequest);
        return ApiResponse.success(100).data(arAppProgramResponse1);
    }

    @PostMapping("/v1/ArAppProgram/appNum")
    public  ResponseEntity<ApiResponse<List<ArAppProgramResponse>>> fetchByAppNum(@Valid @RequestBody AppIndvListRequest appIndvListRequest){
        List<ArAppProgramResponse> arAppProgramResponseList = applicationRegistrationService.fetchArAppProgramByAppNum(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(arAppProgramResponseList);
    }


    @PostMapping("/v1/ArAppProgramIndv")
    public ResponseEntity<ApiResponse<List<ArAppProgramIndvResponse>>> insertArAppProgramIndv(@Valid @RequestBody List<ArAppProgramIndvRequest> arAppProgramIndvRequest){
        List<ArAppProgramIndvResponse> arAppProgramIndvResponse1 = applicationRegistrationService.insertArAppProgramIndv(arAppProgramIndvRequest);
        return ApiResponse.success(100).data(arAppProgramIndvResponse1);
    }

    @PostMapping("/v1/fetchArAppProgramIndv/appNum")
    public  ResponseEntity<ApiResponse<List<ArAppProgramIndvResponse>>> fetchArAppProgramIndv(@Valid @RequestBody AppIndvListRequest appIndvListRequest){
        List<ArAppProgramIndvResponse> arAppProgramIndvResponseList = applicationRegistrationService.findArAppProgramIndvByAppNum(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(arAppProgramIndvResponseList);
    }

    @PostMapping("/v1/AppIndividual/appNum")
    public ResponseEntity<ApiResponse<List<AppIndvListResponse>>> fetchAppIndv(@Valid @RequestBody AppIndvListRequest appIndvListRequest)  {
        List<AppIndvListResponse> appIndvListResponses = applicationRegistrationService.fetchAppIndv(appIndvListRequest);
        return ApiResponse.success(100).data(appIndvListResponses);
    }

    @PostMapping("/v1/application-progress/appNum")
    public  ResponseEntity<ApiResponse<Integer>> fetchProgressForApp(@RequestBody AppIndvListRequest appIndvListRequest){
        Integer completionPercent = applicationRegistrationService.fetchProgressForApp(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(completionPercent);
    }

    @PutMapping("/v1/submitApplication/appNum")
    public  ResponseEntity<ApiResponse<Boolean>> submitApplication(@RequestBody AppIndvListRequest appIndvListRequest){
        Boolean isSubmitted = applicationRegistrationService.submitApplication(appIndvListRequest.getAppNum());
        return ApiResponse.success(100).data(isSubmitted);
    }

    @PostMapping("/v1/conflict-applicants/appNum")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictApplicants(@RequestBody ApplicantsRequest applicantsRequestVO) throws IllegalAccessException {
        List<ApplicantsResponse>  arApplicantsResponseVO = applicationRegistrationService.fetchApplicantsInfo(applicantsRequestVO);
        List<ApplicantsResponse>  sspApplicantsResponseVO = ConflictPanelUtil.fetchARApplicants(applicantsRequestVO.getAppNum());
        List<Map<String,Map<String,Object>>> response=new ArrayList<>();
        for(ApplicantsResponse dc: arApplicantsResponseVO)
        {
            ApplicantsResponse ssp =sspApplicantsResponseVO.stream().
                    filter(temp -> temp.getSsn().equals(dc.getSsn())).
                    findAny().orElse(null);
            if (ssp != null) {
                response.add(ConflictPanelUtil.fetchConflicts(ssp,dc,"ApplicantsResponseVO"));
            }
        }
        return ApiResponse.success(100).data(response);
    }

    @PostMapping("/v1/conflict-contacts/appNum")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictContacts(@Valid @RequestBody AppContactDetailsResponse appContactDetailsVO) throws IllegalAccessException {
        ContactConflictsResponse sspResponse= ConflictPanelUtil.findSSPContactConflicts(appContactDetailsVO.getAppNum());
        ContactConflictsResponse dcResponse=new ContactConflictsResponse();

        List<Map<String,Map<String,Object>>> response=new ArrayList<>();

        WeekDayPreferenceConflictsResponse dcWeekDayPreferenceConflictsResponseVO =new WeekDayPreferenceConflictsResponse();
        dcWeekDayPreferenceConflictsResponseVO.setPanelName("WeekDay Preference");
        dcWeekDayPreferenceConflictsResponseVO.setWeekdayContMethSw(appContactDetailsVO.getArApplicationForAid().getWeekdayContMethSw());
        dcWeekDayPreferenceConflictsResponseVO.setWeekdayContTime(appContactDetailsVO.getArApplicationForAid().getWeekdayContTime());
        dcResponse.setWeekDayPreferenceConflictsResponse(dcWeekDayPreferenceConflictsResponseVO);

        if(sspResponse.getWeekDayPreferenceConflictsResponse()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getWeekDayPreferenceConflictsResponse(),dcResponse.getWeekDayPreferenceConflictsResponse(),"WeekDayPreference");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        PhoneConflictsResponse phoneConflictsResponseVO = ConflictPanelUtil.toResponseEntityPhone(appContactDetailsVO.getArPhoneList());
        phoneConflictsResponseVO.setPanelName("Phone Details");
        dcResponse.setPhoneConflictsResponse(phoneConflictsResponseVO);
        if(sspResponse.getPhoneConflictsResponse()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getPhoneConflictsResponse(),dcResponse.getPhoneConflictsResponse(),"DCPhoneConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        AddrConflictsResponse addrConflictsResponseVO = ConflictPanelUtil.toResponseEntityAddr(appContactDetailsVO.getArAppAddrPA(),appContactDetailsVO.getArAppAddrMA());
        dcResponse.setAddrConflictsResponse(addrConflictsResponseVO);
        if(sspResponse.getAddrConflictsResponse()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAddrConflictsResponse(),dcResponse.getAddrConflictsResponse(),"DCAddrConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        return ApiResponse.success(100).data(response);
    }

    @PostMapping("/v1/conflict-authRep/appNum")
    public ResponseEntity<ApiResponse<List<Map<String,Map<String,Object>>>>> fetchConflictAuthRep(@RequestBody AuthRepResponse authRepVO) throws IllegalAccessException {
        AuthRepConflictsResponse sspResponse= ConflictPanelUtil.findSSPAuthRepConflicts(authRepVO.getAppNum());
        AuthRepConflictsResponse dcResponse = new AuthRepConflictsResponse();

        List<Map<String,Map<String,Object>>> response=new ArrayList<>();

        AuthIndividualInformationResponse authIndividualInformationVO = new AuthIndividualInformationResponse();
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

        dcResponse.setAuthIndividualInformationResponse(authIndividualInformationVO);

        if(sspResponse.getAuthIndividualInformationResponse()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAuthIndividualInformationResponse(),dcResponse.getAuthIndividualInformationResponse(), "AuthIndividualInformation");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }


        AuthPhnDetailsResponse authPhnDetails = new AuthPhnDetailsResponse();
        authPhnDetails.setPanelName("Phone Details");
        authPhnDetails.setAppNum(authRepVO.getAppNum());
        ArPhnDetailsDto arPhnDetailsDto=authRepVO.getArPhoneList().stream().filter(i->i.getPhoneSrcTyp().equalsIgnoreCase("AA")).findFirst().orElse(null);
        if(null != arPhnDetailsDto) {
            authPhnDetails.setPhnNum(arPhnDetailsDto.getPhnNum());
        }

        if (authRepVO.getArAppAddrAA() != null) {
            authPhnDetails.setHasAuthRepSw('Y');
        } else {
            authPhnDetails.setHasAuthRepSw('N');
        }

        dcResponse.setAuthPhnDetailsResponse(authPhnDetails);

        if(sspResponse.getAuthPhnDetailsResponse()!=null)
        {
            Map<String,Map<String,Object>> output= ConflictPanelUtil.fetchConflicts(sspResponse.getAuthPhnDetailsResponse(), dcResponse.getAuthPhnDetailsResponse(),"AuthPhoneConflicts");
            if(!output.isEmpty())
            {
                response.add(output);
            }
        }

        return ApiResponse.success(100).data(response);
    }
}

