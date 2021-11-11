package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArAppProgramResponse {
    @NotNull
    private String appNum;
    private String programCd;
    private String priorMedicaidCd;
    private java.sql.Timestamp requestDt;
    @Convert(converter = ActiveConverter.class)
    private Active expeditedSw;
    private String progStatusCd;
    private java.sql.Timestamp progStatusDt;
    private String serServiceCd;
    private java.sql.Timestamp expScreenDt;
    private String medicaidTypeCd;
    private char waitlistScreeningSw;
    private String withdrwlReason;
    private char pckProgSw;
    private String clinicCd;
}
