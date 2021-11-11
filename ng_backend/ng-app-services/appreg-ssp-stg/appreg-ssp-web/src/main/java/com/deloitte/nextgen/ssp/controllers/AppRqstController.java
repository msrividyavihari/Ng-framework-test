package com.deloitte.nextgen.ssp.controllers;

import com.deloitte.nextgen.ssp.entities.entities.T1001_AppRqst;
import com.deloitte.nextgen.ssp.entities.T1001AppRqstDto;
import com.deloitte.nextgen.ssp.mappings.T1001AppRqstMapping;
import com.deloitte.nextgen.ssp.request.AppRequest;
import com.deloitte.nextgen.ssp.services.AppRqstSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ssp-staging-application")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppRqstController {

    @Autowired
    private AppRqstSSPService service;
    private final T1001AppRqstMapping t1002AppRqstMapping;

    @PostMapping("/v1/app-request/appNum")
    public ResponseEntity<T1001AppRqstDto> getSSPdetails(@Valid @RequestBody AppRequest appRequest){
        T1001_AppRqst appRqst=service.findByAppNum(appRequest.getAppNum());
        return  ResponseEntity.status(HttpStatus.OK).body(t1002AppRqstMapping.toDto(appRqst));
    }
}
