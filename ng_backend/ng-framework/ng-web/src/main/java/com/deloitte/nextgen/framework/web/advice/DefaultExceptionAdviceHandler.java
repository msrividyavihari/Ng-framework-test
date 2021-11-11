package com.deloitte.nextgen.framework.web.advice;

import com.deloitte.nextgen.framework.commons.CorrelationIdHolder;
import com.deloitte.nextgen.framework.commons.enums.MessageType;
import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorLogRequest;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.framework.commons.payload.response.Debug;
import com.deloitte.nextgen.framework.commons.payload.response.ErrorResponse;
import com.deloitte.nextgen.framework.commons.spi.ExceptionWriter;
import com.deloitte.nextgen.framework.exceptions.ErrorResponseComposer;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@RestControllerAdvice
public class DefaultExceptionAdviceHandler<T extends Throwable> {

    private final ErrorResponseComposer<T> errorResponseComposer;

    private final ExceptionWriter exceptionWriter;

    public DefaultExceptionAdviceHandler(ErrorResponseComposer<T> errorResponseComposer, ExceptionWriter exceptionWriter) {

        this.errorResponseComposer = errorResponseComposer;
        this.exceptionWriter = exceptionWriter;
        log.info(LogMarker.WEB, "Initialized DefaultExceptionAdviceHandler");
    }


    @RequestMapping(produces = "application/json")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleException(T ex, HttpServletRequest request, HttpServletResponse response) throws T {

        Debug<T> debug = new Debug(ex.getMessage(), ex);
        ApiResponse<ErrorResponse> errorResponse = errorResponseComposer.compose(ex)
                .orElse(
                        ApiResponse.with(HttpStatus.INTERNAL_SERVER_ERROR, 500)
                                .type(MessageType.ERROR)
                                .notify(true)
                                .description("An unknown exception occurred. Please contact API developer")
                                .error(new ErrorResponse(debug))
                );


        sendErrorDetails(request, ex);

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    public void sendErrorDetails(HttpServletRequest request, T ex) {

        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.error(LogMarker.WEB, "Unable to resolve host name", e);
        }

        ErrorContextRequest errorContextDTO = new ErrorContextRequest();
        errorContextDTO.setCorrelationId(CorrelationIdHolder.getId());
        errorContextDTO.setResourceUrl(request.getRequestURI());
        errorContextDTO.setServerName(hostName);

        ErrorLogRequest el = new ErrorLogRequest();
        el.setExceptionSummary(ex.getMessage());
        el.setExceptionDetail(ExceptionUtils.getStackTrace(ex));

        errorContextDTO.setErrorLog(el);

        exceptionWriter.write(errorContextDTO);
    }
}
