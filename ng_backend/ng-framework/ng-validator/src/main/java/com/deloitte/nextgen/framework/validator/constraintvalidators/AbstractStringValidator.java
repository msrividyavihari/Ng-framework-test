package com.deloitte.nextgen.framework.validator.constraintvalidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.function.Function;

/**
 * @author nishmehta
 * @since
 */

public abstract class AbstractStringValidator<T extends Annotation> implements ConstraintValidator<T, String> {

    protected T annotation;

    @Override
    public void initialize(T constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return condition().apply(value);
    }

    public abstract Function<String, Boolean> condition();

}
