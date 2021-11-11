package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.CoViewHistorySearchDTO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;

import java.util.List;

public interface CoViewHistorySearchService {

    List<ViewPendingVO> fetchHistoryCO(CoViewHistorySearchDTO coViewHistorySearchDTO) throws FwApplicationException;


}
