package com.deloitte.nextgen.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@NoArgsConstructor
public class DocumentManagementSearchHistoryVO {

    private String docuedgeDocumentId;
    private Long disDocMstrSeqNum;
    private String transactionId;
    private Long docId;
    private String docType;
    private Character docUploadType;
    @JsonFormat(pattern = "MM/dd/YY")
    private java.sql.Timestamp entryDt;
    private Character processFlag;
    private Long caseNum;
    private Long indvId;
    private String appNum;
    private Long indvSeqNum;
    private Long taskNum;
    private Character cpHistoryFlag;
    private Character delinkInd;
    private Character disUpdInd;
    private String source;
    private String program;
    private Long coReqSeq;
    private String filePath;
    private String commentCd;
    @JsonFormat(pattern = "MM/dd/YY")
    private java.sql.Timestamp dateOfReceipt;
    private String fullName;
    private Long age;
    private Character gender;
    private Long uniqueTransId;
}
