package com.deloitte.nextgen.framework.exceptions.handlers;

import org.springframework.http.HttpStatus;

/**
 * @author nishmehta on 17/06/2021 6:38 PM
 * @project ng-framework
 */
public class AbstractPersistenceExceptionHandler<T extends Throwable> extends AbstractExceptionHandler<T> {

    public AbstractPersistenceExceptionHandler(Class<?> exceptionClass) {
        super(exceptionClass);
    }

    @Override
    protected int getMessageCode(T ex) {
        return 300;
    }

    @Override
    public HttpStatus getStatus(T ex) {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
