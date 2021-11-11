package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class CorePersistenceIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

        final CorePersistenceService corePersistenceService = serviceRegistry.getService(CorePersistenceService.class);
        final EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        log.info(LogMarker.PERSISTENCE, "Initiating integrator Listeners");

        listenerRegistry.appendListeners(EventType.PRE_INSERT, new EntityTypePreInsertListener(corePersistenceService));
        listenerRegistry.appendListeners(EventType.PRE_UPDATE, new EntityTypePreUpdateListener(corePersistenceService));
        listenerRegistry.appendListeners(EventType.POST_UPDATE, new EntityTypePostUpdateListener(corePersistenceService));
        listenerRegistry.appendListeners(EventType.POST_DELETE, new EntityTypePostDeleteListener(corePersistenceService));
        listenerRegistry.appendListeners(EventType.SAVE_UPDATE, new EntityTypeSaveOrUpdateListener());

        log.info(LogMarker.PERSISTENCE, "Initiated all Listener");

    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // Nothing to disintegrate
    }
}
