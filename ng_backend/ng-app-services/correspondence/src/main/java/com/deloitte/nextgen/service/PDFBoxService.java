package com.deloitte.nextgen.service;


import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.dto.entities.ClientCommunicationDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author rahmaurya on 02/12/2020 09:51 PM
 * @project correspondence
 */
public interface PDFBoxService {
    byte[] downloadGeneralCorrespondencePDF (String xmlFile);
    void validateRequest(BatchNotice dto) throws Exception;
    String getToken() throws Exception;
    ResponseEntity<byte[]> getDocument(BatchNotice dto) throws Exception;
    ClientCommunicationDTO sendEmailOrText(BatchNotice batchNotice) throws Exception;

}
