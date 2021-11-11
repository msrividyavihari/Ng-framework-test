package com.deloitte.nextgen.appreg.client.request;

import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArAppIndvReqAndResp {
    @NotNull
    private String appNum;
    private Character employeeSw;
    @Convert(converter = ActiveConverter.class)
    private Active headOfHouseholdSw;
    @NotNull
    private Long indvId;
    private Character indvStatusSw;

}
