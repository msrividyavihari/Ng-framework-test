package com.deloitte.nextgen.ssp.entities.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.ssp.entities.converters.ActiveConverter;
import com.deloitte.nextgen.ssp.enums.Active;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = T1001_AppRqst.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
public class T1001_AppRqst extends TypeOneBaseEntity<String> {

    @Transient
    public static final String TABLE_NAME = "T1001_APP_RQST";

    @Id
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
