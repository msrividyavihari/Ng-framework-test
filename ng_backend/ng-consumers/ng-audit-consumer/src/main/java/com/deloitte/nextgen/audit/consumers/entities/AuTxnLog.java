package com.deloitte.nextgen.audit.consumers.entities;

import com.deloitte.nextgen.framework.commons.enums.AuditLogType;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = AuTxnLog.TABLE_NAME)
@EntityType
public class AuTxnLog extends TypeZeroBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "AU_TXN_LOG";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_TXN_LOG_1SQ")
    @Column(name = "TXN_LOG_ID")
    private Long txnLogId;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "MODE_CD")
    private AuditLogType modeCode;

    @Column(name = "OPERATION_NAME")
    private Character operationName;

    @Column(name = "ACTION_NAME")
    private String actionName;

    @Column(name = "PAGE_ID")
    private String pageId;

    @Column(name = "WORKSTATION_ID")
    private String workstationId;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "ENTITY_TYPE")
    private Character entityType;

    @Column(name = "ENTITY_KEY")
    private String entityYKey;

    @Column(name = "LOC_ID")
    private Long locId;

    @Column(name = "LOC_NAME")
    private String locName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EVENT_CD")
    private String eventCode;

    @Column(name = "AUDIT_DATA")
    private String auditData;

    @Column(name = "BATCH_ID")
    private String batchId;

    @Column(name = "BATCH_TABLE")
    private String batchTable;

}
