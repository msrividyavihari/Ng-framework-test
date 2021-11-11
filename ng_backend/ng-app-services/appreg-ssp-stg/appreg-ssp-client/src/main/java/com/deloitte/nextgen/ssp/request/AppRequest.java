package com.deloitte.nextgen.ssp.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AppRequest {
    @NotNull
    private String appNum;
}
