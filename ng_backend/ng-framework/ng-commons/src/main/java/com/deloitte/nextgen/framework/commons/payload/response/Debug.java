package com.deloitte.nextgen.framework.commons.payload.response;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Data
public class Debug<T extends Throwable> {

    private String message;

    private String stackTrace;

    public Debug(String message, T ex) {
        this.message = message;
        this.stackTrace = ExceptionUtils.getStackTrace(ex);
    }
}
