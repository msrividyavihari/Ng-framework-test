package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.ConfigureEntityType;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public class EntitiesConfigurations {

    private Map<String, EntityInformation> entityInformationMap;

    private List<ConfigureEntityType> entityVisitors;

    private CorePersistenceService corePersistenceService;

    public EntitiesConfigurations(CorePersistenceService corePersistenceService) {
        this.corePersistenceService = corePersistenceService;
        this.entityInformationMap = new HashMap<>();

        this.entityVisitors = new ArrayList<>();
        this.entityVisitors.add(new EntityTypeNone());
        this.entityVisitors.add(new EntityTypeZero());
        this.entityVisitors.add(new EntityTypeOne());
        this.entityVisitors.add(new EntityTypeTwo());

        configure();
    }

    private void configure() {
        entityVisitors.forEach(ev -> ev.configure(corePersistenceService, entityInformationMap));
    }

    public boolean containsEntityTypeAnnotation(String entityName) {
        return getEntityInformation(entityName) != null;
    }

    public boolean isTypeZero(String entityName) {
        return getEntityInformation(entityName).getEntityType() == TypeEnum.ZERO;
    }

    public boolean isTypeOne(String entityName) {
        return getEntityInformation(entityName).getEntityType() == TypeEnum.ONE;
    }

    public boolean isTypeTwo(String entityName) {
        return getEntityInformation(entityName).getEntityType() == TypeEnum.TWO;
    }

    public EntityInformation getEntityInformation(String entityName) {
        return entityInformationMap.get(entityName);
    }
}
