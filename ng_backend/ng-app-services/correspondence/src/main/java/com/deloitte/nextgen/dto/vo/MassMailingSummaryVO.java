package com.deloitte.nextgen.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MassMailingSummaryVO {

    @NonNull
    private Long massMailingSeqNum;
    private String noticeTitle;
    private String[] programLst;
    private String recptPop;
    private String programStr;
    @JsonFormat(pattern = "MM/dd/YY")
    private java.sql.Timestamp scheduledDt;
    private String createUserId;
    private String noticeText;
    private String policyManualRef;
    private String[] countyCd;
    public ArrayList<String> messageCodes;
}
