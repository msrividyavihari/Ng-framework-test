package com.deloitte.nextgen.appreg.web.exceptionhandlers;

import com.deloitte.nextgen.framework.commons.exceptions.ApiException;

public class AppRegCustomException extends ApiException {

    public AppRegCustomException(String message) {
        super(0,message);
    }
}
