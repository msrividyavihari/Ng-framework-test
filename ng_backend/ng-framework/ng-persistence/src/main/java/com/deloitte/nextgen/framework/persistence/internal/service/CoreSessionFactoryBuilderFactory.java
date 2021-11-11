package com.deloitte.nextgen.framework.persistence.internal.service;

import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;
import org.hibernate.service.ServiceRegistry;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public class CoreSessionFactoryBuilderFactory implements SessionFactoryBuilderFactory {

    @Override
    public SessionFactoryBuilder getSessionFactoryBuilder(MetadataImplementor metadata, SessionFactoryBuilderImplementor defaultBuilder) {

        ServiceRegistry serviceRegistry = metadata.getMetadataBuildingOptions().getServiceRegistry();
        final CorePersistenceService corePersistenceService = serviceRegistry.getService(CorePersistenceService.class);

        corePersistenceService.initiate(metadata, serviceRegistry);

        return defaultBuilder;
    }
}
