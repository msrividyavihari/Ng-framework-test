package com.deloitte.nextgen.framework.commons.exceptions;

/**
 * @author nishmehta on 06/11/2020 1:37 PM
 * @since v0.0.1-SNAPSHOT
 */
public class ResourceConflictException extends ApiException {

    public ResourceConflictException(int code, String message) {
        super(code, message);
    }

    public ResourceConflictException(int code, String message, Throwable t) {
        super(code, message, t);
    }
}
