package com.deloitte.nextgen.appreg.client.request;

import lombok.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppSearchRequest {
    @NotNull
    private String appNum;
    private Long applicantSSN;
    private String applicantFirstName;
    private String applicantLastName;
}
