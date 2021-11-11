package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.ConfigureEntityType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.reflection.XClass;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@Slf4j
public class EntityTypeOne implements ConfigureEntityType {

    @Override
    public boolean isEntityTypeMatches(XClass xClazz) {
        return xClazz.getAnnotation(EntityType.class) != null && getEntityType().equals(xClazz.getAnnotation(EntityType.class).type());
    }

    @Override
    public TypeEnum getEntityType() {
        return TypeEnum.ONE;
    }
}
