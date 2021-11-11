package com.deloitte.nextgen.framework.exceptions.handlers;

import org.springframework.http.HttpStatus;

/**
 * @author nishmehta on 17/06/2021 6:31 PM
 * @project ng-framework
 */
public abstract class AbstractNotFoundExceptionHandler<T extends Throwable> extends AbstractExceptionHandler<T> {

    protected AbstractNotFoundExceptionHandler(Class<?> exceptionClass) {
        super(exceptionClass);
    }

    @Override
    protected int getMessageCode(T ex) {
        return 102;
    }

    @Override
    public HttpStatus getStatus(T ex) {
        return HttpStatus.NOT_FOUND;
    }
}
