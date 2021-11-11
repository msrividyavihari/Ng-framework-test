package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.validator.annotations.IsDate;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/**
 * @author nishmehta
 * @since
 */
public class IsDateValidator implements HibernateConstraintValidator<IsDate, String> {

    private String pattern;

    @Override
    public void initialize(IsDate constraintAnnotation) {
        this.pattern = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        try {
            dtf.parse(value);
            return true;
        }catch(DateTimeException ex) {
            return false;
        }
    }
}
