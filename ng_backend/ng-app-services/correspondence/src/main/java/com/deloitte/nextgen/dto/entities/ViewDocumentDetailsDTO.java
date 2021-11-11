package com.deloitte.nextgen.dto.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class ViewDocumentDetailsDTO {
    private Long disDocMstrSeqNum;
    private Long referenceNo;
    private String caseAppNum;
    private String modifiedDocumentType;
    private String originalDocumentType;
    private Long indvId;
    private Long originalSsn;
    private Long modifiedSsn;
    private String docuedgeDocumentId;
    private String transactionNumber;
    private String documentDescription;
    private String actionType;
    private Long clientId;
    private String userId;
    private String caseNo;
    private String application;
    private String pageId;
    private String documentTypeCd;
}
