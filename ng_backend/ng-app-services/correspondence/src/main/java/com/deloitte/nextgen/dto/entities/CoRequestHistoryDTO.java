package com.deloitte.nextgen.dto.entities;

import lombok.*;

import java.sql.Blob;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CoRequestHistoryDTO {

    private Long coReqSeq;
    private String docId;
    private Long indvId;
    private Character actionCd;
    private String reasonCdList;
    private Character draftSw;
    private String languageCd;
    private Long empId;
    private Timestamp generateDt;
    private String miscParms;
    private Character historySw;
    private Character pendingTrigSw;
    private Blob hstPrintString;
    private Character docTypeCd;
    private Character requestTypeCd;
    private String programCd;
    private Timestamp origPrintDt;
    private Long apptId;
    private Long officeNum;
    private Long edgNum;
    private String benefitNum;
    private Character manuallyGeneratedSw;
    private Character massGeneratedSw;
    private String assistanceList;
    private Long caseNum;
    private String appNum;
    private String chipAppNum;
    private Long logId;
    private Long edgTraceId;
    private Long massMailingId;
    private Long providerId;
    private String locationId;
    private String disId;
    private String specialNotes;
    private String goGreen;
    private Character coStatusSw;
    private String edbcRunId;
    private String medicaidIndvId;
    private Long pageNum;
    private Long ccProviderId;
    private Timestamp ccProviderCertStartDt;
    private Timestamp ccProviderCertEndDt;
}
