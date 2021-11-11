package com.deloitte.nextgen.framework.commons.payload.request.error;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author nishmehta on 09/03/2021 4:57 PM
 * @project ng-error-dto
 */

@Data
public class ErrorContextRequest {

    private Long referenceId;

    @NotBlank
    private String resourceUrl;

    private String serverName;

    @NotBlank
    private String serviceName;

    @NotBlank
    private String correlationId;

    @NotNull
    private ErrorLogRequest errorLog;

}