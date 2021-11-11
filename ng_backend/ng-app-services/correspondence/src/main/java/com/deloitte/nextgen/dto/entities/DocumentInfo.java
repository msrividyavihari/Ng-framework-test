package com.deloitte.nextgen.dto.entities;

import lombok.*;

@Data
@NoArgsConstructor
public class DocumentInfo {

    private String title;
    private String clientId;
    private String documentType;
    private String caseNum;
    private String applicationNum;
    private String clientName;
    private String comments;
    private byte[] fileArray;
}
