package com.deloitte.nextgen.ha.create.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigInteger;


@Data
@ToString
@EqualsAndHashCode
public class AmRepresentativeDetailsDto {

    private String lastName;
    private String firstName;
    private String suffixName;
    private String midName;

    private BigInteger addressId;
    private long authRepId;

  /*  private String addrLine1;
    private String addrLine2;
    private String addrCity;
    private String addrStateCd;
    private String addrZip5;
    private String addrZip4;
    private String addrCountyCd;*/

    private String phoneNum1;
    private String phoneNum2;

    private String phoneExt;
    private String email;

}
