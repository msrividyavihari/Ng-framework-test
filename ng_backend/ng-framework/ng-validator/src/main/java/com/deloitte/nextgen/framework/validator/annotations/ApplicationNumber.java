package com.deloitte.nextgen.framework.validator.annotations;

import com.deloitte.nextgen.framework.commons.constants.PatternConstants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationNumber {

    String message() default "{com.deloitte.nextgen.framework.validator.annotations.ApplicationNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regex() default PatternConstants.Identifier.APPLICATION_NUMBER;
}
