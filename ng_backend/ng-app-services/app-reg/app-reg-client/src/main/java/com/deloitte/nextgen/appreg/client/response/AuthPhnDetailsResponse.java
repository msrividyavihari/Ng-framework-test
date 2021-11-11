package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthPhnDetailsResponse {
    private String panelName;
    @NotNull
    private String appNum;
    private Character hasAuthRepSw;
    @Digits(integer=10, fraction=0)
    private String phnNum;
    private String EmailAdr;
}
