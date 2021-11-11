package com.deloitte.nextgen.framework.commons.spi;

import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;

public interface AuditLogWriter {
    void writeLog(AuTxnLogContextDto contextDto);

}
