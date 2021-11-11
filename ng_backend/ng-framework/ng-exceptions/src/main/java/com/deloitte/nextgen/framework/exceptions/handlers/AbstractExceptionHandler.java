package com.deloitte.nextgen.framework.exceptions.handlers;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.commons.payload.response.Debug;
import com.deloitte.nextgen.framework.commons.payload.response.ErrorResponse;
import com.deloitte.nextgen.framework.commons.payload.response.InvalidFieldError;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * @author nishmehta on 07/06/2021 12:25 PM
 * @project ng-framework
 */

@Slf4j
public abstract class AbstractExceptionHandler<T extends Throwable> {

    private final Class<?> exceptionClass;

    protected AbstractExceptionHandler(Class<?> exceptionClass) {
        this.exceptionClass = exceptionClass;
        log.info(LogMarker.EXCEPTION, "Initialized {}", exceptionClass.getSimpleName());
    }

    public Class<?> getExceptionClass() {
        return exceptionClass;
    }

    protected abstract  int getMessageCode(T ex);

    protected abstract HttpStatus getStatus(T ex);

    protected Collection<InvalidFieldError> getErrors(T ex) {
        log.error("invalid field error", ex);
        return CollectionUtils.emptyCollection();
    }

    public boolean instanceOf(T ex) {
        return exceptionClass.isInstance(ex);
    }

    public ApiResponse<ErrorResponse> getErrorResponse(T ex) {

        Collection<InvalidFieldError> invalidFieldErrors = getErrors(ex);
        ErrorResponse er = new ErrorResponse(invalidFieldErrors);

        if (log.isDebugEnabled() || log.isInfoEnabled()) {
            Debug debug = new Debug(ex.getMessage(), ex);
            er = new ErrorResponse(debug, invalidFieldErrors);
        }

        return ApiResponse
                .<ErrorResponse>with(getStatus(ex), getMessageCode(ex))
                .error(er);

    }
}
