package com.deloitte.nextgen.appreg.client.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class AppNumApplicantsRequest {
    @NotNull
    private String appNum;
    @Valid
    private List<ApplicantRequest> applicants;
}
