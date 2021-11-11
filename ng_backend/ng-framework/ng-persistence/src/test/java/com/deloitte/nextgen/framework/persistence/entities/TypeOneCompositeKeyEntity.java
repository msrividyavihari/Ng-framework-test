package com.deloitte.nextgen.framework.persistence.entities;

import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TypeOneCompositeKeyEntity extends TypeOneBaseEntity<TypeOneCompositeKey> {

    @Id
    private TypeOneCompositeKey compositeKey;

    private String testing;

}
