package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.EntityAddWorkUnit;
import com.deloitte.nextgen.framework.persistence.internal.EntityTypeProcess;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class EntityTypePostDeleteListener extends BaseEntityTypeListener implements PostDeleteEventListener {

    private static final long serialVersionUID = 87439135184637641L;

    public EntityTypePostDeleteListener(CorePersistenceService corePersistenceService) {
        super(corePersistenceService);
        log.info(LogMarker.PERSISTENCE, "Initiated Entity Post Delete Listener");
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {

        String entityName = event.getPersister().getEntityName();

        EntityTypeProcess process = getCorePersistenceService().getEntityTypeManager().get(event.getSession());
        if (getCorePersistenceService().getEntityConfigurations().containsEntityTypeAnnotation(entityName)) {

            if (getCorePersistenceService().getEntityConfigurations().isTypeOne(entityName)) {

                process.addWorkUnit(new EntityAddWorkUnit(
                        event.getSession(),
                        event.getPersister().getEntityName(),
                        event.getEntity(),
                        event.getId(),
                        getCorePersistenceService(),
                        event.getPersister(),
                        event.getDeletedState()));


            } else if (getCorePersistenceService().getEntityConfigurations().isTypeTwo(entityName)) {
                log.info("in type two");
                process.addWorkUnit(new EntityAddWorkUnit(
                        event.getSession(),
                        event.getPersister().getEntityName(),
                        event.getEntity(),
                        event.getId(),
                        getCorePersistenceService(),
                        event.getPersister(),
                        event.getDeletedState(),
                        "DELETE"));



            }
        }
    }



    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return true;
    }
}