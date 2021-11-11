package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.demo.client.enums.ContactType;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @author nishmehta
 * @since 16/08/2021 2:11 PM
 */

@Data
@Entity
@Table(name = DcEmail.TABLE_NAME)
@EntityType(type = TypeEnum.ZERO)
public class DcEmail extends TypeZeroBaseEntity<Long> {
    @Transient
    public static final String TABLE_NAME = "DC_EMAIL";

    @Transient
    private static final String PK_SEQUENCE_GEN = TABLE_NAME + "_1SQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE_GEN)
    @SequenceGenerator(
            name = DcEmail.PK_SEQUENCE_GEN,
            sequenceName = DcEmail.PK_SEQUENCE_GEN,
            allocationSize = PersistenceConstants.Sequence.ALLOCATION_SIZE
    )
    private Long id;

    @Column(length = DemoConstants.ColumnLength.CONTACT_TYPE_CD)
    private ContactType contactTypeCd;

    @Email
    @Column(length = DemoConstants.ColumnLength.EMAIL)
    private String emailId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DcIndividual.DcIndividualFKName)
    private DcIndividual dcIndividual;

}
