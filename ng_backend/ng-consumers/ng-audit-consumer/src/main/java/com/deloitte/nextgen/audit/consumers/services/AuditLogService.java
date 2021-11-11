package com.deloitte.nextgen.audit.consumers.services;

import com.deloitte.nextgen.audit.consumers.entities.AuTxnLogContext;

public interface AuditLogService {

    AuTxnLogContext insert(AuTxnLogContext auditLog);

}
