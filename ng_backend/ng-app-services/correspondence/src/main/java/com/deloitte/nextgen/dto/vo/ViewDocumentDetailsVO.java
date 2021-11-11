package com.deloitte.nextgen.dto.vo;

import com.deloitte.nextgen.entities.DocumentDynaCargo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ViewDocumentDetailsVO {
    private String originalDocumentType;
    private Long disDocMstrSeqNo;
    private String caseAppNum;
    private Long ssn;
    private ArrayList<String> messageCodes;
    private ArrayList<DocumentDynaCargo> documentDynaCargos;
    private Long documentId;
    private String documentType;
    private String documentTypeCd;
    private String pageId;
    List<ClientsVO> clients;
}
