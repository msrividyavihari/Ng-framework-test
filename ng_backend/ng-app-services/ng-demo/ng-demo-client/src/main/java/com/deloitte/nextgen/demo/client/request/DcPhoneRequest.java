package com.deloitte.nextgen.demo.client.request;

import com.deloitte.nextgen.demo.client.enums.ContactType;
import com.deloitte.nextgen.framework.commons.constants.PatternConstants;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author nishmehta
 * @since 16/08/2021 2:11 PM
 */

@Data
public class DcPhoneRequest {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotBlank
    @Pattern(regexp = PatternConstants.US.PHONE_NUMBER)
    private String phoneNumber;

}
