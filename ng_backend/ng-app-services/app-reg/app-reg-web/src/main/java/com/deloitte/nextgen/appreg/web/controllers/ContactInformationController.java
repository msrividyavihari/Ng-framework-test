package com.deloitte.nextgen.appreg.web.controllers;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.request.AppIndvListRequest;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.request.CaseLinkRequest;
import com.deloitte.nextgen.appreg.client.response.AppContactDetailsResponse;
import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import com.deloitte.nextgen.appreg.web.entities.ArEmailDetails;
import com.deloitte.nextgen.appreg.web.entities.ArPhnDetails;
import com.deloitte.nextgen.appreg.web.mappings.ArAppAddrMapping;
import com.deloitte.nextgen.appreg.web.mappings.ArEmailDetailsMapping;
import com.deloitte.nextgen.appreg.web.mappings.ArPhnDetailsMapping;
import com.deloitte.nextgen.appreg.web.services.AuthRepService;
import com.deloitte.nextgen.appreg.web.services.ContactInformationService;
import com.deloitte.nextgen.appreg.web.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class ContactInformationController {
    private final ContactInformationService contactInformationService;
    private final AuthRepService authRepService;
    private final ArPhnDetailsMapping arPhnDetailsMapping;
    private final ArEmailDetailsMapping arEmailDetailsMapping;
    private final ArAppAddrMapping arAppAddrMapping;

    @Autowired
    private RestTemplate restTemplate;

    @PutMapping("/v1/deassociate")
    public ResponseEntity<ApiResponse<String>> deleteAppDataForAppNum(@Valid @RequestBody AppIndvListRequest appIndvListRequest){
        //De-associate case
        //Delete Contact info
        String appNum = appIndvListRequest.getAppNum();
        contactInformationService.deleteArData(appNum);
        //Delete Auth rep data
        authRepService.deleteAuthRepData(appNum);

        return ApiResponse.noContent(100);
    }

    // Need to use only one path Variable per-request
    @PostMapping("/v1/contact-details/appNum/caseNum")
    public ResponseEntity<ApiResponse<AppContactDetailsResponse>> findContactDetailsByAppNum(@Valid @RequestBody CaseLinkRequest caseLinkRequest){
        log.info("Inside findContactDetailsForAppNum");
        String appNum = caseLinkRequest.getAppNum();
        Long caseNum = caseLinkRequest.getCaseNum();
        AppContactDetailsResponse appContactDetailsResponse = new AppContactDetailsResponse();

        ArApplicationForAidReqAndResp arApplicationForAidReqAndResp = contactInformationService.findAppDetails(appNum);

        appContactDetailsResponse.setAppNum(appNum);
        appContactDetailsResponse.setCaseNum(caseNum);
        appContactDetailsResponse.setArApplicationForAid(arApplicationForAidReqAndResp);

        //If caseNum association is done
        if (0 != defaultIfNull(caseNum,0L) /*caseNum != null && caseNum != 0*/) {
            log.info("Inside findContactDetailsForAppNum associated caseNum exists");
            //Get AR Phones
            List<ArPhnDetails> arPhoneDtoList= contactInformationService.findPhoneByAppNumAndPhoneSrcTyp(appNum,"PA");
            //Get AR Emails
            List<ArEmailDetails> arEmailDtoList= contactInformationService.findEmailByAppNumAndEmailSrcTyp(appNum,"PA");
            //Get AR Address for PA
            ArAppAddr arAppAddrPA = contactInformationService.findAppAddrDetailsAndAddrTypeCd(appNum,"PA");
            //Get AR Address for MA
            ArAppAddr arAppAddrMA = contactInformationService.findAppAddrDetailsAndAddrTypeCd(appNum,"MA");


            //If any AR data is present, return AR data
            if ( (CollectionUtils.isNotEmpty(arPhoneDtoList) /*arPhoneDtoList != null && !arPhoneDtoList.isEmpty()*/) ||
                 (CollectionUtils.isNotEmpty(arEmailDtoList) /*arEmailDtoList != null && !arEmailDtoList.isEmpty()*/) ||
                 (arAppAddrPA != null ) ||
                 (arAppAddrMA != null)
               )
            {
                log.info("AR data exists");
                appContactDetailsResponse.setArPhoneList(arPhnDetailsMapping.toDto(arPhoneDtoList));
                appContactDetailsResponse.setArEmailList(arEmailDetailsMapping.toDto(arEmailDtoList));
                appContactDetailsResponse.setArAppAddrPA(arAppAddrMapping.toDto(arAppAddrPA));
                appContactDetailsResponse.setArAppAddrMA(arAppAddrMapping.toDto(arAppAddrMA));
                appContactDetailsResponse.setSource("AR");

            }
            else {
                //If NO AR data show DC data
                log.info("AR data does not exist");

                //Check if DC phone exists
                List<ArPhnDetailsDto> dcPhoneDtoList = contactInformationService.findPhoneByCaseNumAndPhoneSrcTyp(appNum, caseNum, "PA");
                appContactDetailsResponse.setArPhoneList(dcPhoneDtoList);

                //Check if DC email exists
                List<ArEmailDetailsDto> dcEmailDtoList = contactInformationService.findEmailByCaseNumAndEmailSrcTyp(appNum, caseNum, "PA");
                appContactDetailsResponse.setArEmailList(dcEmailDtoList);

                //Check if DC Case Address exists
                ArAppAddrDto dcCaseAddrPADto = contactInformationService.findDcAddrByCaseNumAndAddrTypeCd(appNum, caseNum, "PA");
                appContactDetailsResponse.setArAppAddrPA(dcCaseAddrPADto);

                //Check if DC Case Address exists
                ArAppAddrDto dcCaseAddrMADto = contactInformationService.findDcAddrByCaseNumAndAddrTypeCd(appNum, caseNum, "MA");
                appContactDetailsResponse.setArAppAddrMA(dcCaseAddrMADto);
                appContactDetailsResponse.setSource("DC");

                //For SSP App if no DC then prefill the SSP Data
                if(dcPhoneDtoList.isEmpty() && dcEmailDtoList.isEmpty()
                && null == dcCaseAddrPADto.getAppNum() && null == dcCaseAddrMADto.getAppNum()
                        && arApplicationForAidReqAndResp.getAppModeCd() != null
                        && arApplicationForAidReqAndResp.getAppModeCd().equals("SS")
                ){
                    AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
                    appIndvListRequest.setAppNum(appNum);
                    ResponseEntity<AppContactDetailsResponse>responseEntity=
                            restTemplate.postForEntity(Constants.fetchSSPContactsURL, appIndvListRequest,AppContactDetailsResponse.class);
                    appContactDetailsResponse=responseEntity.getBody();
                }

            }

        //if No case association, get AR phone & email
        } else {
            log.info("Inside findContactDetailsForAppNum No associated caseNum exists");
            List<ArPhnDetails> arPhoneDtoList= contactInformationService.findPhoneByAppNumAndPhoneSrcTyp(appNum,"PA");
            List<ArEmailDetails> arEmailDtoList= contactInformationService.findEmailByAppNumAndEmailSrcTyp(appNum,"PA");
            ArAppAddr arAppAddrPA = contactInformationService.findAppAddrDetailsAndAddrTypeCd(appNum,"PA");
            ArAppAddr arAppAddrMA = contactInformationService.findAppAddrDetailsAndAddrTypeCd(appNum,"MA");

            appContactDetailsResponse.setArPhoneList(arPhnDetailsMapping.toDto(arPhoneDtoList));
            appContactDetailsResponse.setArEmailList(arEmailDetailsMapping.toDto(arEmailDtoList));
            appContactDetailsResponse.setArAppAddrPA(arAppAddrMapping.toDto(arAppAddrPA));
            appContactDetailsResponse.setArAppAddrMA(arAppAddrMapping.toDto(arAppAddrMA));
            appContactDetailsResponse.setSource("AR");

            if(arPhoneDtoList.isEmpty() && arEmailDtoList.isEmpty()
                    && null == arAppAddrPA && null == arAppAddrMA
                    && arApplicationForAidReqAndResp.getAppModeCd() != null && arApplicationForAidReqAndResp.getAppModeCd().equals("SS")
            ){
                AppIndvListRequest appIndvListRequest = new AppIndvListRequest();
                appIndvListRequest.setAppNum(appNum);
                ResponseEntity<AppContactDetailsResponse>responseEntity=
                        restTemplate.postForEntity(Constants.fetchSSPContactsURL,appIndvListRequest,AppContactDetailsResponse.class);
                appContactDetailsResponse=responseEntity.getBody();
            }
        }

        return ApiResponse.success(100).data(appContactDetailsResponse);
    }

    @PostMapping("/v1/contact-details")
    public ResponseEntity<ApiResponse<AppContactDetailsResponse>>  insertIntoContactDetails(@Valid @RequestBody AppContactDetailsResponse appContactDetailsResponse){

        log.info("Received from UI: {}", appContactDetailsResponse.toString());

        AppContactDetailsResponse appContactDetailsResponseFinal = contactInformationService.insertContactDetails(appContactDetailsResponse);
        return ApiResponse.success(100).data(appContactDetailsResponseFinal);
    }

}