package com.deloitte.nextgen.ha.common.mapstruct.mapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
@EqualsAndHashCode
public class AddressInfoCreateDto {

    /*Residence address*/
    private String streetName; //Residenceaddress1
    private String addressLine2; //Residennceaddress2
    private String cityName; //city
    private String stateCd;//state
    private String zipCd; //zipcode
    private String countyCd; //countycd


}




