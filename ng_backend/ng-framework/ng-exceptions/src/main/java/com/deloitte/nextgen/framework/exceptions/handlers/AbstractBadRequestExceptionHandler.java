package com.deloitte.nextgen.framework.exceptions.handlers;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

/**
 * @author nishmehta on 17/06/2021 3:24 PM
 * @project ng-framework
 */


@Order
public abstract class AbstractBadRequestExceptionHandler<T extends Throwable> extends AbstractExceptionHandler<T> {

    protected AbstractBadRequestExceptionHandler(Class<?> exceptionClass) {
        super(exceptionClass);
    }

    @Override
    public HttpStatus getStatus(T ex) {
        return HttpStatus.BAD_REQUEST;
    }
}
