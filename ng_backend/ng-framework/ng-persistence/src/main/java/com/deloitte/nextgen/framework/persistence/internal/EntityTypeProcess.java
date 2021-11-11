package com.deloitte.nextgen.framework.persistence.internal;

import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.action.spi.BeforeTransactionCompletionProcess;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.spi.EventSource;

import java.util.LinkedList;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Slf4j
public class EntityTypeProcess implements BeforeTransactionCompletionProcess {

    private final LinkedList<EntityTypeWorkUnit> workUnits;

    public EntityTypeProcess(EventSource session) {
        log.debug("session :: " + session);
        this.workUnits = new LinkedList<>();
    }

    public void addWorkUnit(EntityAddWorkUnit entityAddWorkUnit) {
        workUnits.add(entityAddWorkUnit);
    }

    @Override
    public void doBeforeTransactionCompletion(SessionImplementor session) {

        log.debug(LogMarker.PERSISTENCE, "in doBeforeTransactionCompletion");
        if (workUnits.isEmpty()) {
            log.debug(LogMarker.PERSISTENCE, "nothing to work on doBeforeTransactionCompletion");
            return;
        }

        EntityTypeWorkUnit etwu;
        while ((etwu = workUnits.poll()) != null) {
           if (etwu.getActionName() !=null && etwu.getActionName().equalsIgnoreCase("DELETE")){
               session.remove(session.merge(etwu.getBEntity()));
           }
            session.save(etwu.getAuditEntityName(), etwu.getData());
            session.flush();
        }
    }
}
