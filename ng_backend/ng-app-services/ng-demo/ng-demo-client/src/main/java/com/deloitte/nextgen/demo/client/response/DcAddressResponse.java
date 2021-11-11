package com.deloitte.nextgen.demo.client.response;

import com.deloitte.nextgen.framework.commons.enums.ActiveType;
import lombok.Builder;
import lombok.Data;

/**
 * @author nishmehta on 15/06/2021 7:37 PM
 * @project ng-demo
 */

@Data
@Builder
public class DcAddressResponse {

    private Long addressId;

    private String addressLineOne;

    private String addressLineTwo;

    private Long city;

    private Long state;

    private String zipCode;

    private ActiveType activeSw;

    private Long uniqueTransId;
}
