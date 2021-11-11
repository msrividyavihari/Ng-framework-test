package com.deloitte.nextgen.framework.persistence.entity.type.two;

import com.deloitte.nextgen.framework.persistence.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@MappedSuperclass
@Data
public class TypeTwoEntity<P> extends BaseEntity {

    @Column
    private LocalDate effBeginDt;

    @Column
    private LocalDate effEndDt;

}

