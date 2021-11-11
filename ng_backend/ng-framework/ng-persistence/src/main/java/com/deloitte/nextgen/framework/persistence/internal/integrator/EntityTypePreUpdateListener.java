package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class EntityTypePreUpdateListener extends BaseEntityTypeListener implements PreUpdateEventListener {

    private static final long serialVersionUID = 988963483254124L;

    public EntityTypePreUpdateListener(CorePersistenceService corePersistenceService) {
        super(corePersistenceService);
        log.info(LogMarker.PERSISTENCE, "Initiated Entity Pre Update Listener");
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {

        String entityName = event.getEntityName();
        log.warn(LogMarker.PERSISTENCE, "onPreUpdate 1");
        if (getCorePersistenceService().getEntityConfigurations().containsEntityTypeAnnotation(entityName)) {

            log.warn(LogMarker.PERSISTENCE, "onPreUpdate 2 ");
            validateCreateBy(event);
            touchUpdatedBy(event);

            if (getCorePersistenceService().getEntityConfigurations().isTypeTwo(entityName)) {
                Long totalMainRecordCount = countOfMainTableRecords(event);
                log.info("Inside PreInsert Listener: count of Main records ={}", totalMainRecordCount);
                touchHistNavIndInsert(event, totalMainRecordCount);
            }
        }
        return false;
    }


}