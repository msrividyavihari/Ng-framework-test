package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.vo.CoCaseSearchVO;
import com.deloitte.nextgen.dto.vo.CoMasterVO;

import java.util.List;
import java.util.Map;

public interface CoCaseSearchService {
    List<CoCaseSearchVO> fetchByCaseNum(Long caseNum, String curDt);

    List<CoMasterVO> fetchCOMaster();

    Map<String, String> fetchCOMasterMap();
}
