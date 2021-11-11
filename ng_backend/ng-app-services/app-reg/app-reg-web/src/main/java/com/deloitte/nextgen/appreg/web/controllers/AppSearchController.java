package com.deloitte.nextgen.appreg.web.controllers;

import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.appreg.client.request.AppIndvListRequest;
import com.deloitte.nextgen.appreg.client.request.AppSearchRequest;
import com.deloitte.nextgen.appreg.client.response.AppSearchResponse;
import com.deloitte.nextgen.appreg.web.services.ApplicationSearchService;
import com.deloitte.nextgen.appreg.web.utils.Constants;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/maintain-application")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppSearchController {

    private final ApplicationSearchService appSearchService;

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/v1/applications")
    public ResponseEntity<ApiResponse<List<AppSearchResponse>>> findApplications(@Valid @RequestBody AppSearchRequest appSearchRequest) {
        log.debug(LogMarker.SECRET_LEVEL_ONE,"Input Values : appNum=" + appSearchRequest.getAppNum() + ",ssn=" + appSearchRequest.getApplicantSSN() +
                ",firstName=" + appSearchRequest.getApplicantFirstName() + ",lastName=" + appSearchRequest.getApplicantLastName());
        List<AppSearchResponse> appSearchResponses = appSearchService.findApplications(appSearchRequest);
        List<AppSearchResponse> sspResponse=findAppsFromSSP(appSearchRequest);
        appSearchResponses.addAll(sspResponse);
        updateSourceCode(appSearchResponses);
        return ApiResponse.success(100).data(appSearchResponses);
    }

    public List<AppSearchResponse> updateSourceCode(List<AppSearchResponse> input) {
        List<AppSearchResponse> output = input;
        for (AppSearchResponse temp : output) {
            if (temp.getSource() != null) {
                switch (temp.getSource()) {
                    case "SS":
                        temp.setSource("Customer Portal");
                        break;
                    case "DBS":
                        temp.setSource("Disaster SNAP");
                        break;
                    case "DTF":
                        temp.setSource("Disaster TANF");
                        break;
                    case "EXP":
                        temp.setSource("Ex Parte");
                        break;
                    case "FFM":
                        temp.setSource("Federally Facilitated Marketplace");
                        break;
                    case "INP":
                        temp.setSource("In Person");
                        break;
                    case "LIS":
                        temp.setSource("Low Income Subsidy");
                        break;
                    case "PAP":
                        temp.setSource("Paper");
                        break;
                    case "TP":
                        temp.setSource("Phone-In");
                        break;
                    case "SHI":
                        temp.setSource("Shines");
                        break;
                    default:
                        temp.setSource("");
                }
            }
        }
        return output;
    }

    public List<AppSearchResponse> findAppsFromSSP(@RequestBody AppSearchRequest appSearchRequest) {
        List<AppSearchResponse> al = null;
        try {
            AppSearchRequest request = appSearchRequest;
            ResponseEntity<AppSearchResponse[]> response =
                    restTemplate.postForEntity(Constants.fetchSSPAppNumbersURL, request, AppSearchResponse[].class);
            al = Arrays.asList(response.getBody());
        } catch (Exception e) {
            log.error("Error while finding Apps from SSP ", e);
            throw new AppRegCustomException(e.toString());
        }
        return al;
    }

    // DeleteMapping
    @PutMapping("/v1/application")
    public ResponseEntity<ApiResponse<String>> deleteApp(@RequestBody AppIndvListRequest appIndvListRequest) {
        log.debug("App Number to be withdrawn = " + appIndvListRequest.appNum);
        String status = appSearchService.deleteApp(appIndvListRequest.appNum);
        if (status.equalsIgnoreCase("SUCCESS")) {
            return ApiResponse.success(100).data(status);
        }
        return ApiResponse.error(300).data(status);
    }
}

