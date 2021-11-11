package com.deloitte.nextgen.framework.persistence.entity.type.one;

import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.generators.HistorySequenceGenerator;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@MappedSuperclass
@Data
public class TypeOneBaseEntity<P> extends TypeZeroBaseEntity<P> {

    @Column(nullable = false)
    @GeneratorType(type = HistorySequenceGenerator.class, when = GenerationTime.ALWAYS)
    private Long historySeq;
}
