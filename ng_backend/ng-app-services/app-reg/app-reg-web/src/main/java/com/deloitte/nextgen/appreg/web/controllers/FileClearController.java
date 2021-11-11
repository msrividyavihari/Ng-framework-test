package com.deloitte.nextgen.appreg.web.controllers;

import com.deloitte.nextgen.appreg.client.request.DcIndvFileClearReqAndResp;
import com.deloitte.nextgen.appreg.client.request.FileClearSsnRequest;
import com.deloitte.nextgen.appreg.web.services.FileClearService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application-registration")
@RequiredArgsConstructor
@CrossOrigin
public class FileClearController {
    private final FileClearService fileClearService;

    @PostMapping("/v1/fc/ssn")
    public List<DcIndvFileClearReqAndResp> fileClearBySSN(@RequestBody FileClearSsnRequest fileClearSsnRequest){
        return "Y".equals(fileClearSsnRequest.getMpi())
                ? fileClearService.getFileClearFromMpi(fileClearSsnRequest.getSsn())
                : fileClearService.getFileClearRecords(fileClearSsnRequest.getSsn());
    }

    @PostMapping(value = {"/v1/fc/details","/v1/fc/details/{mpi}"})
    public List<DcIndvFileClearReqAndResp> fileClearByDetails(@PathVariable(name = "mpi",required = false) String mpi, @RequestBody DcIndvFileClearReqAndResp fileClear){
        return "Y".equals(mpi)
                ? fileClearService.getFileClearFromMpi(fileClear)
                : fileClearService.getFileClearRecords(fileClear);
    }
}
