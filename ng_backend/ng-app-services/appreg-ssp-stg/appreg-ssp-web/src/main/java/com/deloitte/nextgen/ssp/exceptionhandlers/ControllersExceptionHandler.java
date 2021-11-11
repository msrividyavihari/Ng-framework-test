package com.deloitte.nextgen.ssp.exceptionhandlers;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.deloitte.nextgen.controllers")
@RequiredArgsConstructor
@Slf4j
public class ControllersExceptionHandler {
    @ExceptionHandler(ARSSPStgCustomException.class)
    public final ResponseEntity<ApiResponse<String>> handleOtherException(ARSSPStgCustomException exceptionObject){
        log.error("Exception" , exceptionObject);
        return ApiResponse.error(500).data(exceptionObject.getMessage());
    }
}
