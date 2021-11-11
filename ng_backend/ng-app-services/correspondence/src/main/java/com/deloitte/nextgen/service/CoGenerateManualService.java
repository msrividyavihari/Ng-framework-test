package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.dto.entities.CoGenerateManualDTO;
import com.deloitte.nextgen.dto.entities.NoticeCustomer;

import java.util.List;

public interface CoGenerateManualService {

    List<CoGenerateManualDTO> saveManualCorrDetails(CoGenerateManualDTO generateManualReqDTO) throws Exception;
    String manualCorr(CoGenerateManualDTO generateManualReqDTO) throws Exception;
    CoGenerateManualDTO initialize(CoGenerateManualDTO generateManualReqDTO) throws Exception;
    NoticeCustomer getNoticeRequest(CoGenerateManualDTO dto);
    BatchNotice getBatchNotice(CoGenerateManualDTO dto) throws Exception;
}
