package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.DocumentIdDTO;
import com.deloitte.nextgen.dto.entities.DocumentUpdateDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentLinkVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentHandlerService {
    ResponseEntity<String> uploadDocumentToDocuEdge(String title, String categoryId, String metaDataJson, MultipartFile file);
    DocumentDetailsVO getDocumentDetailsFromUpload(ResponseEntity<String> response) throws FwApplicationException;
    ResponseEntity<String> getDocumentsMetaDataResponse(DocumentIdDTO dto, String str);
    DocumentLinkVO getDocumentLinkVO(ResponseEntity<String> responseObj);
    DocumentDetailsVO getDocumentDetailsVO(ResponseEntity<String> responseObj) throws FwApplicationException;
    byte[] getDocumentStream(String documentId);
    ResponseEntity<String> updateDocument(DocumentUpdateDTO documentUpdateDTO) throws FwApplicationException;
}
