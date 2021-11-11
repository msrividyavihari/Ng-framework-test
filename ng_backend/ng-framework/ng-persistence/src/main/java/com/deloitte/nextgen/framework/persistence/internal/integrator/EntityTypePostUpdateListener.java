package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.EntityAddWorkUnit;
import com.deloitte.nextgen.framework.persistence.internal.EntityTypeProcess;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class EntityTypePostUpdateListener extends BaseEntityTypeListener implements PostUpdateEventListener {

    private static final long serialVersionUID = 576037236597109L;

    public EntityTypePostUpdateListener(CorePersistenceService corePersistenceService) {
        super(corePersistenceService);
        log.info(LogMarker.PERSISTENCE, "Initiated Entity Post Update Listener");
    }


    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return true;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {

        String entityName = event.getPersister().getEntityName();

        EntityTypeProcess process = getCorePersistenceService().getEntityTypeManager().get(event.getSession());
        if (getCorePersistenceService().getEntityConfigurations().containsEntityTypeAnnotation(entityName)) {
            //Type 1 or Type 2
            if (getCorePersistenceService().getEntityConfigurations().isTypeOne(entityName)) {

                try {


                    process.addWorkUnit(new EntityAddWorkUnit(
                            event.getSession(),
                            event.getPersister().getEntityName(),
                            event.getEntity(),
                            event.getId(),
                            getCorePersistenceService(),
                            event.getPersister(),
                            event.getOldState()));
                } catch (Exception e) {
                    log.error("EXCEPTION : ");
                    log.error(LogMarker.PERSISTENCE, e.getMessage(), e.toString());
                }

            } else if (getCorePersistenceService().getEntityConfigurations().isTypeTwo(entityName)) {
                //TODO To be implemented during Type 2 functionality

                if (getCorePersistenceService().getEntityConfigurations().isTypeTwo(entityName)) {
                    endDateBTable(event);
                }
                Class clazzMain = null;
                Object[] oldSate = event.getOldState();
                Object[] state = event.getState();
                try {
                    clazzMain = Class.forName(event.getPersister().getEntityName());


                    Field[] fields = clazzMain.getDeclaredFields();
                    for (Field field : fields) {
                        System.err.println(field.getName());
                    }
                    String[] names = event.getPersister().getEntityMetamodel().getPropertyNames();
                    Type[] types = event.getPersister().getEntityMetamodel().getPropertyTypes();

                    System.err.println(Arrays.asList(postUpdateDBState(event)));
                    for (int i = 0; i < names.length; i++) {
                        System.err.println("field " + names[i]
                                + ", old value " + oldSate[i] + ", new value " + state[i]);
                        System.err.println(types[i].getName());
                        System.err.println();
                    }

                    process.addWorkUnit(new EntityAddWorkUnit(
                            event.getSession(),
                            event.getPersister().getEntityName(),
                            event.getEntity(),
                            event.getId(),
                            getCorePersistenceService(),
                            event.getPersister(),
                            event.getOldState()));

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object[] postUpdateDBState(PostUpdateEvent event) {
        final Object[] newDbState = event.getState().clone();
        if (event.getOldState() != null) {
            final EntityPersister entityPersister = event.getPersister();
            for (int i = 0; i < entityPersister.getPropertyNames().length; ++i) {
                if (!entityPersister.getPropertyUpdateability()[i]) {
                    // Assuming that PostUpdateEvent#getOldState() returns database state of the record before modification.
                    // Otherwise, we would have to execute SQL query to be sure of @Column(updatable = false) column value.
                    newDbState[i] = event.getOldState()[i];
                }
            }
        }
        return newDbState;
    }
}