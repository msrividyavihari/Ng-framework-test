package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArAppProgramIndvResponse {
    @NotNull
    private String appNum;
    private String programCd;
    @NotNull
    private long indvId;
    private String priorMedicaidCd;
    private java.sql.Timestamp requestDt;
    @Convert(converter = ActiveConverter.class)
    private Active aidRequestSw;
    private char dchApprovedMaEligSw;
    private String maFormCd;
    private char pckProgSw;
}
