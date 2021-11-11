package com.deloitte.nextgen.framework.commons.exceptions;

import lombok.Data;

/**
 * @author nishmehta on 26/10/2020 3:41 PM
 * @since v0.0.1-SNAPSHOT
 */

@Data
public class ResourceNotFoundException extends ApiException {

    private static final String MESSAGE = "No Resource Found";

    private String resourceName;

    private String resourceId;

    public ResourceNotFoundException(int code, String resourceName, String resourceId, Throwable t) {
        super(code, MESSAGE, t);
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    /**
     * An overloaded constructor to allow to pass the resource name and resource id.
     *
     * @param code
     * @author Uday Ala
     * @CreatedOn 07/14/2021 04:22 PM CST
     */
    public ResourceNotFoundException(int code, String resourceName, String resourceId) {
        super(code, MESSAGE);
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    /**
     * An overloaded constructor to allow to pass the custom message
     * and root cause.
     *
     * @param code
     * @author Uday Ala
     * @CreatedOn 07/14/2021 04:22 PM CST
     */
    public ResourceNotFoundException(int code, String message, Throwable t) {
        super(code, message, t);
    }

    /**
     * An overloaded constructor to allow to pass the custom message
     * for example "No AuthRep found for given Case # 12345"
     *
     * @param code
     * @author Uday Ala
     * @CreatedOn 07/14/2021 04:22 PM CST
     */
    public ResourceNotFoundException(int code, String message) {
        super(code, message);
    }
}
