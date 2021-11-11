package com.deloitte.nextgen.framework.commons.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author nishmehta on 27/03/2021 12:13 PM
 * @project ng-framework
 */

@Data
@AllArgsConstructor
public class InvalidFieldError {

    private String field;

    private final String code;

    private String message;


    public static List<InvalidFieldError> getErrors(
            Set<ConstraintViolation<?>> constraintViolations) {

        return constraintViolations.stream()
                .map(InvalidFieldError::of).collect(Collectors.toList());
    }

    private static InvalidFieldError of(ConstraintViolation<?> constraintViolation) {

        String field = StringUtils.substringAfter(
                constraintViolation.getPropertyPath().toString(), ".");

        return new InvalidFieldError(field,
                constraintViolation.getMessageTemplate(),
                constraintViolation.getMessage());
    }

    public static List<InvalidFieldError> getErrors(BindingResult bindingResult) {

        List<InvalidFieldError> errors = bindingResult.getFieldErrors().stream()
                .map(InvalidFieldError::of).collect(Collectors.toList());

        errors.addAll(bindingResult.getGlobalErrors().stream()
                .map(InvalidFieldError::of).collect(Collectors.toSet()));

        return errors;
    }

    public static List<InvalidFieldError> getErrors(WebExchangeBindException ex) {

        List<InvalidFieldError> errors = ex.getFieldErrors().stream()
                .map(InvalidFieldError::of).collect(Collectors.toList());

        errors.addAll(ex.getGlobalErrors().stream()
                .map(InvalidFieldError::of).collect(Collectors.toSet()));

        return errors;
    }

    private static InvalidFieldError of(FieldError fieldError) {

        return new InvalidFieldError(fieldError.getField(),
                fieldError.getCode(), fieldError.getDefaultMessage());
    }

    public static InvalidFieldError of(ObjectError error) {

        return new InvalidFieldError(error.getObjectName(),
                error.getCode(), error.getDefaultMessage());
    }
}
