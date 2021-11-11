package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class VCoRequestDTO {

    private Character actionCd;
    private String appNum;
    private Long apptId;
    private String assistanceList;
    private String benefitNum;
    private Long caseNum;
    private String chipAppNum;
    private Long coDetSeq;
    private String coMasterRid;
    private String coRequestHistoryDetailRid;
    private String coRequestHistoryRid;
    private Long coRptSeq;
    private String docName;
    private Character draftSw;
    private Long edgNum;
    private Long edgTraceId;
    private Long empId;
    private Character freeformSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp generateDt;
    private Character generateManuallySw;
    private Character historySw;
    private String hstPrintString;
    private Long indvId;
    private String languageCd;
    private Long logId;
    private Character manuallyGeneratedSw;
    private Character massEnabledSw;
    private Character massGeneratedSw;
    private String miscParms;
    private Long officeNum;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp origPrintDt;
    private Character pendingTrigSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp printDt;
    private Character printMode;
    private Character printModeCd;
    private String programCd;
    private Long providerId;
    private String reasonCdList;
    private Character reprintSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp reqDt;
    private Character requestCd;
    private Character requestTypeCd;
    private Character stufferSw;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t1ArchiveDt;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t1CreateDt;
    private String t1CreateUserId;
    private String t1DocId;
    private Character t1DocTypeCd;
    private Long t1UniqueTransId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t1UpdateDt;
    private String t1UpdateUserId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t2ArchiveDt;
    private Long t2CoReqSeq;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t2CreateDt;
    private String t2CreateUserId;
    private String t2DocId;
    private Character t2DocTypeCd;
    private Long t2UniqueTransId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t2UpdateDt;
    private String t2UpdateUserId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t3ArchiveDt;
    private Long t3CoReqSeq;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t3CreateDt;
    private String t3CreateUserId;
    private Long t3UniqueTransId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp t3UpdateDt;
    private String t3UpdateUserId;
    private Long massMailingId;
    private String caseAppNum;
    private Long provider;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp effBeginDt;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp effEndDt;
    private String recipientType;
    private Character coStatusSw;
    private String disId;
    private Long disDocMstrSeqNum;
    private String edbcRunId;
    private String specialNotes;
    private Long ccProviderId;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp ccProviderCertStartDt;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private Timestamp ccProviderCertEndDt;
}
