package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.CoViewHistorySearchDTO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoViewHistorySearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/viewHistory/")
public class ViewHistorySearchController {


    @Autowired
    CoViewHistorySearchService coViewHistorySearchService;


    @PostMapping("/searchHistory")
    public ResponseEntity<ApiResponse<List<ViewPendingVO>>> searchHistory(@RequestBody CoViewHistorySearchDTO coViewHistorySearchDTO) {
        List<ViewPendingVO> caseList = null;
        try{
            caseList = coViewHistorySearchService.fetchHistoryCO(coViewHistorySearchDTO);
        } catch(FwApplicationException e) {
            log.error(e.getMessage());
        }
        if(caseList != null)
            return ApiResponse.success(100).data(caseList);
        else
            return ApiResponse.error(103, "Invalid Details").notify(true).data(null);
    }

}
