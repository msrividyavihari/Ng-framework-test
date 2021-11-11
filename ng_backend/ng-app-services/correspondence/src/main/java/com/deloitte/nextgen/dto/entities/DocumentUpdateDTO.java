package com.deloitte.nextgen.dto.entities;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class DocumentUpdateDTO {
    private Long caseNum;
    // TODO: have to change the appNum type to String and respective code related to it, once docuEdge starts supporting appNum as alphanumeric
    private Long appNum;
    private String docuedgeDocumentId;
    private Long clientId;
    private Long referenceNumber;
    private String transactionNumber;
    private String documentDescription;
    private Timestamp documentReceivedBeginDate;
    private Timestamp documentReceivedEndDate;
}
