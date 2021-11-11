package com.deloitte.nextgen.framework.persistence.internal;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

public interface EntityTypeWorkUnit {


    String getAuditEntityName();

    String getBEntityName();

    Object getBEntity();

    String getActionName();

    Object getData();
}
