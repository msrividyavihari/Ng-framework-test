package com.deloitte.nextgen.framework.persistence.junit;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.junit.generated.TypeTwoEntityPK;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Entity
@EntityType(type = TypeEnum.TWO)
@Data
@IdClass(TypeTwoEntityPK.class)
@Table(name = "SAMPLE_TWO")
@ToString
public class TypeTwoEntity extends TypeTwoBaseEntity<TypeTwoEntityPK> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long seqNum;

    @Id
    private LocalDate effBeginDt;

    @Column(length = 20)
    private String title;

    private String testing;

    private long indivId;

    private boolean isActive;


}
