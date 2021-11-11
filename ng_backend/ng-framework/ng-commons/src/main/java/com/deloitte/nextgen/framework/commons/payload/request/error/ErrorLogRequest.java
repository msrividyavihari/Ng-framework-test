package com.deloitte.nextgen.framework.commons.payload.request.error;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author nishmehta on 09/03/2021 4:57 PM
 * @project ng-error-dto
 */

@Data
public class ErrorLogRequest {

    @Setter(AccessLevel.NONE)
    private Timestamp exceptionDt = new Timestamp(Instant.now().getEpochSecond());

    private long exceptionNum;

    private char exceptionSeverity;

    @NotNull
    private String exceptionSummary;

    @NotNull
    private String exceptionDetail;

    private long referenceId;

    private String rowid;

}