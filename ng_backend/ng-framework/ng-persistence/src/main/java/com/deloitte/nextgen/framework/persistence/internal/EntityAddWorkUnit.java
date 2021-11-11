package com.deloitte.nextgen.framework.persistence.internal;

import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.event.spi.EventSource;
import org.hibernate.persister.entity.EntityPersister;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public class EntityAddWorkUnit implements EntityTypeWorkUnit {

    //private final Object[] oldState;
    private final String oldEntityName;
    private Object newEntity;
    private String action;
    private Object newBEntity;


    public EntityAddWorkUnit(EventSource session, String entityName, Object entity, Serializable id, CorePersistenceService corePersistenceService, EntityPersister persister, Object[] oldState, String action) {
        //this.oldState = oldState;
        this.action = action;
        this.oldEntityName = entityName;
        String[] propertyNames = persister.getPropertyNames();
        Class<?> clazz = null;

        try {
            clazz = Class.forName(getAuditEntityName());
            Constructor<?> ctor = clazz.getConstructor();
            newEntity = ctor.newInstance();


            BeanUtils.copyProperties(newEntity, entity);
            Map<String, Object> oldDataMap = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                if (oldState[i] != null) {
                    oldDataMap.put(propertyNames[i], oldState[i]);
                }
            }

            BeanUtils.copyProperties(newEntity, oldDataMap);

            Class<?> clazzBPK = null;
            clazzBPK = Class.forName(getBEntityName());
            System.err.println(getBEntityName());
            Constructor<?> ctorbPk = clazzBPK.getConstructor();
            newBEntity = ctorbPk.newInstance();
            BeanUtils.copyProperties(newBEntity, entity);

            Map<String, Object> oldDataMapBpk = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                System.err.println("In getBasePkData KK " + propertyNames[i] + " " + oldState[i]);
                if (oldState[i] != null) {
                    oldDataMapBpk.put(propertyNames[i], oldState[i]);
                }
            }
            BeanUtils.copyProperties(newBEntity, oldDataMapBpk);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public EntityAddWorkUnit(EventSource session, String entityName, Object entity, Serializable id, CorePersistenceService corePersistenceService, EntityPersister persister, Object[] oldState) {
        //this.oldState = oldState;
        this.oldEntityName = entityName;
        String[] propertyNames = persister.getPropertyNames();
        Class<?> clazz = null;

        try {
            clazz = Class.forName(getAuditEntityName());
            Constructor<?> ctor = clazz.getConstructor();
            newEntity = ctor.newInstance();


            BeanUtils.copyProperties(newEntity, entity);
            Map<String, Object> oldDataMap = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                if (oldState[i] != null) {
                    oldDataMap.put(propertyNames[i], oldState[i]);
                }
            }

            BeanUtils.copyProperties(newEntity, oldDataMap);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAuditEntityName() {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("A");
    }

    @Override
    public Object getBEntity() {
        return newBEntity;
    }

    @Override
    public String getActionName() {
        return action;
    }

    @Override
    public Object getData() {
        return newEntity;
    }

    public String getMainEntityName() {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        //Don't refer to generated folder
        basePackage = basePackage.concat(oldEntityName.substring(dotIndex));

        return basePackage;
    }

    public String getBasePkEntityName() {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("BasePK");
    }

    @Override
    public String getBEntityName() {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("B");
    }

    public String getMainPkEntityName() {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("PK");
    }
}
