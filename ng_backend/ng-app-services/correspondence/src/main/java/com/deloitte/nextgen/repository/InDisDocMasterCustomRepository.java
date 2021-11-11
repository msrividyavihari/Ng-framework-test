package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;

import java.util.List;

public interface InDisDocMasterCustomRepository {
    List<InDisDocMaster> findByDisQuickSearch(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO, boolean isShinesCcCase, boolean isIndvSearch, String indvIds, boolean isCcCaseApp);
    List<InDisDocMaster> findByIndividualDetails(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws FwApplicationException;
    List<InDisDocMaster> findDuplicate(InDisDocMaster inDisDocMasterCargo);
}
