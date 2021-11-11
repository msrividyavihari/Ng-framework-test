package com.deloitte.nextgen.dto.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class DocumentManagementSearchHistoryDTO {

    private Long empId;
    private String caseApp;
    private String caseAppNum;
    private String clientId;
    private String ssn;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String beginDate;
    private String toDate;


    private String docId;
    private String documentType;
    private String reference;
    private String createDate;
    private Long disDocMstrSeqNum;
    private String transactionId;
    private String docType;
    private String entryDt;
    private String transactionNum;
    private Long coReqSeq;
    private Long caseNum;
    private String appNum;
    private Long indvId;
}
