package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.ConfigureEntityType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.common.reflection.XClass;

/**
 * @author sunshah on 05/16/2021 9 PM
 * @project ng-framework
 */
@Slf4j
public class EntityTypeTwo implements ConfigureEntityType {

    @Override
    public boolean isEntityTypeMatches(XClass xClazz) {
        return xClazz.getAnnotation(EntityType.class) != null && getEntityType().equals(xClazz.getAnnotation(EntityType.class).type());
    }

    @Override
    public TypeEnum getEntityType() {
        return TypeEnum.TWO;
    }
}
