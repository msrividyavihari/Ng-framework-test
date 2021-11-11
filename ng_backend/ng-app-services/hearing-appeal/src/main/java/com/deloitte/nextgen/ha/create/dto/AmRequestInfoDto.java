package com.deloitte.nextgen.ha.create.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class AmRequestInfoDto {

    private BigInteger aplNum;
    private BigInteger IesCaseNum;//casenumber
    private LocalDate perfectedDt; //fillingdate/
    private String filingMethodCd; //FilingMethodCd/
    private BigInteger indvId;
    private BigInteger addressId;

    private String createUserId;
    private Date createDt;
    private String updateUserId;
    private Date updateDt;
    private Long uniqueTransId;
    private Long historySeq;


}
