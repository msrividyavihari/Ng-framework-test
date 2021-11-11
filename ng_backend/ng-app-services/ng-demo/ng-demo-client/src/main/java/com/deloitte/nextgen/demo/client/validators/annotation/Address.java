package com.deloitte.nextgen.demo.client.validators.annotation;

import com.deloitte.nextgen.framework.validator.annotations.Text;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

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

@ConstraintComposition(CompositionType.OR)
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Text(allowSpecialCharacters = {'_', '&', '-', ','})
public @interface Address {

    String message() default "{com.deloitte.nextgen.demo.client.validators.annotation.Address.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
