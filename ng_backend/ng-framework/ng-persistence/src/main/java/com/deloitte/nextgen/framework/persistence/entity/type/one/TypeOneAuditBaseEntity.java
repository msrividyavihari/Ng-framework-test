package com.deloitte.nextgen.framework.persistence.entity.type.one;

import com.deloitte.nextgen.framework.persistence.annotations.CreatedBy;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@MappedSuperclass
@Data
public class TypeOneAuditBaseEntity<P> extends BaseEntity {

    @Column(length = PersistenceConstants.Field.USER_ID_LENGTH)
    private String createUserId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createDt;

    @Column(length = PersistenceConstants.Field.USER_ID_LENGTH)
    private String updateUserId;

    @Column
    private LocalDateTime updateDt;

    @Column(length = PersistenceConstants.Field.USER_ID_LENGTH)
    @CreatedBy
    private String auditUserId;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date auditDt;

    @Column(nullable = false)
    private Long uniqueTransId;
}
