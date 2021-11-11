package com.deloitte.nextgen.framework.commons.spi.internal.writer;

import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import com.deloitte.nextgen.framework.commons.spi.ExceptionWriter;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nishmehta on 05/04/2021 12:09 PM
 * @project ng-framework
 */

@Slf4j
public class ConsoleExceptionWriter implements ExceptionWriter {

    @Override
    public void write(ErrorContextRequest errorContextDTO) {
        log.info(LogMarker.EXCEPTION, "Writing error data");
        log.error(errorContextDTO.getErrorLog().getExceptionDetail());
    }
}
