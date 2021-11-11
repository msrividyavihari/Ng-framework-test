package com.deloitte.nextgen.framework.web.advice;

import com.deloitte.nextgen.framework.exceptions.ErrorResponseComposer;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author nishmehta
 * @since 1.0.0
 */


@Slf4j
public class NextGenErrorAttributes<T extends Throwable> extends DefaultErrorAttributes {

    static final String HTTP_STATUS_KEY = "httpStatus";
    private final ErrorResponseComposer<T> errorResponseComposer;

    public NextGenErrorAttributes(ErrorResponseComposer<T> errorResponseComposer) {

        this.errorResponseComposer = errorResponseComposer;
        log.info("Initialized NextGenErrorAttributes");
    }

    /**
     * Calls the base class and then adds our details
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);

        addErrorDetails(errorAttributes, request);

        return errorAttributes;
    }

    /**
     * Handles exceptions
     */
    @SuppressWarnings("unchecked")
    protected void addErrorDetails(Map<String, Object> errorAttributes, WebRequest request) {

        Throwable ex = getError(request);

        errorResponseComposer.compose((T) ex).ifPresent(errorResponse -> {

            // check for null - errorResponse may have left something for the DefaultErrorAttributes

            if (errorResponse.getMessage() != null)
                errorAttributes.put("message", errorResponse.getMessage());

            Integer status = errorResponse.getHttpStatus().value();

            if (status != null) {
                errorAttributes.put(HTTP_STATUS_KEY, status); // a way to pass response status to LemonErrorController
                errorAttributes.put("status", status);
                errorAttributes.put("error", ex.getMessage());
            }

            log.error(LogMarker.WEB, "in error attributes", ex);

        });
    }

}
