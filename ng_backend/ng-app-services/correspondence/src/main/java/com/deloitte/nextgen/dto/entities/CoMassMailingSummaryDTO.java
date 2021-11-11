package com.deloitte.nextgen.dto.entities;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class CoMassMailingSummaryDTO {

    @NonNull
    private Long massMailingSeqNum;
    private String noticeTitle;
    private String noticeTxt;
    private String legalCites;
    private String stdTextLst;
    private String[] programLst;
    private String recptPop;
    private String scheduledDt;
    private String author;
    private Character jobProcessedSw;
    private String[] countySelect;
    private String countyCd;
//    private java.lang.String rowId;
    private String coPolicyManualReference;
    private String[] standardTextCode;
    private String userName;
}
