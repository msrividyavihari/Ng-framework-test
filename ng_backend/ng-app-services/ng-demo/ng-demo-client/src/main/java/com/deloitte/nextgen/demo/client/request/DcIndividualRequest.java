package com.deloitte.nextgen.demo.client.request;

import com.deloitte.nextgen.framework.validator.annotations.AlphaSpace;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author nishmehta on 14/06/2021 6:48 PM
 * @project ng-demo
 */

@Data
public class DcIndividualRequest {

    private Long individualId;

    @AlphaSpace
    @Size(min = 3)
    private String firstName;

    @AlphaSpace
    @Size(min = 3)
    private String lastName;

    @AlphaSpace
    private String middleName;

    //@SSN
    private String ssn;

    @PastOrPresent
    private LocalDate birthDate;

    private LocalTime birthTime;

    @Valid
    private Set<DcAddressRequest> addresses;

    @Valid
    private Set<DcPhoneRequest> phoneNumbers;

    @Valid
    private Set<DcEmailRequest> emailIds;

}
