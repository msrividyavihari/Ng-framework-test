package com.deloitte.nextgen.appreg.client.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CaseDetailsRequest {
    @NotNull
    private Long indvId;
    @NotNull
    public String appNum;
}
