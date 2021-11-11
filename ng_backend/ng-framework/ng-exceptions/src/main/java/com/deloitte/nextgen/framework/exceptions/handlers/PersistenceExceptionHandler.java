package com.deloitte.nextgen.framework.exceptions.handlers;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;

/**
 * @author nishmehta on 08/06/2021 2:23 PM
 * @project ng-framework
 */

@Component
@Order(1)
public class PersistenceExceptionHandler extends AbstractNotFoundExceptionHandler<PersistenceException> {

    public PersistenceExceptionHandler() {
        super(PersistenceException.class);
    }

}
