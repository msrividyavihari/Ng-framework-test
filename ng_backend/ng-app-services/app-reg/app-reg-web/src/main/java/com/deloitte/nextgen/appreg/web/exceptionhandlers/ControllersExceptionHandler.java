package com.deloitte.nextgen.appreg.web.exceptionhandlers;

import com.deloitte.nextgen.appreg.web.response.utility.ResponseUtility;
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

   private final ResponseUtility responseUtility ;

    @ExceptionHandler(IllegalStateException.class)
    //public final ResponseEntity<Object>  handleIllegalStateException
    public final ResponseEntity<ApiResponse<String>>  handleIllegalStateException
            (IllegalStateException exceptionObject){
    log.error("IllegalStateException" , exceptionObject);
        return ApiResponse.error(509).data(exceptionObject.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse<String>>  handleOtherException(Exception exceptionObject){
        log.error("Exception" , exceptionObject);
        return ApiResponse.error(417).data(exceptionObject.getMessage());

    }
    @ExceptionHandler(AppRegCustomException.class)
    public final ResponseEntity<ApiResponse<String>>  handleOtherException(AppRegCustomException exceptionObject){
        log.error("Exception" , exceptionObject);
        return ApiResponse.error(500).data(exceptionObject.getMessage());
    }

}
