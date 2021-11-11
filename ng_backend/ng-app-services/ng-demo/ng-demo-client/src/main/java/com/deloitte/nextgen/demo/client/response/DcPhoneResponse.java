package com.deloitte.nextgen.demo.client.response;

import com.deloitte.nextgen.demo.client.enums.ContactType;
import lombok.Data;

/**
 * @author nishmehta
 * @since 16/08/2021 2:11 PM
 */

@Data
public class DcPhoneResponse {

    private Long id;

    private ContactType type;

    private String phoneNumber;

    private Long uniqueTransId;
}
