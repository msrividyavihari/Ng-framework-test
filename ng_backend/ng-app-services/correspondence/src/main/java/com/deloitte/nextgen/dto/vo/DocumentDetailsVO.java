package com.deloitte.nextgen.dto.vo;

import lombok.*;

@Data
@NoArgsConstructor
public class DocumentDetailsVO {

    private String documentId;
    private String documentName;
    private String documentVersionFileName;
    private long categoryId;
    private String categoryName;
    private double size;
    private String type;
    private String fileName;
    private String lastModifiedDate;
    private long clientId;
    private long caseNumber;
    private String documentType;
    private long referenceNumber;
    private long applicationNumber;
    private String transactionNumber;
    private java.sql.Timestamp documentReceivedDateBeginDate;
    private java.sql.Timestamp documentReceivedDateEndDate;
    private String tags;

}
