package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.commons.exceptions.IdentifierException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 08/06/2021 2:23 PM
 * @project ng-framework
 */

@Component
@Order(1)
public class IdentifierExceptionHandler extends AbstractNotFoundExceptionHandler<IdentifierException> {

    public IdentifierExceptionHandler() {
        super(IdentifierException.class);
    }

}
