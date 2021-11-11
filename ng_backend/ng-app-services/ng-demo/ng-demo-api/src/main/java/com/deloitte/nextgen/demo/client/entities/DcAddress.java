package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.demo.client.enums.AddressType;
import com.deloitte.nextgen.framework.commons.enums.ActiveType;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author nishmehta on 15/06/2021 7:37 PM
 * @project ng-demo
 */

@Data
@Entity
@Table(name = DcAddress.TABLE_NAME)
@EntityType(type = TypeEnum.ONE)
public class DcAddress extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_ADDRESS";

    @Transient
    private static final String PK_SEQUENCE_GEN = TABLE_NAME + "_1SQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE_GEN)
    @SequenceGenerator(
            name = DcAddress.PK_SEQUENCE_GEN,
            sequenceName = DcAddress.PK_SEQUENCE_GEN,
            allocationSize = PersistenceConstants.Sequence.ALLOCATION_SIZE
    )
    private Long addressId;

    @Column(length = DemoConstants.ColumnLength.ADDRESS_TYPE_CD)
    private AddressType addressTypeCd;

    @Column(length = DemoConstants.ColumnLength.ADDRESS_LINE_ONE)
    private String addressLineOne;

    @Column(length = DemoConstants.ColumnLength.ADDRESS_LINE_TWO)
    private String addressLineTwo;

    private Long city;

    private Long state;

    @Column(length = DemoConstants.ColumnLength.ZIPCODE)
    private String zipCode;

    @Column(length = PersistenceConstants.Number.ONE)
    private ActiveType activeSw;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DcIndividual.DcIndividualFKName)
    private DcIndividual dcIndividual;
}
