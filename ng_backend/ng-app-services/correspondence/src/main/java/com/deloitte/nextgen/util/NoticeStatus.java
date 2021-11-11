package com.deloitte.nextgen.util;

public enum NoticeStatus {

    RR("REQUEST RECEIVED"),
    IR("INVALID REQUEST"),
    IA("INVALID ADDRESS"),
    AU("ADDRESS UPDATED"),
    PP("PENDING PRINT"),
    CF("PDF CREATION FAILED"),
    SU("SUPPRESS"),
    HD("HOLD"),
    LP("LOCAL PRINT"),
    PO("PREVIEW ONLY"),
    PR("PROCESSED"),
    FL("FAILURE"),
    RM("BOUNCED BACK");

    private final String statusVal;

    NoticeStatus(String statusValue) {
        this.statusVal = statusValue;
    }

    public String getStatusVal() {
        return statusVal;
    }
}
