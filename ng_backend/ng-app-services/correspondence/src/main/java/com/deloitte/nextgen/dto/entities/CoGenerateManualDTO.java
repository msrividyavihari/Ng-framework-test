package com.deloitte.nextgen.dto.entities;

import com.deloitte.nextgen.dto.vo.CoManualDataVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Map;

@Data
@NoArgsConstructor
public class CoGenerateManualDTO {

    private String templateId;
    private String FormNo;
    private String formVersion;
    private Long caseNum;
    private String documentId;
    private Timestamp noticeDate;
    private Long reciepientSeqNum;

    private String action;
    private String fullName;
    private Timestamp mailDate;
    private Character watermark;
    private Character securityFlag;
    private String preferredLanguage;
    private Character goGreen;
    private String agencyCode;
    private String agencyName;
    private String SSN;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp clientDOB;
    private String formTitle;
    private MassHealthMedicaid MASSHealthMedicaid;

    private MailingAdd mailingAdd;

    private String docId;
    private Long indivId;
    private Long coReqSeq;
    private String AppealId;
    private String pageId;
    private boolean response;
    private String preview;
    private String parentPageId;
    private String caseAppNumber;
    private Map<String, CoManualFieldsDTO[]> coManualFieldsMap;
    private Map<String, FwDataElementListDTO[]> fwDataElementListMap;
    private Map<String, String> request;
    private CoManualDataVO coManualData;



//    private MailingAdd mailingAdd;
}
