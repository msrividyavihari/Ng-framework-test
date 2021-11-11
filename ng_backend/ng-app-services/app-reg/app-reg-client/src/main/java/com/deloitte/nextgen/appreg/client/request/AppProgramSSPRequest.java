package com.deloitte.nextgen.appreg.client.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppProgramSSPRequest {
    @NotNull
    private String appNum;
    private Character request_status_ind;
    private String program_cd;
}
