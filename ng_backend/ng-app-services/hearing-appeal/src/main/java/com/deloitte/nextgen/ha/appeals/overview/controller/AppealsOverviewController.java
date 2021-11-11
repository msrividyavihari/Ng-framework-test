package com.deloitte.nextgen.ha.appeals.overview.controller;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.appeals.overview.dto.AppealEditStatusRequestDto;
import com.deloitte.nextgen.ha.appeals.overview.dto.AppealOverviewRequestDto;
import com.deloitte.nextgen.ha.appeals.overview.dto.AppealOverviewResponseDto;
import com.deloitte.nextgen.ha.appeals.overview.dto.AppealTimelineResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appeals")
@Slf4j
@RequiredArgsConstructor
public class AppealsOverviewController {

    @GetMapping("/{appealNum}/overview")
    public ResponseEntity<ApiResponse<AppealOverviewResponseDto>> getApealOverview(@PathVariable("appealNumOrGroupNum") long appealNumOrGroupNum){
        return ApiResponse.noContent(100);
    }

    @GetMapping("/{appealNum}/overview/timeline")
    public ResponseEntity<ApiResponse<List<AppealTimelineResponseDto>>> getApealTimeline(@PathVariable("appealNumOrGroupNum") long appealNumOrGroupNum, AppealOverviewRequestDto overviewRequest){
        return ApiResponse.noContent(100);
    }

    @PutMapping("/{appealNum}/status")
    public ResponseEntity<ApiResponse<List<AppealTimelineResponseDto>>> editStatus(@PathVariable("appealNumOrGroupNum") long appealNumOrGroupNum, @RequestBody AppealEditStatusRequestDto overviewRequest){
        return ApiResponse.noContent(100);
    }
}
