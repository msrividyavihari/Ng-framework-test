package com.deloitte.nextgen.ssp.responses;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AuthIndividualInformationResponse {
    private String panelName;
    @NotNull
    private String appNum;
    private Character hasAuthRepSw;
    private String authRepFirstName;
    private String authRepMiddleName;
    private String authRepLastName;
    private String l1Adr;
    private String l2Adr;
    private String cityAdr;
    private String staAdr;
    private String zipAdr;
}
