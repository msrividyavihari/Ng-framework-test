package com.deloitte.nextgen.framework.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.RowId;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@RowId("ROWID")
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date archiveDt = Date.from(LocalDate.of(2999, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

    /*@Column(insertable = false, updatable = false)
    private String rowid;*/

}
