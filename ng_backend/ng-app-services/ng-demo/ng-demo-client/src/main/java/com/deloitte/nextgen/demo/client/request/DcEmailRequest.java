package com.deloitte.nextgen.demo.client.request;

import com.deloitte.nextgen.demo.client.enums.ContactType;
import com.deloitte.nextgen.framework.validator.annotations.KairosFuture;
import com.deloitte.nextgen.framework.validator.annotations.KairosFutureOrPresent;
import com.deloitte.nextgen.framework.validator.annotations.KairosPast;
import com.deloitte.nextgen.framework.validator.annotations.KairosPastOrPresent;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author nishmehta
 * @since 16/08/2021 2:11 PM
 */

@Data
public class DcEmailRequest {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotBlank
    @Email(message = "100")
    private String emailId;

    @KairosPastOrPresent(message="200")
    private LocalDate pastOrPresent;

    @KairosPast
    private LocalDate past;

    @KairosFuture
    private LocalDate future;

    @KairosFutureOrPresent
    private LocalDate futureOrPresent;

}
