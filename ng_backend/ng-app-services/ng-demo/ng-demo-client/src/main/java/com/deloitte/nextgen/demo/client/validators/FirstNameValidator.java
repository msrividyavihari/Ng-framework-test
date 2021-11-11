package com.deloitte.nextgen.demo.client.validators;

import com.deloitte.nextgen.demo.client.validators.annotation.FirstName;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Component
public class FirstNameValidator implements ConstraintValidator<FirstName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
