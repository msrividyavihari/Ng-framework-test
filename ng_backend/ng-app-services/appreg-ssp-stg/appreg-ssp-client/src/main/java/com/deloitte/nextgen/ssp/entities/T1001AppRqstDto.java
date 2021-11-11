package com.deloitte.nextgen.ssp.entities;

import com.deloitte.nextgen.ssp.entities.converters.ActiveConverter;
import com.deloitte.nextgen.ssp.enums.Active;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
public class T1001AppRqstDto {
    private String appNum;
    private Character app_cnfd_sw;
    @JsonProperty("appRecvdDt")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Timestamp appRcvDt;
    private Character appStatCd;
    @JsonProperty("dateTimeRegistered")
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp appTms;
    private String appTyp;
    private String cntyNum;
    private Character dablStatSw;
    private String hshlZipAdr;
    private String langCd;
    private Long ofcNum;
    private Character prirSrvcElgSw;
    @Convert(converter = ActiveConverter.class)
    private Active expeditedFapSw;
    private String appMode;
    private String authRepInd;
    private Character appStatusInd;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp sopDueDt;
    private String mcAppNum;
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    private java.sql.Timestamp appSubDate;
    private String appPdfName;
    private String arRltWithAppCd;
    private Long caseNum;
    private Character rteIndicatorSw;
    private Character noTouchEligSw;
    private Character alrdyNotouchProcessedSw;
    private String addrZip4;
    private String cpAppNum;
    private Character autoAppRegSw;
    private String isAabType;
}
