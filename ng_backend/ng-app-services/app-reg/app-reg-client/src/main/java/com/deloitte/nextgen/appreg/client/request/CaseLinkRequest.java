package com.deloitte.nextgen.appreg.client.request;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CaseLinkRequest {
    @NotNull
    public String appNum;
    @NotNull
    private Long caseNum;
}
