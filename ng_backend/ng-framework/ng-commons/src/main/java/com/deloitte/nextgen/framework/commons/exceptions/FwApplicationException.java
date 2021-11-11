/**
 * This package is used for all Exceptions information related to Framework Exception classes.
 * Framework exception classes that are all self-logging.
 */
package com.deloitte.nextgen.framework.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * The class <code> FwApplicationException </code> is the Root of Framework
 * Application Exception. This exception is used only during authentication and
 * authorization procedures.
 * <p>
 * The logging category is always BATCH with a level of ERROR to stand out when
 * logged. However, ERROR,WARNING,INFO,DEBUG level is usually not logged in
 * production. Therefore, separate security audits must be created by the error
 * handler if desired.
 * </p>
 *
 * @deprecated Use any exception which extends {@link ApiException} instead
 *
 */
@Slf4j
@Deprecated
public class FwApplicationException extends Exception {
    /**
     * Field t.
     */
    private Throwable t = null;
    /**
     * Field level.
     */
    private int level = 0;

    /**
     * FwApplicationException constructor.
     */
    public FwApplicationException() {
        super("FwApplicationException");

        t = this;
        level = 7;
    }

    /**
     * FwApplicationException constructor. Logs category and priority of debug.
     * Stack trace unavailable.
     *
     * @param message to returns the detail message string of log.
     */

    public FwApplicationException(String message) {
        // Creation date: (01/01/2002 12:00:00 AM)
        super(message);

        this.t = this;
        this.level = 7;

    }

    public FwApplicationException(String message, Throwable t) {
        // Creation date: (01/01/2002 12:00:00 AM)
        super(message);

        this.t = t;
        this.level = 7;
    }

    /**
     * FwApplicationException constructor. Stack trace available.
     *
     * @param category contains the type of category ApplicationException
     * @param level   contains the type of level of exception.
     * @param t       contains the message of exception when throwable.
     */

    public FwApplicationException(String category, int level, Throwable t) {
        // Creation date: (01/01/2002 12:00:00 AM)

        this.level = level;
        this.t = t;
    }

    /**
     * FwApplicationException constructor. Logs category of provided object's fully
     * qualified class name. Stack trace available. *
     *
     * @param object contains the exception object.
     * @param level contians the type of the level in int,
     * @param t     contians the message when throwable.
     */

    public FwApplicationException(Object object, int level, Throwable t) {
        // Creation date: (01/01/2002 12:00:00 AM)
        super(t.getMessage());

        this.level = level;
        this.t = t;

        log.error((String) object, this.t);
    }

    /**
     * Throwable accessor.
     *
     * @return Throwable returns the exception of throwable.
     */

    public Throwable getThrowable() {
        // Creation date: (01/01/2002 12:00:00 AM)
        return t;
    }

    /**
     * Log level accessor.
     *
     * @return int returns the value of exception.
     */

    public int getLevel() {
        // Creation date: (01/01/2002 12:00:00 AM)
        return level;
    }

}