package com.deloitte.nextgen.framework.exceptions.handlers;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

/**
 * @author nishmehta on 08/06/2021 2:23 PM
 * @project ng-framework
 */

@Component
@Order(1)
public class EntityNotFoundExceptionHandler extends AbstractNotFoundExceptionHandler<EntityNotFoundException> {

    public EntityNotFoundExceptionHandler() {
        super(EntityNotFoundException.class);
    }

}
