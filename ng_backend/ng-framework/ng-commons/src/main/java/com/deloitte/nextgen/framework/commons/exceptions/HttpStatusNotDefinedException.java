package com.deloitte.nextgen.framework.commons.exceptions;

/**
 * @author nishmehta on 27/10/2020 12:08 PM
 * @since v0.0.1-SNAPSHOT
 */
public class HttpStatusNotDefinedException extends ApiException {

    public HttpStatusNotDefinedException(int code, String message) {
        super(code, message);
    }

    public HttpStatusNotDefinedException(int code, String message, Throwable t) {
        super(code, message, t);
    }
}
