package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.commons.payload.response.InvalidFieldError;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

/**
 * @author nishmehta on 07/06/2021 1:33 PM
 * @project ng-framework
 */

@Slf4j
@Component
@Order(1)
public class ConstraintViolationExceptionHandler extends AbstractExceptionHandler<ConstraintViolationException> {

    public ConstraintViolationExceptionHandler() {

        super(ConstraintViolationException.class);
        log.info(LogMarker.EXCEPTION, "Created ConstraintViolationExceptionHandler");
    }

    @Override
    public Collection<InvalidFieldError> getErrors(ConstraintViolationException ex) {
        return InvalidFieldError.getErrors(ex.getConstraintViolations());
    }

    @Override
    protected int getMessageCode(ConstraintViolationException ex) {
        return 100;
    }

    @Override
    protected HttpStatus getStatus(ConstraintViolationException ex) {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
