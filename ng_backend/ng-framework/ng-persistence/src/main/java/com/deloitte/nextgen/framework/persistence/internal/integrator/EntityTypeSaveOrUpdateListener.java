package com.deloitte.nextgen.framework.persistence.internal.integrator;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityTypeSaveOrUpdateListener implements SaveOrUpdateEventListener {

    private static Logger logger = LoggerFactory.getLogger(EntityTypeSaveOrUpdateListener.class);

    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException
    {
        logger.info("Inside EntityTypeSaveOrUpdateListener");

        // Here we prevent the invocation
        // of saveOrUpdate on the Session from having
        // any effect on the database!

        try {
            String entityName = event.getEntityName();
            logger.info("Reached here in EntityTypeSaveOrUpdateListener: update Entity={}", entityName);
        }
        catch (HibernateException he) {
            logger.info("Inside EntityTypeSaveOrUpdateListener catch Hibernate Exception");
            he.printStackTrace();
        } catch(Exception e) {
            logger.info("Inside EntityTypeSaveOrUpdateListener catch Exception");
            e.printStackTrace();
        }


    }

}