package com.deloitte.nextgen.framework.commons.exceptions;

/**
 * @author nishmehta on 27/10/2020 12:14 PM
 * @since v0.0.1-SNAPSHOT
 */
public class MessageNotAvailableException extends ApiException {

    public MessageNotAvailableException(int code, String message) {
        super(code, message);
    }

    public MessageNotAvailableException(int code, String message, Throwable t) {
        super(code, message, t);
    }
}
