package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Data
@Entity
@Table(name = ArAppIndividual.TABLE_NAME)
@EntityType(type = TypeEnum.ONE)
public class ArAppIndividual extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "AR_APP_INDIVIDUAL";

    @Transient
    private static final String PK_SEQUENCE_GEN = TABLE_NAME + "_1SQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE_GEN)
    @SequenceGenerator(
            name = ArAppIndividual.PK_SEQUENCE_GEN,
            sequenceName = ArAppIndividual.PK_SEQUENCE_GEN,
            allocationSize = PersistenceConstants.Sequence.ALLOCATION_SIZE
    )
    private Long arIndividualId;

    @Column(length = DemoConstants.ColumnLength.FIRST_NAME)
    private String firstName;

    @Column(length = DemoConstants.ColumnLength.LAST_NAME)
    private String lastName;

    @Column(length = DemoConstants.ColumnLength.MIDDLE_NAME)
    private String middleName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ArApp.ArAppFKName)
    private ArApp arApp;
}
