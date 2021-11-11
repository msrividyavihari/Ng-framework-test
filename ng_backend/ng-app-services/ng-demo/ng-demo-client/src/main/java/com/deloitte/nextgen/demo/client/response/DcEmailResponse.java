package com.deloitte.nextgen.demo.client.response;

import com.deloitte.nextgen.demo.client.enums.ContactType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author nishmehta
 * @since 16/08/2021 2:11 PM
 */

@Data
public class DcEmailResponse {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotBlank
    @Email
    private String emailId;

    private Long uniqueTransId;

}
