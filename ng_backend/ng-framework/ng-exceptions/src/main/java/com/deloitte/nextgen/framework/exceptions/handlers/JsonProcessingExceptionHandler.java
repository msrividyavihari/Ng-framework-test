package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 17/06/2021 3:23 PM
 * @project ng-framework
 */

@Slf4j
@Component
@Order(1002)
public class JsonProcessingExceptionHandler extends AbstractBadRequestExceptionHandler<JsonProcessingException> {

    public JsonProcessingExceptionHandler() {

        super(JsonProcessingException.class);
        log.info(LogMarker.EXCEPTION, "Created JsonProcessingException");
    }


    @Override
    protected int getMessageCode(JsonProcessingException ex) {
        return 100;
    }
}
