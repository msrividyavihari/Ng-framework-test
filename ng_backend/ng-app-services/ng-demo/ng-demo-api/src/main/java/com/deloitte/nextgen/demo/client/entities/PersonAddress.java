package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.entities.generated.PersonAddressPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = PersonAddress.TABLE_NAME)
@EntityType(type = TypeEnum.TWO)
@Data
@IdClass(PersonAddressPK.class)
public class PersonAddress extends TypeTwoBaseEntity<PersonAddressPK> {

    @Transient
    public static final String TABLE_NAME="Person_Address";
    @Id
    private long id;
    @Id
    private long addressId;
    @Id
    protected LocalDate effBeginDt;
    private String city;
    private String street;



    @ManyToOne
    private PersonDetail personDetail;
}
