package com.deloitte.nextgen.framework.validator.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nishmehta on 30/03/2021 12:56 PM
 * @project ng-validator
 */

@Constraint(validatedBy = {})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EffectiveDates {

    String message() default "{com.deloitte.nextgen.framework.validator.annotations.EffectiveDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
