package com.deloitte.nextgen.ha.search;


import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.search.dto.CaseSearchResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cases")
@Slf4j
@RequiredArgsConstructor
public class CaseSearchController {

    private final CaseSearchService caseSearchService;

    @GetMapping("/{caseNum}")
    public ResponseEntity<ApiResponse<CaseSearchResponseDto>> findByCaseNum(@PathVariable("caseNum") Long caseNum) {
        return ApiResponse
                .success(HttpStatus.OK.value())
                .data(caseSearchService.getCaseAndAppealDetails(caseNum));
    }

}