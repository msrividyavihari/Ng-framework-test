package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.commons.payload.response.InvalidFieldError;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;

/**
 * @author nishmehta on 07/06/2021 1:33 PM
 * @project ng-framework
 */

@Slf4j
@Component
@Order(1)
public class MethodArgumentNotValidExceptionHandler extends AbstractExceptionHandler<MethodArgumentNotValidException> {

    public MethodArgumentNotValidExceptionHandler() {

        super(MethodArgumentNotValidException.class);
        log.info(LogMarker.EXCEPTION, "Created MethodArgumentNotValidExceptionHandler");
    }

    @Override
    public Collection<InvalidFieldError> getErrors(MethodArgumentNotValidException ex) {
        return InvalidFieldError.getErrors(ex.getBindingResult());
    }

    @Override
    protected int getMessageCode(MethodArgumentNotValidException ex) {
        return 101;
    }

    @Override
    protected HttpStatus getStatus(MethodArgumentNotValidException ex) {
        return HttpStatus.BAD_REQUEST;
    }
}
