package com.deloitte.nextgen.framework.commons.payload.request.error;

import lombok.Data;

/**
 * @author nishmehta on 09/03/2021 4:57 PM
 * @project ng-error-dto
 */

@Data
public class ErrorLogDetailsRequest {

    private String contextId;

    private long contextNum;

    private String contextType;

    private long exceptionNum;

    private long referenceId;

    private String rowid;

}