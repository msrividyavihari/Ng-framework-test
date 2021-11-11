package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.DocumentInfoCollection;
import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentManagementSearchHistoryVO;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface DocumentManagementService {
    ArrayList<DocumentDetailsVO> uploadDocument(DocumentInfoCollection documentInfoCollection) throws FwApplicationException;
    List<InDisDocMaster> searchDocuments(DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws ParseException, FwApplicationException;
    List<DocumentManagementSearchHistoryVO> processInDisDocMaster(List<InDisDocMaster> inDisDocMasterList);
}
