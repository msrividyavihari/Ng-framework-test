package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import lombok.*;

import javax.persistence.Convert;
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
    @NotBlank
    private String resAddrZip5;
    private String resAddrTypeCd;
    private String mailAddrFormatCd;
    @NotBlank
    private String mailAddrLine;
    private String mailAddrLine1;
    private String mailAddrCountyCd;
    @NotBlank
    private String mailAddrCity;
    @NotEmpty
    private String mailAddrStateCd;
    @NotBlank
    private String mailAddrZip5;
    private String mailAddrTypeCd;
    private String EmailAdr;
    @Convert(converter = ActiveConverter.class)
    private Active resAddrSw;
    private String panelName;
}