package com.deloitte.nextgen.framework.commons.spi.internal.writer;

import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;
import com.deloitte.nextgen.framework.commons.spi.AuditLogWriter;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleAuditLogWriter implements AuditLogWriter {

    private ObjectMapper jacksonObjectMapper;

    public ConsoleAuditLogWriter(ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    public void writeLog(AuTxnLogContextDto contextDto) {
        try {
            log.info(LogMarker.AUDIT, "Writing audit data");
            log.info(LogMarker.AUDIT, jacksonObjectMapper.writeValueAsString(contextDto.getAuTxnLog().getAuditData()));
        } catch (Exception e) {
            log.error("Error while printing audit log : ", e);
        }
    }
}
