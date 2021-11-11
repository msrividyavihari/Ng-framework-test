package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.annotations.CreatedBy;
import com.deloitte.nextgen.framework.persistence.annotations.UpdatedBy;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Data
public class EntityInformation {

    private final Class<?> type;

    @Setter(AccessLevel.NONE)
    private String entityName;

    private final TypeEnum entityType;

    private final Optional<Field> createdByField;

    private final Optional<Field> updatedByField;


    @Setter(AccessLevel.NONE)
    private List<String> additionalEntitiesToPersist;

    public EntityInformation(Class<?> type, TypeEnum entityType) {

        this.type = type;

        Field[] f = FieldUtils.getFieldsWithAnnotation(type, CreatedBy.class);
        this.createdByField = Optional.ofNullable(f.length == 0 ? null : f[0]);

        f = FieldUtils.getFieldsWithAnnotation(type, UpdatedBy.class);
        this.updatedByField = Optional.ofNullable(f.length == 0 ? null : f[0]);

        this.entityType = entityType;

    }

    public void addAdditionalEntities(String entityName) {
        if (additionalEntitiesToPersist == null) {
            additionalEntitiesToPersist = new ArrayList<>();
        }
        additionalEntitiesToPersist.add(entityName);
    }

    public String getEntityName() {
        return type.getName();
    }
}
