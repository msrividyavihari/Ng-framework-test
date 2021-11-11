package com.deloitte.nextgen.demo.client.exceptions.handler;

import com.deloitte.nextgen.demo.client.exceptions.DemoAddressNotFoundException;
import com.deloitte.nextgen.framework.exceptions.handlers.AbstractNotFoundExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 18/06/2021 1:34 PM
 * @project ng-demo
 */

@Component
public class DemoAddressNotFoundExceptionHandler extends AbstractNotFoundExceptionHandler<DemoAddressNotFoundException> {

    public DemoAddressNotFoundExceptionHandler() {
        super(DemoAddressNotFoundException.class);
    }
}
