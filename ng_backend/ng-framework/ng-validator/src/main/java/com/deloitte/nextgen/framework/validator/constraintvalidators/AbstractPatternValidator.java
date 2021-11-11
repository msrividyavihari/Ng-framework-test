package com.deloitte.nextgen.framework.validator.constraintvalidators;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public abstract class AbstractPatternValidator<T extends Annotation> implements ConstraintValidator<T, String> {

    protected T annotation;

    @Override
    public void initialize(T constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String regex = getRegex();
        if (".*".equals(regex)) {
            throw new IllegalArgumentException("\".*\" is default pattern. Please specify exact pattern.");
        }

        Pattern pattern;

        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            log.error("Incorrect pattern", e);
            throw e;
        }

        return pattern.matcher(value).matches();
    }

    protected abstract String getRegex();

}
