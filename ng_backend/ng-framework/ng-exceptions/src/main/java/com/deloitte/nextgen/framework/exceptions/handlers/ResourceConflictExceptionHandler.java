package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.commons.exceptions.ResourceConflictException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 08/06/2021 2:27 PM
 * @project ng-framework
 */

@Component
@Order(1)
public class ResourceConflictExceptionHandler extends AbstractExceptionHandler<ResourceConflictException> {

    public ResourceConflictExceptionHandler() {
        super(ResourceConflictException.class);
    }

    @Override
    protected HttpStatus getStatus(ResourceConflictException ex) {
        return HttpStatus.CONFLICT;
    }

    @Override
    protected int getMessageCode(ResourceConflictException ex) {
        return ex.getCode();
    }
}
