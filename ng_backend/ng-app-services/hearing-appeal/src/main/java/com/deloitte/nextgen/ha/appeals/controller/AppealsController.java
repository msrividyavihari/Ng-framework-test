package com.deloitte.nextgen.ha.appeals.controller;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.commons.payload.response.PagedData;
import com.deloitte.nextgen.ha.appeals.repository.AppealSearchRepository;
import com.deloitte.nextgen.ha.appeals.service.AppealSearchService;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchCriteria;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/appeals")
@Slf4j
@RequiredArgsConstructor
public class AppealsController {

    private final AppealSearchService appealSearchService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PagedData<AppealSearchResponseDto>>> findAppeals(
             AppealSearchCriteria criteria
            , @RequestParam(required = false, name = "filter") String filterExpression
            , @PageableDefault @SortDefault(AppealSearchRepository.DEFAULT_SORT_FIELD) Pageable pageable) {
        log.info("\n Criteria: {} \n Pageable: {} \n filter-expression: {}", criteria, pageable, filterExpression);
        Page<AppealSearchResponseDto> results = appealSearchService.findAppeals(criteria, filterExpression, pageable);
        if (results.getTotalElements() == 0) {
            return ApiResponse.with(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()).build();
        }
        return ApiResponse.pageable(results);
    }
}
