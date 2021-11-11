package com.deloitte.nextgen.ssp.controllers;

import com.deloitte.nextgen.ssp.entities.entities.T1053_App_Program;
import com.deloitte.nextgen.ssp.entities.vo.SSPDataRequest;
import com.deloitte.nextgen.ssp.entities.T1053AppProgramDto;
import com.deloitte.nextgen.ssp.mappings.T1053AppProgramMapping;
import com.deloitte.nextgen.ssp.request.AppRequest;
import com.deloitte.nextgen.ssp.responses.SnapExpeditedResponse;
import com.deloitte.nextgen.ssp.services.AppregSSPservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.util.Objects.isNull;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/program")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppregSSPController {

    @Autowired
    private AppregSSPservice service;
    private final T1053AppProgramMapping t1053AppProgramMapping;

    @PostMapping("/v1/app-programs/appNum")
    public ResponseEntity<List<T1053AppProgramDto>> getSSPdetails(@RequestBody AppRequest appRequest) {
        List<T1053_App_Program> appProgram = service.findByAppNum(appRequest.getAppNum());
        return !isNull(appProgram) ?
                ResponseEntity.status(HttpStatus.OK).body(t1053AppProgramMapping.toDto(appProgram)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(t1053AppProgramMapping.toDto(appProgram));
    }

    @PostMapping("/v1/snap-expedited-details/appNum")
    public ResponseEntity<SnapExpeditedResponse> getSnapExpDetails(@RequestBody AppRequest appRequest){
        SnapExpeditedResponse snapExpeditedVO = service.getSnapExpDetails(appRequest.getAppNum());
        return ResponseEntity.status(HttpStatus.OK).body(snapExpeditedVO);

    }


    @PostMapping("/insertSSP")
    public ResponseEntity<List<Map<String, Map<String, Map<String, Object>>>>> insertSSPData(@RequestBody List<SSPDataRequest> sspDataRequest) {
        List<Map<String, Map<String, Map<String, Object>>>> insert = service.insertSSPData(sspDataRequest);

        if (null != insert) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(insert);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(insert);
        }
    }
}