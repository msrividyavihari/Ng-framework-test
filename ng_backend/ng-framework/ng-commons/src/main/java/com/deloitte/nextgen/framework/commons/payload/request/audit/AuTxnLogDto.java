package com.deloitte.nextgen.framework.commons.payload.request.audit;

import com.deloitte.nextgen.framework.commons.enums.AuditLogType;
import lombok.Data;

@Data
public class AuTxnLogDto {

    private Long txnLogId;

    private String serviceName;

    private AuditLogType modeCode;

    private Character operationName;

    private String actionName;

    private String pageId;

    private String workstationId;

    private String ipAddress;

    private String sessionId;

    private Character entityType;

    private String entityKey;

    private Long locId;

    private String locName;

    private String userName;

    private String eventCode;

    private String auditData;

    private String batchId;

    private String batchName;

}
