package com.deloitte.nextgen.ha.dashboard.controller;


import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.dashboard.entity.AppealFiledResolvedStatsEntity;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenActionStatsDto;
import com.deloitte.nextgen.ha.dashboard.dto.AppealOpenAgeStatsDto;
import com.deloitte.nextgen.ha.dashboard.service.AppealDashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/dashboard/appeals")
@Slf4j
@RequiredArgsConstructor
public class AppealDashboardController {

    private final AppealDashboardService dashboardService;

    @GetMapping({"/age/stats"})
    public ResponseEntity<ApiResponse<AppealOpenAgeStatsDto>> getOpenAppealsStats(){
        AppealOpenAgeStatsDto responseDto = dashboardService.getOpenAppealsStats();
        return ApiResponse.success(HttpStatus.OK.value()).data(responseDto);
    }

    @GetMapping("/action/stats")
    public ResponseEntity<ApiResponse<AppealOpenActionStatsDto>> getOpenAppealActionStats(){
        AppealOpenActionStatsDto responseDto = dashboardService.getOpenAppealActionStats();
        return ApiResponse.success(HttpStatus.OK.value()).data(responseDto);
    }

    @GetMapping("/filed-resolved/stats")
    public ResponseEntity<ApiResponse<List<AppealFiledResolvedStatsEntity>>>  getFiledResolvedAppealsStats(
                  @RequestParam(name="from", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> fromDate
                , @RequestParam(name="to", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Optional<LocalDate> endDate) {
        List<AppealFiledResolvedStatsEntity> responseDtoList = dashboardService.getFiledResolvedAppealsStats(fromDate, endDate);
        return ApiResponse.success(HttpStatus.OK.value()).data(responseDtoList);
    }
}