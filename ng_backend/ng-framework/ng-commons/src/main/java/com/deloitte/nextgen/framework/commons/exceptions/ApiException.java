package com.deloitte.nextgen.framework.commons.exceptions;

import lombok.Data;

/**
 * @author nishmehta on 26/10/2020 3:42 PM
 * @since v0.0.1-SNAPSHOT
 */

@Data
public class ApiException extends RuntimeException {

    private final int code;

    private final String message;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }
}
