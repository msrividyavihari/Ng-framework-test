package com.deloitte.nextgen.ssp.exceptionhandlers;

import com.deloitte.nextgen.framework.commons.exceptions.ApiException;

public class ARSSPStgCustomException extends ApiException {
    public ARSSPStgCustomException(String message) {
        super(0,message);
    }
}
