package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 17/06/2021 3:23 PM
 * @project ng-framework
 */

@Slf4j
@Component
@Order(1001)
public class JsonParseExceptionHandler extends AbstractBadRequestExceptionHandler<JsonParseException> {

    public JsonParseExceptionHandler() {

        super(JsonParseException.class);
        log.info(LogMarker.EXCEPTION, "Created JsonParseExceptionHandler");
    }

    @Override
    protected int getMessageCode(JsonParseException ex) {
        return 100;
    }

}
