package com.deloitte.nextgen.framework.persistence.entity.type.zero;

import com.deloitte.nextgen.framework.persistence.annotations.CreatedBy;
import com.deloitte.nextgen.framework.persistence.annotations.UpdatedBy;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.BaseEntity;
import com.deloitte.nextgen.framework.persistence.generators.UniqueTransactionSequenceGenerator;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@MappedSuperclass
@Data
public class TypeZeroBaseEntity<P> extends BaseEntity {

    @Column(length = PersistenceConstants.Field.USER_ID_LENGTH)
    @CreatedBy
    private String createUserId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;

    @Column(length = PersistenceConstants.Field.USER_ID_LENGTH)
    @UpdatedBy
    private String updateUserId;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDt;

    @Column(nullable = false)
    @GeneratorType(type = UniqueTransactionSequenceGenerator.class, when = GenerationTime.ALWAYS)
    private Long uniqueTransId;
}
