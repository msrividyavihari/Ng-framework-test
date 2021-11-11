package com.deloitte.nextgen.framework.persistence.internal;

import org.hibernate.Transaction;
import org.hibernate.event.spi.EventSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

public class EntityTypeManager {


    private final Map<Transaction, EntityTypeProcess> auditProcesses;

    public EntityTypeManager() {
        auditProcesses = new ConcurrentHashMap<>();
    }

    public EntityTypeProcess get(EventSource session) {
        Transaction transaction = session.accessTransaction();

        return auditProcesses.computeIfAbsent(transaction, k -> {

            EntityTypeProcess auditProcess = new EntityTypeProcess(session);
            session.getActionQueue().registerProcess(
                    session1 -> {
                        final EntityTypeProcess process = auditProcesses.get(transaction);
                        if (process != null) {
                            process.doBeforeTransactionCompletion(session1);
                        }
                    }
            );

            session.getActionQueue().registerProcess(
                    (success, session12) -> auditProcesses.remove(transaction)
            );

            return auditProcess;
        });

    }
}
