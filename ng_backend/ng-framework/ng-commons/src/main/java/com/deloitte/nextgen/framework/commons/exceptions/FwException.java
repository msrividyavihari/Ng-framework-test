package com.deloitte.nextgen.framework.commons.exceptions;

/**
 *
 * @deprecated Use any exception which extends {@link ApiException} instead
 *
 */
@Deprecated
public class FwException extends Exception {
    public FwException(Object abstractValueObject, int critical, Exception e) {
    }
}
