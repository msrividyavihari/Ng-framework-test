package com.deloitte.nextgen.framework.validator.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@NotBlank
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsDate {

    String format();

    String message() default "{com.deloitte.nextgen.framework.validator.annotations.IsDate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
