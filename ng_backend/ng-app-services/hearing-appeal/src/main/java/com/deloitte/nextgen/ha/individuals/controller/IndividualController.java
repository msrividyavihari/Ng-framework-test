package com.deloitte.nextgen.ha.individuals.controller;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.individuals.dto.IndividualAssociatedCasesResponseDto;

import com.deloitte.nextgen.ha.individuals.service.IndividualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/individuals")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualService;

    @GetMapping("/{individualId}/cases")
    public ResponseEntity<ApiResponse<IndividualAssociatedCasesResponseDto>> getIndividualAssociatedCases(@NonNull @PathVariable("individualId") Long individualId) {
        IndividualAssociatedCasesResponseDto responseDto = individualService.findAssociatedCases(individualId);
        return ApiResponse
                .success(100)
                .data(responseDto);
    }
}