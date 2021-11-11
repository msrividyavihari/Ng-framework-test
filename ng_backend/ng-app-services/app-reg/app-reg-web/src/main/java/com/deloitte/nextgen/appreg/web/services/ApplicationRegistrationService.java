package com.deloitte.nextgen.appreg.web.services;


import com.deloitte.nextgen.appreg.client.entities.ArExpScreenRespDto;
import com.deloitte.nextgen.appreg.client.request.*;
import com.deloitte.nextgen.appreg.client.response.*;

import java.util.List;

public interface ApplicationRegistrationService {

    ArApplicationForAidReqAndResp associateCase(String appNum, Long caseNum);
    List<AssociateCaseResponse> fetchAssociateCases(Long indvId, String appNum);
    public String validateAddress(String street1,String street2,String city,String state,String zipCode);
    ArApplicationForAidReqAndResp insertIntoArAppFrAid(ArApplicationForAidReqAndResp arApplicationForAidReqAndResp);
    ArAppIndvReqAndResp insertArAppIndv(ArAppIndvReqAndResp arAppIndvReqAndResp);
    ArApplicationForAidReqAndResp findByAppNum(String appNum);
    List<ApplicantsResponse> fetchApplicantsInfo(ApplicantsRequest applicantsRequestVO);
    List<ApplicantsResponse> fetchApplInfoPanelDetails(String appNum);
    List<ApplicantsResponse> insertUpdateApplicants(String appNum, List<ApplicantRequest> applicants);
    List<ArExpScreenRespDto> insertArExpResp(List<ArExpScreenRespDto> arExpScreenResponseList, String appNum);
    List<ArExpScreenRespDto> fetchExpInfo(String appNUm);
    ArApplicationForAidReqAndResp insertPriorityAppStatus(PriorityStatusRequest priorityStatusRequest);
   List<ArAppProgramResponse> insertArAppProgram(ArAppProgramRequest arAppProgramRequest);
    List<ArAppProgramResponse> fetchArAppProgramByAppNum(String appNum);
    List<ArAppProgramIndvResponse> insertArAppProgramIndv(List<ArAppProgramIndvRequest> arAppProgramIndvRequest);
    List<AppIndvListResponse> fetchAppIndv(AppIndvListRequest appIndvListRequest);
    List<ArAppProgramIndvResponse> findArAppProgramIndvByAppNum(String appNum);
    Integer fetchProgressForApp(String appNum);
    Boolean submitApplication(String appNum);
}
