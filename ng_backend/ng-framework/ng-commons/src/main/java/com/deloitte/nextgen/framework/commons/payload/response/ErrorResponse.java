package com.deloitte.nextgen.framework.commons.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @author nishmehta on 08/06/2021 1:33 PM
 * @project ng-framework
 */

@Data
public class ErrorResponse {

    @JsonProperty("detail")
    private Debug debug;

    private Collection<InvalidFieldError> invalidFields;

    public ErrorResponse(Debug debug) {
        this.debug = debug;
    }

    public ErrorResponse(Collection<InvalidFieldError> invalidFields) {
        this.invalidFields = invalidFields;
    }

    public ErrorResponse(Debug debug, Collection<InvalidFieldError> invalidFields) {
        this.debug = debug;
        this.invalidFields = invalidFields;
    }

}
