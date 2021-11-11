package com.deloitte.nextgen.demo.client.request;

import com.deloitte.nextgen.demo.client.validators.annotation.Address;
import com.deloitte.nextgen.framework.commons.enums.ActiveType;
import com.deloitte.nextgen.framework.validator.annotations.ZipCode;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author nishmehta on 15/06/2021 7:37 PM
 * @project ng-demo
 */

@Data
public class DcAddressRequest {

    private Long addressId;

    @Address
    private String addressLineOne;

    @Address
    private String addressLineTwo;

    @NotNull
    private Long city;

    @NotNull
    private Long state;

    @ZipCode
    private String zipCode;

    private ActiveType activeSw;

}
