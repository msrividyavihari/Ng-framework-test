package com.deloitte.nextgen.framework.commons.exceptions;

/**
 *
 * @deprecated Use any exception which extends {@link ApiException} instead
 *
 */
@Deprecated
public class NgUserInfoNotFoundException extends ApiException {

    private static final String MESSAGE = "User Information not found";

    public NgUserInfoNotFoundException(int code, String message) {
        super(code, message);
    }

    public NgUserInfoNotFoundException(int code) {
        super(code, MESSAGE);
    }
}
