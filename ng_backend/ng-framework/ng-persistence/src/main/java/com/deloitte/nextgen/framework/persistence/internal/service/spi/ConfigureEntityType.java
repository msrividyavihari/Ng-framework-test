package com.deloitte.nextgen.framework.persistence.internal.service.spi;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.internal.service.EntityInformation;
import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.annotations.common.reflection.XClass;

import java.util.Map;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

public interface ConfigureEntityType {

    default void configure(CorePersistenceService corePersistenceService, Map<String, EntityInformation> entityInformationMap) {

        final ReflectionManager rm = corePersistenceService.getReflectionManager();

        corePersistenceService.getMetadata().getEntityBindings()
                .stream()
                .map(clazz -> rm.classForName(clazz.getClassName()))
                .filter(this::isEntityTypeMatches)
                .forEach(xClazz -> entityInformationMap.put(xClazz.getName(), new EntityInformation(rm.toClass(xClazz), getEntityType())));
    }

    TypeEnum getEntityType();

    boolean isEntityTypeMatches(XClass xClazz);
}
