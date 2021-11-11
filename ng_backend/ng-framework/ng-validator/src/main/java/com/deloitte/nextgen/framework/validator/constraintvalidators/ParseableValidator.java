package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.Parseable;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import java.util.function.Function;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public class ParseableValidator implements HibernateConstraintValidator<Parseable, String> {

    private Parseable annotation;

    @Override
    public void initialize(Parseable constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value) {
            return false;
        }

        Parseable.ParseableType type = annotation.value();

        switch (type) {
            case TO_INT:
                return parse(value, Integer::parseInt);
            case TO_DOUBLE:
                return parse(value, Double::parseDouble);
            case TO_LONG:
                return parse(value, Long::parseLong);
            case TO_SHORT:
                return parse(value, Short::parseShort);
            case TO_FLOAT:
                return parse(value, Float::parseFloat);
        }

        return false;
    }

    private <T> boolean parse(String value, Function<String, T> function) {
        try {
            function.apply(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
