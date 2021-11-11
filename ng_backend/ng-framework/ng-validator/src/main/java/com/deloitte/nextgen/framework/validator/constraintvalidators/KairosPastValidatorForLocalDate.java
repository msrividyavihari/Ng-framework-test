package com.deloitte.nextgen.framework.validator.constraintvalidators;

import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class KairosPastValidatorForLocalDate extends AbstractKairosPastValidator<LocalDate> {

    @Override
    protected LocalDate getReferenceValue(Clock reference) {
        return LocalDate.now(reference);
    }
}
