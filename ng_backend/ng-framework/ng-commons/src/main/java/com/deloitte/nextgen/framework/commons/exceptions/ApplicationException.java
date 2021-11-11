package com.deloitte.nextgen.framework.commons.exceptions;


/**
 * This class provides a wrapper for all the application exceptions. This is the super class for all
 * the application level exceptions.
 *
 * @author Deloitte
 * @version %Revision%
 * @deprecated
 */
@Deprecated
public class ApplicationException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7632587115241407253L;

    /**
     * Constructor for ApplicationException
     */
    public ApplicationException() {
        super();
    }

    /**
     * Constructor for ApplicationException
     *
     * @param message String
     */
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String string, Exception e) {
        super(string, e);
    }

    /**
     * Invokes the Logger and log the exception information
     *
     * @param aLevel     LogLevel
     * @param aMessage   String
     * @param aThrowable Throwable
     */
    public void log() {
        //TODO Need to implement
    }

    /**
     * PrintStackTrace is overridden here to avoid the stack trace being written to the stderr.
     * Instead, this method writes the trace in the Exception log.
     */
    @Override
    public void printStackTrace() {
        //TODO Need to implement
    }
}
