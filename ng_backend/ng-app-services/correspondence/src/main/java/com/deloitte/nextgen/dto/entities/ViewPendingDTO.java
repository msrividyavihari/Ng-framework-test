package com.deloitte.nextgen.dto.entities;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewPendingDTO {

    private String searchCriteria;
    private Long caseNum;
    private String appNum;
    private Long clientId;
    private String workerName;
    private Long workerId;
    private Timestamp ccCertStartDt;
    private Timestamp ccCertEndDt;
    private Long coReqSeq;
    private String docId;
    private String ccProviderId;
    private String previewVal;
    private List messageCode;
    private Long indivId;
    private String actionType;

    private Character opentextInd;

    private CoGenerateManualDTO generateManualDTO;

    private String action;
    private String fullName;
    private Character watermark;
    private Character securityFlag;
    private String formVersion;
    private String formTitle;
    private String preferredLanguage;
    private Character goGreen;
    private String agencyCode;
    private String SSN;
    private Timestamp clientDOB;
    private Timestamp systemDate;
    private Timestamp mailDate;
    private MailingAdd mailingAdd;
    private MassHealthMedicaid MASSHealthMedicaid;
    private String templateId;
    private String formNo;
    private String caseworkerName;
    private String documentId;
    private String salutation;
    private java.sql.Timestamp noticeDate;
    private Long reciepientSeqNum;

}
