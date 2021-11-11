package com.deloitte.nextgen.framework.commons.exceptions;

/**
 * @author nishmehta on 22/06/2021 12:40 PM
 * @since v0.0.2-SNAPSHOT
 */
public class IdentifierException extends ApiException {

    private static final String MESSAGE = "ID not valid exception";

    public IdentifierException(int code) {
        super(code, MESSAGE);
    }

    public IdentifierException(int code, String message) {
        super(code, message);
    }

    public IdentifierException(int code, String message, Throwable t) {
        super(code, message, t);
    }

}
