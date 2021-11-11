package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.*;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;

import java.util.List;

public interface CoViewPendingService {
    String viewPendingDetailService(Long coReqSeq, String docId, String previewVal, CoGenerateManualDTO generateManualDTO) throws Exception;
    List<ViewPendingVO> fetchPendingCO(ViewPendingDTO pendingDTO, String curDt);
    List<ViewPendingDTO> suppressUnsuppress(ViewPendingDTO pendingDTO) throws Exception;
    List<CoManualDataDTO> fetchManualData(Long coReqSeq) throws Exception;
    String centralPrint(ViewPendingDTO pendingDTO);
    NoticeCustomer getNoticeCustomer(ViewPendingDTO dto);
    BatchNotice getBatchNotice(ViewPendingDTO dto) throws FwApplicationException;
    DocumentDetailsVO uploadFileToEDMS(byte[] byteArray);
    void insertDocumentDetails(DocumentDetailsVO dvo) throws FwApplicationException;
}
