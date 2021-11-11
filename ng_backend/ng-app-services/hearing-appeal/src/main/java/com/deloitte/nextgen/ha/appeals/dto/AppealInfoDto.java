package com.deloitte.nextgen.ha.appeals.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class AppealInfoDto {

    private LocalDate filedDate;
    private String filingMethod;
    private BigInteger caseNum;
    private String programCd;
    private String typeCd;
    private String reasonCd;

}
