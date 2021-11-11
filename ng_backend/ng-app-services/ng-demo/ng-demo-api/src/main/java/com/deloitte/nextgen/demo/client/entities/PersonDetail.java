package com.deloitte.nextgen.demo.client.entities;


import com.deloitte.nextgen.demo.client.entities.generated.PersonDetailPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Data
@Entity
@Table(name = PersonDetail.TABLE_NAME)
@IdClass(PersonDetailPK.class)
@EntityType(type = TypeEnum.TWO)
public class PersonDetail extends TypeTwoBaseEntity<PersonDetailPK> {
    @Transient
    public static final String TABLE_NAME = "Person_Detail";
    @Id
    protected LocalDate effBeginDt;
    @Id
    private Long id;
    @Id
    private Long personalDetailId;
    private String personName;
    private String personData;
    private String personAddr;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumns ({
            @JoinColumn(name = "person_address_id_fk", referencedColumnName = "id"),
            @JoinColumn(name = "address_id_fk", referencedColumnName = "personalDetailId"),
            @JoinColumn(name = "eff_begin_date_fk", referencedColumnName = "effBeginDt")
    })
    private Collection<PersonAddress> addresses;



}
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DC_ADDRESS_0SQ")
//    @SequenceGenerator(
//            name = "DC_ADDRESS_0SQ",
//            sequenceName =  "DC_ADDRESS_0SQ",
//            allocationSize = PersistenceConstants.Sequence.ALLOCATION_SIZE
//    )