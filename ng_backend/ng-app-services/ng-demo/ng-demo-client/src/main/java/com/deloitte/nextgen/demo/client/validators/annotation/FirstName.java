package com.deloitte.nextgen.demo.client.validators.annotation;

import com.deloitte.nextgen.framework.validator.annotations.Alpha;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
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
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@NotBlank
@Alpha
public @interface FirstName {

    String message() default "{com.deloitte.nextgen.demo.client.validators.annotation.FirstName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
