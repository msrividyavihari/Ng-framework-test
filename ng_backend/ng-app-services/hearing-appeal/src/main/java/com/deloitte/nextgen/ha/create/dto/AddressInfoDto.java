package com.deloitte.nextgen.ha.create.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class AddressInfoDto {

    /*Residence address*/
    private BigInteger addressId;
    private String streetName; //Residenceaddress1
    private String addressLine2; //Residennceaddress2
    private String cityName; //city
    private String stateCd;//state
    private String zipCd; //zipcode
    private String countyCd; //countycd

    private String createUserId;
    private Date createDt;
    private String updateUserId;
    private Date updateDt;
    private Long uniqueTransId;
    //private Date archiveDt;
    private Long historySeq;

}
