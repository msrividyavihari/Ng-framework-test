package com.deloitte.nextgen.appreg.web.controllers;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.request.AppIndvListRequest;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.request.CaseLinkRequest;
import com.deloitte.nextgen.appreg.client.response.AuthRepResponse;
import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import com.deloitte.nextgen.appreg.web.entities.ArEmailDetails;
import com.deloitte.nextgen.appreg.web.entities.ArPhnDetails;
import com.deloitte.nextgen.appreg.web.mappings.ArAppAddrMapping;
import com.deloitte.nextgen.appreg.web.mappings.ArEmailDetailsMapping;
import com.deloitte.nextgen.appreg.web.mappings.ArPhnDetailsMapping;
import com.deloitte.nextgen.appreg.web.services.AuthRepService;
import com.deloitte.nextgen.appreg.web.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;


@RestController
@RequestMapping("/application-registration")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AuthRepController {
    private final AuthRepService authRepService;
    private final ArPhnDetailsMapping arPhnDetailsMapping;
    private final ArEmailDetailsMapping arEmailDetailsMapping;
    private final ArAppAddrMapping arAppAddrMapping;
    @Autowired
    private RestTemplate restTemplate;

    // Need to use only one path variable per-request
    @PostMapping("/v1/authrep-details/appNum/caseNum")
    @CrossOrigin
    public ResponseEntity<ApiResponse<AuthRepResponse>> findAuthRepDetailsForAppNum(@Valid @RequestBody CaseLinkRequest caseLinkRequest)
    {
        log.info("Inside findAuthRepDetailsForAppNum");

        String appNum = caseLinkRequest.getAppNum();
        Long caseNum = caseLinkRequest.getCaseNum();
        AuthRepResponse authRepResponse = new AuthRepResponse();

        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = authRepService.findAppDetails(appNum);
       // arApplicationForAidDto.setAuthRepSw('N');   //Initial value

        //If caseNum association is done
        if (0 != defaultIfNull(caseNum, 0L) /*caseNum != null && caseNum != 0*/) {
            log.info("Inside findAuthRepDetailsForAppNum associated caseNum exists");

            //Get Auth Rep Phones
            List<ArPhnDetails> arPhoneDtoList = authRepService.findPhoneByAppNumAndPhoneSrcTyp(appNum, "AA");
            //Get Auth Rep Emails
            List<ArEmailDetails> arEmailDtoList = authRepService.findEmailByAppNumAndEmailSrcTyp(appNum, "AA");

            //Get Auth Rep Address for AA
            ArAppAddr arAppAddrAA = authRepService.findAppAddrDetailsAndAddrTypeCd(appNum, "AA");


            //If any AR data is present, return AR data
            if ( (CollectionUtils.isNotEmpty( arPhoneDtoList) /*||arPhoneDtoList != null && !arPhoneDtoList.isEmpty()*/) ||
                    (CollectionUtils.isNotEmpty( arEmailDtoList) /*|| arEmailDtoList != null && !arEmailDtoList.isEmpty()*/) ||
                    (arAppAddrAA != null) || (arApplicationForAidReqAndResp !=null && arApplicationForAidReqAndResp.getAuthrepFirstName() != null)
            ) {
                log.info("AR data exists");

                authRepResponse.setArPhoneList(arPhnDetailsMapping.toDto(arPhoneDtoList));
                authRepResponse.setArEmailList(arEmailDetailsMapping.toDto(arEmailDtoList));
                authRepResponse.setArAppAddrAA(arAppAddrMapping.toDto(arAppAddrAA));
                //arApplicationForAidDto.setAuthRepSw('Y');
                authRepResponse.setSource("AR");
            }
            else {
                //If NO AR data show DC data
                log.info("AR data does not exist");

                //Get DcAuthRep data
                ArApplicationForAidReqAndResp dcAuthRepDto = authRepService.findDcAuthRepForCase(appNum,caseNum);

                if (dcAuthRepDto != null && null != arApplicationForAidReqAndResp) {
                    //Update data in original AR_APPLICATION_FOR_AID Dto
                    arApplicationForAidReqAndResp.setAuthrepFirstName(dcAuthRepDto.getAuthrepFirstName());
                    arApplicationForAidReqAndResp.setAuthrepMidName(dcAuthRepDto.getAuthrepMidName());
                    arApplicationForAidReqAndResp.setAuthrepLastName(dcAuthRepDto.getAuthrepLastName());
                    arApplicationForAidReqAndResp.setAuthrepSufxName(dcAuthRepDto.getAuthrepSufxName());
                    arApplicationForAidReqAndResp.setRelCd(dcAuthRepDto.getRelCd());
                }

                //Check if DC phone exists
                List<ArPhnDetailsDto> dcPhoneDtoList = authRepService.findPhoneByCaseNumAndPhoneSrcTyp(appNum, caseNum, "AA");
                authRepResponse.setArPhoneList(dcPhoneDtoList);

                //Check if DC email exists
                List<ArEmailDetailsDto> dcEmailDtoList = authRepService.findEmailByCaseNumAndEmailSrcTyp(appNum, caseNum, "AA");
                authRepResponse.setArEmailList(dcEmailDtoList);

                //Check if DC Case Address exists
                ArAppAddrDto dcCaseAddrAADto = authRepService.findDcAddrByCaseNumAndAddrTypeCd(appNum, caseNum, "AA");
                authRepResponse.setArAppAddrAA(dcCaseAddrAADto);
                if((!dcPhoneDtoList.isEmpty()||(dcCaseAddrAADto!=null && dcCaseAddrAADto.getAppNum()!=null)) && null != arApplicationForAidReqAndResp)
                {
                    arApplicationForAidReqAndResp.setAuthRepSw(Active.YES);
                }
                authRepResponse.setSource("DC");

                //For SSP App if no DC then prefill the SSP Data
                if(dcPhoneDtoList.isEmpty()
                && dcCaseAddrAADto!=null
                && dcCaseAddrAADto.getAppNum()==null
                &&arApplicationForAidReqAndResp!=null
                && arApplicationForAidReqAndResp.getAppModeCd().equalsIgnoreCase("SS"))
                {
                    AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
                    appIndvListRequest.setAppNum(appNum);
                    ResponseEntity<AuthRepResponse>responseEntity=
                            restTemplate.postForEntity(Constants.fetchSSPAuthRepURL,appIndvListRequest,AuthRepResponse.class);
                    authRepResponse=responseEntity.getBody();
                    arApplicationForAidReqAndResp = authRepResponse.getArApplicationForAid();
                }
            }
        //if No case association, get AR phone & email
    } else {
        log.info("Inside findContactDetailsForAppNum No associated caseNum exists");
        List<ArPhnDetails> arPhoneDtoList= authRepService.findPhoneByAppNumAndPhoneSrcTyp(appNum,"AA");
        List<ArEmailDetails> arEmailDtoList= authRepService.findEmailByAppNumAndEmailSrcTyp(appNum,"AA");
        ArAppAddr arAppAddrAA = authRepService.findAppAddrDetailsAndAddrTypeCd(appNum,"AA");

            authRepResponse.setArPhoneList(arPhnDetailsMapping.toDto(arPhoneDtoList));
            authRepResponse.setArEmailList(arEmailDetailsMapping.toDto(arEmailDtoList));
            authRepResponse.setArAppAddrAA(arAppAddrMapping.toDto(arAppAddrAA));
            authRepResponse.setSource("AR");
        if ( (CollectionUtils.isNotEmpty(arPhoneDtoList) /*arPhoneDtoList != null && !arPhoneDtoList.isEmpty()*/) ||
        (CollectionUtils.isNotEmpty(arEmailDtoList) /*arEmailDtoList != null && !arEmailDtoList.isEmpty()*/) ||
        (arAppAddrAA != null) || (arApplicationForAidReqAndResp !=null && arApplicationForAidReqAndResp.getAuthrepFirstName() != null)
       ){
                log.info("AR data exists and No case associated");
            arApplicationForAidReqAndResp.setAuthRepSw(Active.YES);}


        if(arPhoneDtoList.isEmpty() && arEmailDtoList.isEmpty() && null == arAppAddrAA
                && arApplicationForAidReqAndResp != null && arApplicationForAidReqAndResp.getAppModeCd() != null
                && arApplicationForAidReqAndResp.getAppModeCd().equals("SS")
            ){

            AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
            appIndvListRequest.setAppNum(appNum);
                ResponseEntity<AuthRepResponse>responseEntity=
                        restTemplate.postForEntity(Constants.fetchSSPAuthRepURL,appIndvListRequest,AuthRepResponse.class);
                authRepResponse=responseEntity.getBody();
            arApplicationForAidReqAndResp=authRepResponse.getArApplicationForAid();
            }
    }

    //Check for AR Application For Aid or DC Auth rep data
    if (arApplicationForAidReqAndResp!=null &&(arApplicationForAidReqAndResp.getAuthrepFirstName() != null ||
            arApplicationForAidReqAndResp.getAuthrepLastName() != null ||
            arApplicationForAidReqAndResp.getAuthrepMidName() != null ||
            arApplicationForAidReqAndResp.getAuthrepSufxName() != null ||
            arApplicationForAidReqAndResp.getRelCd() != null)
    ) {
        arApplicationForAidReqAndResp.setAuthRepSw(Active.YES);
    }

    if(null != authRepResponse) {
        authRepResponse.setAppNum(appNum);
        authRepResponse.setArApplicationForAid(arApplicationForAidReqAndResp);
    }

    return ApiResponse.success(100).data(authRepResponse);
    }

    @PostMapping("/v1/authRep-details")
    public ResponseEntity<ApiResponse<AuthRepResponse>>  insertIntoAuthRep(@Valid @RequestBody AuthRepResponse authRepResponse){

        log.info("Received from UI: {}", authRepResponse.toString());

        AuthRepResponse authRepResponseFinal = authRepService.insertAuthRepDetails(authRepResponse);
        return ApiResponse.success(100).data(authRepResponseFinal);
    }

    // need DeleteMapping
    @DeleteMapping("/v1/authRep-details/appNum")
    public ResponseEntity<ApiResponse<String>> deleteAuthRepDataForAppNum(@RequestBody AppIndvListRequest appIndvListRequest){
        authRepService.deleteAuthRepData(appIndvListRequest.getAppNum());
        return ApiResponse.noContent(100);
    }

}