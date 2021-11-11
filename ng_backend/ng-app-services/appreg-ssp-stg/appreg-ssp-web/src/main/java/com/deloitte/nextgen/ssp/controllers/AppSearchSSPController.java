package com.deloitte.nextgen.ssp.controllers;

import com.deloitte.nextgen.ssp.request.AppRequest;
import com.deloitte.nextgen.ssp.responses.AppSearchSSPResponse;
import com.deloitte.nextgen.ssp.request.AppSearchSSPRequest;
import com.deloitte.nextgen.ssp.services.ApplicationSearchSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/stg-appsearch")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppSearchSSPController
{
    @Autowired
    private ApplicationSearchSSPService appSearchSSPService;

    RestTemplate restTemplate=new RestTemplate();


    @PostMapping("/v1/applications/appNum")
    public ResponseEntity<List<AppSearchSSPResponse>> findApplications(
            @RequestBody AppSearchSSPRequest appSearchVO
    )  {
        log.debug("Input Values : " +
                "appNum="+appSearchVO.getAppNum() + "," +
                "ssn="+appSearchVO.getApplicantSSN() + "," +
                "firstName="+appSearchVO.getApplicantFirstName()+"," +
                "lastName="+appSearchVO.getApplicantLastName());
        List<AppSearchSSPResponse> appSearchResponseVOS = appSearchSSPService.findApplications(appSearchVO);
        return ResponseEntity.status(HttpStatus.OK).body(appSearchResponseVOS);
    }


}
