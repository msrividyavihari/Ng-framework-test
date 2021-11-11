package com.deloitte.nextgen.util;

public enum DocumentName {
    MA_APPDENY_001("Deny All"),
    MAH_CPN_001("Covid Protection Notice"),
    MA_DOWNGRADE_001("Downgrade"),
    MA_DTA_001("DTA - EBC"),
    MA_MANUAL_001("Ad Hoc");

    private final String statusVal;
    DocumentName(String statusValue) {
        this.statusVal = statusValue;
    }

    public String getStatusVal() {
        return statusVal;
    }
}