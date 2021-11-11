package com.deloitte.nextgen.framework.commons.exceptions;

/**
 *
 * @deprecated Use any exception which extends {@link ApiException} instead
 *
 */
@Deprecated
public class NgUsernameNotFoundException extends ApiException {

    private static final String MESSAGE = "Username not Found in the System.";

    public NgUsernameNotFoundException(int code, String message) {
        super(code, message);
    }

    public NgUsernameNotFoundException(int code) {
        super(code, MESSAGE);
    }
}
