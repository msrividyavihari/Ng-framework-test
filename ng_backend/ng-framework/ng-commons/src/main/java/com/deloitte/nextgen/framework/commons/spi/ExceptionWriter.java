package com.deloitte.nextgen.framework.commons.spi;

import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;

/**
 * @author nishmehta on 27/03/2021 12:36 PM
 * @project ng-framework
 */
public interface ExceptionWriter {

    void write(ErrorContextRequest errorContextDTO);
}
