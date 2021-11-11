package com.deloitte.nextgen.framework.persistence.junit;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.junit.generated.TypeOneEntityPK;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Entity
@EntityType(type = TypeEnum.ONE)
@Data
@IdClass(TypeOneEntityPK.class)
@Table(name = "SAMPLE_ONE")
@ToString
public class TypeOneEntity extends TypeOneBaseEntity<TypeOneEntityPK> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seqNum;

    @Column(length = 20)
    private String title;

    private String testing;

    private long indivId;

    private boolean isActive;

    private double dailyIncome;

    private float avgMonthlyIncome;

    private char middleInitial;

    private short siblings;
}
