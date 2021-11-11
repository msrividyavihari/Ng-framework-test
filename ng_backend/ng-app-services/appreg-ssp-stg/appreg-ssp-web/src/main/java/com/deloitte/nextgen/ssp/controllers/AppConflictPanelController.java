package com.deloitte.nextgen.ssp.controllers;

import com.deloitte.nextgen.ssp.request.AppRequest;
import com.deloitte.nextgen.ssp.responses.*;
import com.deloitte.nextgen.ssp.services.AppConflictReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/conflictPanel")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppConflictPanelController {

    @Autowired
    private AppConflictReviewService service;
    RestTemplate restTemplate=new RestTemplate();

    @PostMapping("/v1/Applicants/appNum")
    public ResponseEntity<List<ApplicantsResponse>> findApps(@RequestBody AppRequest appRequest)
    {
        List<ApplicantsResponse> response= service.findByAppNum(appRequest.getAppNum());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/v1/contact-conflicts/appNum")
    public ResponseEntity<ContactConflictsResponse> findContactDtl(@RequestBody AppRequest appRequest)
    {
        ContactConflictsResponse response= service.findContactDtl(appRequest.getAppNum());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/v1/Contacts/appNum")
    public ResponseEntity<AppContactDetailsResponse> fetchContactFromSSP(@RequestBody AppRequest appRequest)
    {
        AppContactDetailsResponse appContactDetailsVO= service.fetchContactFromSSP(appRequest.getAppNum());
        appContactDetailsVO.setSource("SSP");
        return ResponseEntity.status(HttpStatus.OK).body(appContactDetailsVO);
    }

    @PostMapping("/v1/authRep-conflicts/appNum")
    public ResponseEntity<AuthRepConflictsResponse> findAuthRepDtl(@RequestBody AppRequest appRequest)
    {
        AuthRepConflictsResponse response= service.findAuthRepDtl(appRequest.getAppNum());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/v1/authRep-details/appNum")
    public ResponseEntity<AuthRepResponse>findAuthRepSSP(@RequestBody AppRequest appRequest)
    {
        AuthRepResponse response=service.findAuthRepSSP(appRequest.getAppNum());
        response.setSource("SSP");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
