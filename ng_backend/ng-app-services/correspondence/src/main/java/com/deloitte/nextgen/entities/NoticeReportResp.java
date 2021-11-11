package com.deloitte.nextgen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NoticeReportResp {

    private String documentName;
    private Integer total;
    private Integer held;
    private Integer suppressed;
    private Integer localPrinted;
    private Integer failed;
    private Integer emailed;
    private Integer percentEmailed;
    private Integer undeliveredEmail;
    private Integer percentUndeliveredEmail;
    private Integer totalPrinted;
    private Integer percentPrinted;
    private Integer returnedMailCount;
    private Integer percentMailReturned;

 }

