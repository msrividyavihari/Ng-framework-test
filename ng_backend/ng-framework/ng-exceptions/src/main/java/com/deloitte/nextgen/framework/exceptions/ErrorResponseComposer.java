package com.deloitte.nextgen.framework.exceptions;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.commons.payload.response.ErrorResponse;
import com.deloitte.nextgen.framework.exceptions.handlers.AbstractExceptionHandler;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author nishmehta on 07/06/2021 12:15 PM
 * @project ng-framework
 */

@Slf4j
public class ErrorResponseComposer<T extends Throwable> {

    private final Map<Class<?>, AbstractExceptionHandler<T>> handlers;

    public ErrorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {

        this.handlers = handlers.stream().collect(
                Collectors.toMap(AbstractExceptionHandler::getExceptionClass,
                        Function.identity(), (handler1, handler2) ->

                                AnnotationAwareOrderComparator
                                        .INSTANCE.compare(handler1, handler2) < 0 ?
                                        handler1 : handler2
                ));

        log.info(LogMarker.EXCEPTION, "Registered {} exception handlers.", handlers.size());
        log.info(LogMarker.EXCEPTION, "Created ErrorResponseComposer");
    }

    public Optional<ApiResponse<ErrorResponse>> compose(T ex) {
        log.error(LogMarker.EXCEPTION, ex.getMessage(), ex);
        AbstractExceptionHandler<T> handler = null;

        // find a handler for the exception
        // if no handler is found,
        // loop into for its cause (ex.getCause())

        while (ex != null) {

            handler = handlers.get(ex.getClass());

            if (handler != null) // found a handler
                break;

            ex = (T) ex.getCause();
        }

        if (handler != null) // a handler is found
            return Optional.of(handler.getErrorResponse(ex));

        return Optional.empty();
    }

}
