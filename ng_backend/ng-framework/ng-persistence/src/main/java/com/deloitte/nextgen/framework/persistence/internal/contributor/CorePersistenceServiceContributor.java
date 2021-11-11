package com.deloitte.nextgen.framework.persistence.internal.contributor;

import com.deloitte.nextgen.framework.persistence.internal.initiator.CorePersistenceServiceInitiator;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.spi.ServiceContributor;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

public class CorePersistenceServiceContributor implements ServiceContributor {

    @Override
    public void contribute(StandardServiceRegistryBuilder serviceRegistryBuilder) {
        serviceRegistryBuilder.addInitiator(CorePersistenceServiceInitiator.INSTANCE);
    }
}
