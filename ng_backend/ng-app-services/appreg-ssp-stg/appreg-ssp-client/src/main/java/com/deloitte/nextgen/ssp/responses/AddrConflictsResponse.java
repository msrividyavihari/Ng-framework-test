package com.deloitte.nextgen.ssp.responses;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddrConflictsResponse {
    @NotNull
    private String appNum;
    private String resAddrFormatCd;
    @NotBlank
    private String resAddrLine;
    private String resAddrLine1;
    private String resAddrCountyCd;
    @NotBlank
    private String resAddrCity;
    @NotEmpty
    private String resAddrStateCd;
    @Digits(integer = 5, fraction = 0)
    private String resAddrZip5;
    private String resAddrTypeCd;
    private String mailAddrFormatCd;
    private String mailAddrLine;
    private String mailAddrLine1;
    private String mailAddrCountyCd;
    private String mailAddrCity;
    private String mailAddrStateCd;
    private String mailAddrZip5;
    private String mailAddrTypeCd;
    private String EmailAdr;
    private Character resAddrSw;
    private String panelName;
}