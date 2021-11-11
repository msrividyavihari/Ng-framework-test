package com.deloitte.nextgen.framework.persistence.internal.service.spi;

import com.deloitte.nextgen.framework.persistence.internal.EntityTypeManager;
import com.deloitte.nextgen.framework.persistence.internal.service.EntitiesConfigurations;
import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public interface CorePersistenceService extends Service {

    boolean isInitiated();

    void initiate(MetadataImplementor metadata, ServiceRegistry serviceRegistry);

    ReflectionManager getReflectionManager();

    MetadataImplementor getMetadata();

    EntityTypeManager getEntityTypeManager();

    EntitiesConfigurations getEntityConfigurations();

    String getAuditorName();
}
