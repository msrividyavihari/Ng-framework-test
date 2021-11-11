package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.CoMassMailingSummaryDTO;
import com.deloitte.nextgen.dto.entities.ViewHistoryDetailDTO;
import com.deloitte.nextgen.dto.vo.MassMailingSummaryVO;

import java.util.List;

public interface CoMassMailingService {

    List<MassMailingSummaryVO> fetchSummaryCO() throws Exception;
    MassMailingSummaryVO deleteMassMailing(CoMassMailingSummaryDTO coMassMailingSummaryDTO) throws Exception;
    MassMailingSummaryVO processCOMRO(CoMassMailingSummaryDTO coMassMailingSummaryDTO) throws Exception;
    String preview(CoMassMailingSummaryDTO coMassMailingSummaryDTO) throws Exception;
}
