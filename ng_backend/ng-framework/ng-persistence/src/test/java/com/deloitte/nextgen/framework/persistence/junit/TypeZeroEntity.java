package com.deloitte.nextgen.framework.persistence.junit;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.junit.generated.TypeZeroEntityPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Entity
@EntityType(type = TypeEnum.ZERO)
@Data
@IdClass(TypeZeroEntityPK.class)
@Table(name = "SAMPLE")
@NoArgsConstructor
@AllArgsConstructor
public class TypeZeroEntity extends TypeZeroBaseEntity<TypeZeroEntityPK> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @Column
    private LocalDate effBeginDt;

    private String name;

    private int age;

    private boolean isActive;

    private double dailyIncome;

    private float avgMonthlyIncome;

    private char middleInitial;

    private short siblings;
}
