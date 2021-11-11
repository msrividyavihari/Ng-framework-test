package com.deloitte.nextgen.appreg.client.entities;

import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import com.deloitte.nextgen.appreg.client.enums.Active;
import lombok.*;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
public class ArAppAddrDto {
    private String appNum;
    private Long appAddrSeqNum;
    private String addrTypeCd;
    private String addrLine;
    private String addrStNum;
    private String addrStNumFrac;
    private String addrAptNum;
    private String addrStNm;
    private String addrCity;
    private String addrZip5;
    private String addrZip4;
    private String addrDwellingTypeCd;
    private String addrCountyCd;
    private String addrStDirCd;
    private String addrStTypeCd;
    private String addrStateCd;
    private String addrPostDirCd;
    private String addrCareOfLine;
    private Long addrHashNum;
    private String addrFormatCd;
    private String addrLine1;
    private String addrLine3;
    private String apoFpoAddr;
    private String addrMilitary;
    private String addrCntry;
    @Convert(converter = ActiveConverter.class)
    private Active resAddrSw;
    private String drivInst;
    private Character livingResSw;
    private Character addrProgramReqCd;
}