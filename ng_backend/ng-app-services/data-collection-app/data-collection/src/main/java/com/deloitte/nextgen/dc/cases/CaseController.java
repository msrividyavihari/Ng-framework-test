package com.deloitte.nextgen.dc.cases;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.authrep.service.AuthRepService;
import com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cases")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class CaseController {

    private final CaseSearchService caseSearchService;
    private final AuthRepService authRepService;

    @GetMapping("/{caseNum}")
    public ResponseEntity<ApiResponse<CaseSearchResponseDto>> findByCaseNum(@PathVariable("caseNum") Long caseNum){
       CaseSearchResponseDto caseSearchResponseDto = caseSearchService.findByCaseNum(caseNum);
        return ApiResponse
                    .success(100)
                    .data(caseSearchResponseDto);
    }

    @GetMapping("/{caseNum}/auth-rep")
    public ResponseEntity<ApiResponse<AuthRepDto>> getAuthRep(@PathVariable("caseNum") Long caseNum){
        AuthRepDto authRepDto = authRepService.getAuthRepByCaseNum(caseNum);
        return ApiResponse
                .success(100)
                .data(authRepDto);
    }
}
