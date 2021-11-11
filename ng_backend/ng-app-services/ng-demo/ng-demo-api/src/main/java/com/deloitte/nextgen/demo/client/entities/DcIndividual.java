package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nishmehta on 14/06/2021 2:25 PM
 * @project ng-demo
 */

@Data
@Entity
@Table(name = DcIndividual.TABLE_NAME)
@EntityType(type = TypeEnum.ONE)
public class DcIndividual extends TypeOneBaseEntity<Long> {

    @Transient
    public static final String TABLE_NAME = "DC_INDIVIDUAL";

    @Transient
    public static final String DcIndividualFKName = "individualId";

    @Transient
    private static final String DcIndividualMappedByName = "dcIndividual";

    @Transient
    private static final String PK_SEQUENCE_GEN = TABLE_NAME + "_1SQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE_GEN)
    @SequenceGenerator(
            name = DcIndividual.PK_SEQUENCE_GEN,
            sequenceName = DcIndividual.PK_SEQUENCE_GEN,
            allocationSize = PersistenceConstants.Sequence.ALLOCATION_SIZE
    )
    private Long individualId;

    @Column(length = DemoConstants.ColumnLength.FIRST_NAME)
    private String firstName;

    @Column(length = DemoConstants.ColumnLength.LAST_NAME)
    private String lastName;

    @Column(length = DemoConstants.ColumnLength.MIDDLE_NAME)
    private String middleName;

    @Column(length = DemoConstants.ColumnLength.SSN)
    private String ssn;

    private LocalDate birthDate;

    private LocalTime birthTime;

    @Column(length = DemoConstants.ColumnLength.PHONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = DcIndividualMappedByName, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DcPhone> phoneNumbers;

    @Column(length = DemoConstants.ColumnLength.PHONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = DcIndividualMappedByName, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DcEmail> emailIds;

    @Valid
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = DcIndividualMappedByName, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DcAddress> addresses;

    public void addAddress(DcAddress dcAddress) {
        if (CollectionUtils.isEmpty(addresses)) {
            addresses = new HashSet<>();
        }
        dcAddress.setDcIndividual(this);
        addresses.add(dcAddress);
    }

    public void removeAddress(DcAddress dcAddress) {
        dcAddress.setDcIndividual(null);
        addresses.remove(dcAddress);
    }

    public void addPhoneNumber(DcPhone dcPhone) {
        if (CollectionUtils.isEmpty(phoneNumbers)) {
            phoneNumbers = new HashSet<>();
        }
        dcPhone.setDcIndividual(this);
        phoneNumbers.add(dcPhone);
    }

    public void removePhoneNumber(DcPhone dcPhone) {
        dcPhone.setDcIndividual(null);
        phoneNumbers.remove(dcPhone);
    }

    public void addEmail(DcEmail dcEmail) {
        if (CollectionUtils.isEmpty(emailIds)) {
            emailIds = new HashSet<>();
        }
        dcEmail.setDcIndividual(this);
        emailIds.add(dcEmail);
    }

    public void removeEmail(DcEmail dcEmail) {
        dcEmail.setDcIndividual(null);
        emailIds.remove(dcEmail);
    }

}
