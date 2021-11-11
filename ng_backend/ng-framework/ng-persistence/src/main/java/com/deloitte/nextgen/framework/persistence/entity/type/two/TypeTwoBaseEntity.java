package com.deloitte.nextgen.framework.persistence.entity.type.two;

import com.deloitte.nextgen.framework.commons.enums.HistoryNavInd;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@Data
@MappedSuperclass
public class TypeTwoBaseEntity<P> extends TypeOneBaseEntity {



    @Column
    private LocalDate effEndDt;

    @Column
    @Enumerated(EnumType.STRING)
    private HistoryNavInd histNavInd;

}

