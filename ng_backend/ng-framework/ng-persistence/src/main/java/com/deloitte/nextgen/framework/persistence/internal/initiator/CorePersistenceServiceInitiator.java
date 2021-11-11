package com.deloitte.nextgen.framework.persistence.internal.initiator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.service.CorePersistenceServiceImpl;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.registry.StandardServiceInitiator;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import java.util.Map;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class CorePersistenceServiceInitiator implements StandardServiceInitiator<CorePersistenceService> {

    public static final CorePersistenceServiceInitiator INSTANCE = new CorePersistenceServiceInitiator();

    @Override
    public CorePersistenceService initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
        configurationValues.put("javax.persistence.validation.mode", "none");
        log.info(LogMarker.PERSISTENCE, "Initiating Core Persistence Service");
        return new CorePersistenceServiceImpl(registry);
    }

    @Override
    public Class<CorePersistenceService> getServiceInitiated() {
        return CorePersistenceService.class;
    }
}
