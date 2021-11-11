package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.CoCaseSearchDTO;
import com.deloitte.nextgen.dto.vo.CoCaseSearchVO;
import com.deloitte.nextgen.dto.vo.CoMasterVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoCaseSearchService;
import com.deloitte.nextgen.util.CoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/correspondence/generateManual/")
public class CoCaseSearchController {


    @Autowired
    CoCaseSearchService service;

    @PostMapping("/caseSearch")
    public ResponseEntity<ApiResponse<List<CoCaseSearchVO>>> fetchForPendingCO(@RequestBody CoCaseSearchDTO caseSearch) throws FwApplicationException {
        String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        if(caseSearch != null) {
            List<CoCaseSearchVO> response = service.fetchByCaseNum(caseSearch.getCaseNum(), curDt);
            return ApiResponse.success(100).data(response);
        }
        return null;
    }

    @GetMapping("/getCOMaster")
    public ResponseEntity<ApiResponse<List<CoMasterVO>>> fetchCOMaster() {
        List<CoMasterVO> coMasterList = service.fetchCOMaster();
        if(coMasterList != null) {
            return ApiResponse.success(100).data(coMasterList);
        }
        return null;
    }

    @GetMapping("/getCOMasterMap")
    public ResponseEntity<ApiResponse<Map<String, String>>> fetchCOMasterMap() throws FwApplicationException {
        String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
        Map<String, String> coMasterList = service.fetchCOMasterMap();
        if(coMasterList != null) {
            return ApiResponse.success(100).data(coMasterList);
        }
        return null;
    }

}
