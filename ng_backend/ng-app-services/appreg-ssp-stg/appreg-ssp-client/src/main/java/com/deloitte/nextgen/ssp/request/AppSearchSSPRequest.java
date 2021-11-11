package com.deloitte.nextgen.ssp.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppSearchSSPRequest {
    @NotNull
    private String appNum;
    private Long applicantSSN;
    @NotBlank
    private String applicantFirstName;
    @NotBlank
    private String applicantLastName;
}
