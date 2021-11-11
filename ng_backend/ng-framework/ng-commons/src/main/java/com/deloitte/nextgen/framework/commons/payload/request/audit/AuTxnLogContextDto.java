package com.deloitte.nextgen.framework.commons.payload.request.audit;

import com.deloitte.nextgen.framework.commons.enums.AuditContextType;
import lombok.Data;

@Data
public class AuTxnLogContextDto {

    private AuTxnLogDto auTxnLog;

    private AuditContextType contextType;

    private String contextId;

    private String correlationId;
}
