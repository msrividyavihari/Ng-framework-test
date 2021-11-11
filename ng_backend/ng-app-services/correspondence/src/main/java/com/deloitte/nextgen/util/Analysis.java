package com.deloitte.nextgen.util;

public class Analysis {

    private String dpvMatchCode;

    private String dpvFootnotes;

    private String cmra;

    private String vacant;

    private String no_stat;

    private String active;

    private boolean ewsMatch;

    private String footnotes;

    private String lacsLinkCode;

    private String lacsLinkIndicator;

    private boolean suiteLinkMatch;

    private String enhancedMatch;

    //endregion

    //region [ Getters ]

    public String getDpvMatchCode() {
        return this.dpvMatchCode;
    }

    public String getDpvFootnotes() {
        return this.dpvFootnotes;
    }

    public String getCmra() {
        return this.cmra;
    }

    public String getVacant() {
        return this.vacant;
    }

    public String getNo_stat() { return this.no_stat; }

    public String getActive() {
        return this.active;
    }

    public String getEnhancedMatch() {
        return this.enhancedMatch;
    }

    //@deprecated moved to metadata field
    @Deprecated
    public boolean isEwsMatch() {
        return false;
    }

    public String getFootnotes() {
        return this.footnotes;
    }

    public String getLacsLinkCode() {
        return this.lacsLinkCode;
    }

    public String getLacsLinkIndicator() {
        return this.lacsLinkIndicator;
    }

    public boolean isSuiteLinkMatch() {
        return this.suiteLinkMatch;
    }


}
