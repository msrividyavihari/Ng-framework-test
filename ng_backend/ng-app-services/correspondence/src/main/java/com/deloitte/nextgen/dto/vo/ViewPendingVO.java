package com.deloitte.nextgen.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewPendingVO {

    @NonNull
    private Long indvId;
    private String docName;
    private String languageCd;
    @JsonFormat(pattern = "MM/dd/YY")
    private Date reqDt;
    @JsonFormat(pattern = "MM/dd/YY")
    private Date generateDt;
    private Character pendingTrigSw;
    private Long coReqSeq;
    private String docId;
    private Long ccProviderId;
    @JsonFormat(pattern = "MM/dd/YY")
    private Date ccProviderCertStartDt;
    @JsonFormat(pattern = "MM/dd/YY")
    private Date ccProviderCertEndDt;
    private Character coStatusSw;
    private Character draftSw;
    private String workerName;
    private Character printMode;
    private Character printType;
    private String idType;
    private String caseAppNumber;
    private String searchCriteria;
    private Long empId;
    // sequence number from IN_DIS_DOC_MASTER
    private Long disDocMstrSeqNum;
    @JsonFormat(pattern = "MM/dd/YY")
    private Date printDt;
    private String t3CreateUserId;
    private Long t2CoReqSeq;
    private Long coDetSeq;
    private Long coRptSeq;
    private Character rePrintSw;
    private Character opentextInd;
    private String status;
}
