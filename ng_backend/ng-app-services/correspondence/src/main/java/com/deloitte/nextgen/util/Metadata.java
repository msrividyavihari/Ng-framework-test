package com.deloitte.nextgen.util;

public class Metadata {

    private String recordType;

    private String zipType;

    private String countyFips;

    private String countyName;

    private String carrierRoute;

    private String congressionalDistrict;

    private String buildingDefaultIndicator;

    private String rdi;

    private String elotSequence;

    private String elotSort;

    private double latitude;

    private double longitude;

    private String precision;

    private String timeZone;

    private double utcOffset;

    private boolean obeysDst;

    private boolean ewsMatch;

    //endregion

    //region [ Getters ]

    public String getRecordType() {
        return this.recordType;
    }

    public String getZipType() {
        return this.zipType;
    }

    public String getCountyFips() {
        return this.countyFips;
    }

    public String getCountyName() {
        return this.countyName;
    }

    public String getCarrierRoute() {
        return this.carrierRoute;
    }

    public String getCongressionalDistrict() {
        return this.congressionalDistrict;
    }

    public String getBuildingDefaultIndicator() {
        return this.buildingDefaultIndicator;
    }

    public String getRdi() {
        return this.rdi;
    }

    public String getElotSequence() {
        return this.elotSequence;
    }

    public String getElotSort() {
        return this.elotSort;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getPrecision() {
        return this.precision;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public double getUtcOffset() {
        return this.utcOffset;
    }

    public boolean obeysDst() {
        return this.obeysDst;
    }

    public boolean isEwsMatch() {
        return this.ewsMatch;
    }
}
