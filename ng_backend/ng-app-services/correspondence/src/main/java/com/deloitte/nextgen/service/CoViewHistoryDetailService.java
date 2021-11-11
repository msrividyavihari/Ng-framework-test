package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.ViewHistoryDetailDTO;
import com.deloitte.nextgen.dto.vo.ViewHistoryDetailVO;

public interface CoViewHistoryDetailService {
    ViewHistoryDetailVO retrieveHistoryDetails(ViewHistoryDetailDTO viewHistoryDetailDTO);
    byte[] generate(ViewHistoryDetailDTO viewHistoryDetailDTO) throws Exception;
    byte[] viewHistoryDetailService(String toString);
}
