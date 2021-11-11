package com.deloitte.nextgen.audit.consumers.entities;

import com.deloitte.nextgen.framework.commons.enums.AuditContextType;
import com.deloitte.nextgen.framework.commons.enums.AuditLogType;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = AuTxnLogContext.TABLE_NAME)
@EntityType
public class AuTxnLogContext extends TypeZeroBaseEntity<String> {

    @Transient
    public static final String TABLE_NAME = "AU_TXN_LOG_CONTEXT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_TXN_LOG_CONTEXT_0SQ")
    @Column(name = "CONTEXT_ID")
    private Long contextId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CONTEXT_TYPE")
    private AuditContextType contextType;

    @Column(name = "CORRELATION_ID")
    private String correlationId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTEXT_ID_FK", referencedColumnName = "CONTEXT_ID")
    private List<AuTxnLog> auTxnLogList;


}
