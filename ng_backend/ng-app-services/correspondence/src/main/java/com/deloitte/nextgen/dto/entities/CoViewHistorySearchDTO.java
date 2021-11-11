package com.deloitte.nextgen.dto.entities;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CoViewHistorySearchDTO {

    @NonNull
    private String searchCriteria;
    private Long caseNum;
    private String appNum;
    private Long clientId;
    private String workerName;
    private Long workerId;
    private Timestamp reqDt;
    private Timestamp printDt;
    private Timestamp ccCertStartDt;
    private Timestamp ccCertEndDt;
    private Long ccProviderId;
    private Long coReqSeq;
    private String docId;
    private Character printMode;
    private String programCd;

}
