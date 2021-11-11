package com.deloitte.nextgen.appreg.client.request;

import lombok.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ApplicantsRequest {

    @NotNull
    private Long indvId;
    private String applicantType;
    @NotNull
    private String appNum;
}
