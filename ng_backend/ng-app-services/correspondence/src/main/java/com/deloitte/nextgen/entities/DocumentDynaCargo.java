package com.deloitte.nextgen.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class DocumentDynaCargo {
    private String dId;
    private String caseAppNum;
    private String personId;
    private String docType;
    private String docName;
    private String recvdDate;
    private String clientName;
    private Character caseAppFlag;
    private String comments;
    private String docTypeDesc;
    private byte[] fileData;
    private Long docId;
    private String docRecvdDate;
}
