package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.commons.enums.HistoryNavInd;
import com.deloitte.nextgen.framework.commons.exceptions.NgUserInfoNotFoundException;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.exceptions.CorePersistenceException;
import com.deloitte.nextgen.framework.persistence.internal.service.EntitiesConfigurations;
import com.deloitte.nextgen.framework.persistence.internal.service.EntityInformation;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.query.Query;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@NoArgsConstructor
@Slf4j
public class BaseEntityTypeListener {

    private static final String HIST_NAV_IND_PROPERTY = "histNavInd";
    private static final String EFF_BEGIN_DT_PROPERTY = "effBeginDt";
    private static final String EFF_END_DT_PROPERTY = "effEndDt";
    private CorePersistenceService corePersistenceService;
    private EntitiesConfigurations entityConfigurations;

    public BaseEntityTypeListener(CorePersistenceService corePersistenceService) {
        this.corePersistenceService = corePersistenceService;
        this.entityConfigurations = corePersistenceService.getEntityConfigurations();
    }

    public static String camelToSnake(String str) {
        // Empty String
        String result = "";

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Tarverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }

        return result;
    }

    private static boolean propertyExists(Object bean, String property) {
        return PropertyUtils.isReadable(bean, property) &&
                PropertyUtils.isWriteable(bean, property);
    }

    public CorePersistenceService getCorePersistenceService() {
        return corePersistenceService;
    }

    protected Date getArchiveDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(9999, Calendar.DECEMBER, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    protected void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value) {
        int index = ArrayUtils.indexOf(propertyNames, propertyToSet);
        if (index >= 0) {
            currentState[index] = value;
        }
    }

    protected String getValue(Object[] currentState, String[] propertyNames, String propertyToGet) {
        int index = ArrayUtils.indexOf(propertyNames, propertyToGet);
        if (index >= 0) {
            return (String) currentState[index];
        }
        return null;
    }

    protected void touchCreatedBy(PreInsertEvent event) {

        String entityName = event.getEntityName();
        EntityInformation ei = entityConfigurations.getEntityInformation(entityName);

        if (!ei.getCreatedByField().isPresent())
            return;

        try {

            Object entity = event.getEntity();
            String auditorName = corePersistenceService.getAuditorName();

            Optional<Field> createdByFieldOptional = ei.getCreatedByField();
            if (createdByFieldOptional.isPresent()) {
                PropertyUtils.setProperty(entity, createdByFieldOptional.get().getName(), auditorName);
                setValue(event.getState(), event.getPersister().getPropertyNames(), createdByFieldOptional.get().getName(), auditorName);
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NgUserInfoNotFoundException e) {
            log.warn("Error in touchCreatedBy() : ", e);
        }

    }

    protected void validateCreateBy(PreUpdateEvent event) {

        String entityName = event.getEntityName();
        EntityInformation ei = entityConfigurations.getEntityInformation(entityName);

        Optional<Field> createdByFieldOptional = ei.getCreatedByField();
        if (createdByFieldOptional.isPresent()) {
            String oldCreateByValue = getValue(event.getOldState(), event.getPersister().getPropertyNames(), createdByFieldOptional.get().getName());
            String createByValue = getValue(event.getState(), event.getPersister().getPropertyNames(), createdByFieldOptional.get().getName());

            if (createByValue == null || !createByValue.equals(oldCreateByValue)) {
                throw new IllegalStateException(String.format("Create by field value cannot be modified with %s during update of entity.", createByValue));
            }
        }

    }

    protected void touchUpdatedBy(PreUpdateEvent event) {

        String entityName = event.getEntityName();
        EntityInformation ei = entityConfigurations.getEntityInformation(entityName);

        if (!ei.getUpdatedByField().isPresent())
            return;

        log.warn(LogMarker.PERSISTENCE, "touchUpdatedBy 0");
        try {
            Object entity = event.getEntity();
            String auditorName = corePersistenceService.getAuditorName();

            Optional<Field> updatedByFieldOptional = ei.getUpdatedByField();
            if (updatedByFieldOptional.isPresent()) {
                PropertyUtils.setProperty(entity, updatedByFieldOptional.get().getName(), auditorName);
                log.warn(LogMarker.PERSISTENCE, "touchUpdatedBy 1");
                log.warn(LogMarker.PERSISTENCE, auditorName);
                log.warn(LogMarker.PERSISTENCE, event.getPersister().getPropertyNames() + " , " + updatedByFieldOptional.get().getName());
                setValue(event.getState(), event.getPersister().getPropertyNames(), updatedByFieldOptional.get().getName(), auditorName);
            }
            log.warn(LogMarker.PERSISTENCE, "touchUpdatedBy 2");

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NgUserInfoNotFoundException e) {
            log.warn("Error in touchUpdatedBy() : ", e);
        }

    }

    protected void touchHistNavIndInsert(PreUpdateEvent event, Long totalMainRecordCount) {

        String entityName = event.getEntityName();

        try {
            log.info("Inside touchHistNavInd in BaseEntityTypeListener");
            Object entity = event.getEntity();
            HistoryNavInd value = null;

            if (totalMainRecordCount == 0) {
                value = HistoryNavInd.S;   //No old update needed
            } else if (totalMainRecordCount == 1) {
                value = HistoryNavInd.P;    //Update old from S to F
            } else if (totalMainRecordCount > 1) {
                value = HistoryNavInd.P;    //Update old from P to M
            }

            PropertyUtils.setProperty(entity, HIST_NAV_IND_PROPERTY, value);
            setValue(event.getState(), event.getPersister().getPropertyNames(), HIST_NAV_IND_PROPERTY, value);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NgUserInfoNotFoundException e) {
            log.warn("Error in touchHistNavInd() : ", e);
        }

    }

    protected void touchHistNavIndInsert(PreInsertEvent event, Long totalMainRecordCount) {

        String entityName = event.getEntityName();

        try {
            log.info("Inside touchHistNavInd in BaseEntityTypeListener");
            Object entity = event.getEntity();
            HistoryNavInd value = null;

            if (totalMainRecordCount == 0) {
                value = HistoryNavInd.S;   //No old update needed
            } else if (totalMainRecordCount == 1) {
                value = HistoryNavInd.P;  //Update old from S to F
            } else if (totalMainRecordCount > 1) {
                value = HistoryNavInd.P;   //Update old from P to M
            }

            PropertyUtils.setProperty(entity, HIST_NAV_IND_PROPERTY, value);
            setValue(event.getState(), event.getPersister().getPropertyNames(), HIST_NAV_IND_PROPERTY, value);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NgUserInfoNotFoundException e) {
            log.warn("Error in touchHistNavInd() : ", e);
        }

    }

    protected void checkIfTransactionInProgress(SessionImplementor session) {
        if (!session.isTransactionInProgress()) {
            // Historical data would not be flushed to audit tables if outside of active transaction
            // (AuditProcess#doBeforeTransactionCompletion(SessionImplementor) not executed).
            throw new CorePersistenceException("Unable to proceed due to non-active transaction");
        }
    }

    public Long countOfMainTableRecords(PreUpdateEvent event) {

        Long mainEntityCount = 0L;

        try {
            //Get all records of Main class DcCaseIndividual using Criteria query to derive histNavInd
            Class clazzMain = Class.forName(event.getPersister().getEntityName());
            Class clazzMainPk = Class.forName(event.getId().getClass().getName());
            Constructor<?> ctor = clazzMainPk.getConstructor();
            Object newMainEntityPK = ctor.newInstance();
            newMainEntityPK = event.getPersister().getIdentifier(event.getEntity(), event.getSession());

            Field[] allPkTableFields = clazzMainPk.getDeclaredFields();

            CriteriaBuilder cb = event.getSession().getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            Root<Long> countRoot = cr.from(clazzMain);
            cr.select(cb.count(countRoot));
            //Remove effBeginDt one field
            Predicate[] predicates = new Predicate[allPkTableFields.length - 1];
            int i = 0;
            for (Field MainTablePkFieldName : allPkTableFields) {
                String fieldName = MainTablePkFieldName.getName();
                if (!(EFF_BEGIN_DT_PROPERTY.equalsIgnoreCase(fieldName))) {
                    Object fieldValue = PropertyUtils.getProperty(newMainEntityPK, fieldName);
                    predicates[i] = cb.equal(countRoot.get(fieldName), fieldValue);
                    i++;
                }
            }
            cr.where(predicates);
            TypedQuery<Long> queryCount = event.getSession().createQuery(cr);
            queryCount.setFlushMode(FlushModeType.COMMIT);

            mainEntityCount = queryCount.getSingleResult();

            log.info("Inside countOfMainTableRecords: Main Entity count of existing records={}", mainEntityCount);
        } catch (ClassNotFoundException ce) {
            log.info("Inside countOfMainTableRecords ClassNotFoundException error:");
            log.info(ce.getMessage(), ce);
        } catch (Exception e) {
            log.info("Inside countOfMainTableRecords Exception error:");
            log.info(e.getMessage(), e);
        }

        return mainEntityCount;
    }

    public Long countOfMainTableRecords(PreInsertEvent event) {

        Long mainEntityCount = 0L;

        try {
            //Get all records of Main class DcCaseIndividual using Criteria query to derive histNavInd
            Class clazzMain = Class.forName(event.getPersister().getEntityName());
            Class clazzMainPk = Class.forName(event.getId().getClass().getName());
            Constructor<?> ctor = clazzMainPk.getConstructor();
            Object newMainEntityPK = ctor.newInstance();
            newMainEntityPK = event.getPersister().getIdentifier(event.getEntity(), event.getSession());

            Field[] allPkTableFields = clazzMainPk.getDeclaredFields();

            CriteriaBuilder cb = event.getSession().getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);
            Root<Long> countRoot = cr.from(clazzMain);
            cr.select(cb.count(countRoot));
            //Remove effBeginDt one field
            Predicate[] predicates = new Predicate[allPkTableFields.length - 1];
            int i = 0;
            for (Predicate predicate : predicates) {
                System.err.println(predicate);
            }
            Arrays.stream(allPkTableFields).forEach(e->{
                System.err.println(e.getName());
            });
            for (Field MainTablePkFieldName : allPkTableFields) {
                String fieldName = MainTablePkFieldName.getName();
                System.err.println("fieldName "+fieldName);
                if (!(EFF_BEGIN_DT_PROPERTY.equalsIgnoreCase(fieldName))) {
                    Object fieldValue = PropertyUtils.getProperty(newMainEntityPK, fieldName);
                    predicates[i] = cb.equal(countRoot.get(fieldName), fieldValue);
                    i++;
                }
            }
            cr.where(predicates);
            TypedQuery<Long> queryCount = event.getSession().createQuery(cr);
            queryCount.setFlushMode(FlushModeType.COMMIT);

            mainEntityCount = queryCount.getSingleResult();

            log.info("Inside countOfMainTableRecords: Main Entity count of existing records={}", mainEntityCount);
        } catch (ClassNotFoundException ce) {
            log.info("Inside countOfMainTableRecords ClassNotFoundException error:");
            log.info(ce.getMessage(), ce);
        } catch (Exception e) {
            log.info("Inside countOfMainTableRecords Exception error:");
            log.info(e.getMessage(), e);
        }

        return mainEntityCount;
    }

    public boolean updatePreviousMainRecord(PreInsertEvent event, Long totalMainRecordCount) throws Exception {

        try {


            Class clazzMain = Class.forName(event.getPersister().getEntityName());
            Class clazzMainPk = Class.forName(event.getId().getClass().getName());
            Constructor<?> ctor = clazzMainPk.getConstructor();
            Object newMainEntityPK = ctor.newInstance();
            newMainEntityPK = event.getPersister().getIdentifier(event.getEntity(), event.getSession());
            LocalDate newEBD = null;

            Field[] allPkTableFields = clazzMainPk.getDeclaredFields();

            //Get last latest record of Main class DcCaseIndividual using Criteria
            //query to update EffEndDt and histNavInd
            CriteriaBuilder cb = event.getSession().getCriteriaBuilder();
            CriteriaQuery cr = cb.createQuery(clazzMain);
            Root fromClause = cr.from(clazzMain);
            CriteriaQuery selectClause = cr.select(fromClause);

            //Remove effBeginDt one field
            Predicate[] predicates = new Predicate[allPkTableFields.length - 1];
            int i = 0;
            for (Field MainTablePkFieldName : allPkTableFields) {
                String fieldName = MainTablePkFieldName.getName();
                Object fieldValue = PropertyUtils.getProperty(newMainEntityPK, fieldName);

                if (EFF_BEGIN_DT_PROPERTY.equalsIgnoreCase(fieldName)) {
                    newEBD = (LocalDate) fieldValue;
                    if (newEBD==null)
                        throw new Exception("EXCEPTION: Begin Date Is Null");
                    log.info("Inside getLastMainRecordToEndDate: newEBD={}", newEBD);
                } else {
                    predicates[i] = cb.equal(fromClause.get(fieldName), fieldValue);
                    i++;
                }
            }
            selectClause.where(predicates);
            selectClause.orderBy(cb.desc(fromClause.get(EFF_BEGIN_DT_PROPERTY)));
            TypedQuery typedQuery = event.getSession().createQuery(selectClause);

            typedQuery.setFirstResult(0);
            typedQuery.setMaxResults(1);
            typedQuery.setFlushMode(FlushModeType.COMMIT);

            List mainList = typedQuery.getResultList();
            log.info("Inside getLastMainRecordToEndDate: Main Entity count of existing records={}", mainList.size());

            if (mainList != null && mainList.size() == 1) {
                log.info("Inside getLastMainRecordToEndDate: Will now end-date previous record");
                Constructor<?> ctorMain = clazzMain.getConstructor();
                Object oldMainEntity = ctorMain.newInstance();

                oldMainEntity = mainList.get(0);    //to be updated
                System.err.println(newEBD);
                validateChanges(oldMainEntity, event);

                //End date previous record by minus one day
                LocalDate dateBefore1Days = newEBD.minusDays(1);
                LocalDate oldEffBeginDate = (LocalDate) PropertyUtils.getProperty(oldMainEntity, EFF_BEGIN_DT_PROPERTY);
                dateValidation(oldEffBeginDate, dateBefore1Days);
                // Update Query To Update EED and histNavInd in oldMainEntity
                int index = 0;
                String entityName = event.getEntityName();
                int dotIndex = entityName.lastIndexOf(".");
                String tableName = entityName.substring(dotIndex + 1);
                tableName = camelToSnake(tableName);
                StringBuilder builder = new StringBuilder();
                builder.append("UPDATE ");
                builder.append(tableName);
                builder.append(" ");
                builder.append("SET ");
                builder.append(camelToSnake(EFF_END_DT_PROPERTY));
                builder.append(" = ? , ");

                PropertyUtils.setProperty(oldMainEntity, EFF_END_DT_PROPERTY, dateBefore1Days);
                // Update Old record Updated By logic
                EntityInformation ei = entityConfigurations.getEntityInformation(entityName);
                boolean isUpdatedBy = false;
                if (ei.getUpdatedByField().isPresent()) {
                    Optional<Field> updatedByFieldOptional = ei.getUpdatedByField();
                    if (updatedByFieldOptional.isPresent()) {
                        builder.append(camelToSnake(updatedByFieldOptional.get().getName()));
                        builder.append(" = ? , ");
                        isUpdatedBy = true;
                    }
                }

                //Update Old record histNavInd logic
                HistoryNavInd histNavIndValue = null;
                if (totalMainRecordCount == 0) {
                    histNavIndValue = null;    //No old record to update
                } else if (totalMainRecordCount == 1) {
                    histNavIndValue = HistoryNavInd.F;  //Update old from S to F
                } else if (totalMainRecordCount > 1) {
                    histNavIndValue = HistoryNavInd.M;   //Update old from P to M
                }

                if (histNavIndValue != null) {
                    PropertyUtils.setProperty(oldMainEntity, HIST_NAV_IND_PROPERTY, histNavIndValue);
                    builder.append(camelToSnake(HIST_NAV_IND_PROPERTY));
                    builder.append(" = ? ");
                }
                builder.append(" WHERE ");

                for (Field field : allPkTableFields) {
                    builder.append(camelToSnake(field.getName()));
                    builder.append(" = ? AND ");

                }

                Query sqlQuery = event.getSession().createNativeQuery(builder.substring(0, builder.length() - 4));
                sqlQuery.setParameter(++index, dateBefore1Days);
                if (isUpdatedBy) {
                    sqlQuery.setParameter(++index, corePersistenceService.getAuditorName());
                }
                if (histNavIndValue != null) {
                    sqlQuery.setParameter(++index, histNavIndValue.name());
                }
                PropertyUtils.getProperty(oldMainEntity, HIST_NAV_IND_PROPERTY);

                for (Field field : allPkTableFields) {

                    sqlQuery.setParameter(++index, PropertyUtils.getProperty(oldMainEntity, field.getName()));
                }

                int insertCount = sqlQuery.setFlushMode(FlushModeType.COMMIT).executeUpdate();

                log.info("Inside getLastMainRecordToEndDate: Update Old Main Intity={}", insertCount >= 1 ? " TRUE" : " FALSE");


            } else {
                log.info("Inside getLastMainRecordToEndDate: No previous record found to update");
            }
        } catch (ClassNotFoundException ce) {
            log.info("Inside getLastMainRecordToEndDate ClassNotFoundException error:");
            log.info(ce.getMessage(), ce);
            throw ce;
        } catch (Exception e) {
            log.info("Inside getLastMainRecordToEndDate Exception error:");
            e.printStackTrace();
            log.info(e.getMessage(), e);
            throw e;
        }
        return true;
    }

    private void validateChanges(Object oldMainEntity, PreInsertEvent event) throws Exception {
        Class clazzMain = null;
        try {
            boolean isAllSame = true;
            boolean isBeginDateDiff = false;
            clazzMain = Class.forName(event.getPersister().getEntityPersister().getEntityName());
            Field[] fields = clazzMain.getDeclaredFields();
            Object[] newState = event.getState();
            Object _identifierMapper = newState[0];

            for (Field field : fields) {
                System.err.println(field.getName());
                if (propertyExists(oldMainEntity, field.getName())) {
                    if (!PropertyUtils.getProperty(oldMainEntity, field.getName()).equals(PropertyUtils.getProperty(_identifierMapper, field.getName())))
                        isAllSame = false;
                    if (field.getName().equalsIgnoreCase(EFF_BEGIN_DT_PROPERTY)) {
                        isBeginDateDiff = true;
                        isAllSame = true;
                    }

                }
            }
            if (isAllSame && !isBeginDateDiff) {
                log.error(LogMarker.PERSISTENCE, "Nither Entity Data Changed Nor Begin Date Changed");
                throw new Exception("Nither Entity Data Changed Nor Begin Date Changed");
            } else if (isAllSame && isBeginDateDiff) {
                log.error(LogMarker.PERSISTENCE, "Begin and end date change without data change");
                throw new Exception("Begin and end date change without data change");
            } else if (!isAllSame && isBeginDateDiff) {
                log.info(LogMarker.PERSISTENCE, "Begin or end date not changed but data changed");
            } else if (!isAllSame && !isBeginDateDiff) {
                log.error(LogMarker.PERSISTENCE, "Begin or end date change without data change");
                throw new Exception("Begin or end date change without data change");
            }

        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(LogMarker.PERSISTENCE, e.getMessage(), e.toString());
            throw e;
        }


    }

    public void endDateBTable(PostUpdateEvent event) {

        try {

            String entityName = event.getPersister().getEntityName();
            //Get the B table name
            int dotIndex = entityName.lastIndexOf(".");
            String basePackage = entityName.substring(0, dotIndex);
            basePackage = basePackage.concat(".generated").concat(entityName.substring(dotIndex));

            String bTableName = basePackage.concat("B");

            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] oldState = event.getOldState();
            Object[] newState = event.getState();

            for (int i = 0; i < propertyNames.length; i++) {
                String fieldName = propertyNames[i];


                log.info("In endDateBTable propertyNames=" + fieldName);
                if (EFF_END_DT_PROPERTY.equals(fieldName)) {
                    LocalDate oldEED = (LocalDate) oldState[i];
                    LocalDate newEED = (LocalDate) newState[i];
                    log.info("In endDateBTable oldEED={}, newEED={}", oldEED, newEED);
                    if ((oldEED == null && newEED != null)
                            || (oldEED != null && newEED != null && oldEED != newEED)) {

                        String updateSQL = "UPDATE  " + bTableName;
                        updateSQL = updateSQL + "  set " +
                                EFF_END_DT_PROPERTY + " = ?1" +
                                "  where 1=1  ";

                        Class clazzMainPk = Class.forName(event.getId().getClass().getName());
                        Constructor<?> ctor = clazzMainPk.getConstructor();
                        Object newMainEntityPK = ctor.newInstance();
                        newMainEntityPK = event.getPersister().getIdentifier(event.getEntity(), event.getSession());

                        Field[] allPkTableFields = newMainEntityPK.getClass().getDeclaredFields();
                        log.info("In endDateBTable, allPkTableFields count={}", allPkTableFields.length);
                        for (int idx = 0, j = 2; idx < allPkTableFields.length; idx++) {
                            String fieldNamePK = allPkTableFields[idx].getName();
                            log.info("In endDateBTable prepared stmt, fieldNamePK={}", fieldNamePK);
                            if (!(EFF_BEGIN_DT_PROPERTY.equalsIgnoreCase(fieldNamePK))) {
                                updateSQL = updateSQL +
                                        " and  " + fieldNamePK + "=?" + j;
                                j++;
                            }
                        }

                        log.info("In endDateBTable, sql={}", updateSQL);
                        //Add parameters
                        Query sqlQuery = event.getSession().createQuery(updateSQL);
                        sqlQuery.setParameter(1, newEED);

                        for (int idx2 = 0, j = 2; idx2 < allPkTableFields.length; idx2++) {
                            String fieldName2 = allPkTableFields[idx2].getName();
                            System.err.println(fieldName2 + " , " + PropertyUtils.getProperty(newMainEntityPK, fieldName2));
                            if (!(EFF_BEGIN_DT_PROPERTY.equalsIgnoreCase(fieldName2))) {
                                Object fieldValue2 = PropertyUtils.getProperty(newMainEntityPK, fieldName2);
                                sqlQuery.setParameter(j, fieldValue2);
                                log.info("In endDateBTable, add parameter for fieldName={} and fieldValue={}", fieldName2, fieldValue2);
                                j++;
                            }
                        }

                        int updateCount = sqlQuery.setFlushMode(FlushModeType.COMMIT)
                                .executeUpdate();
                        log.info("Count of B table record updated={}", updateCount);
                        break;  //Out of for loop

                    } //Old and New EED are different, so end date B table record

                } //EffEndDate property check
            } //end for loop
        } catch (ClassNotFoundException ce) {
            log.info("Inside getLastMainRecordToEndDate ClassNotFoundException error:");
            log.info(ce.getMessage(), ce);
        } catch (Exception e) {
            log.info("Inside getLastMainRecordToEndDate Exception error:");
            log.info(e.getMessage(), e);
        }

    }

    public String getBEntityName(String oldEntityName) {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("B");
    }

    public String getBasePkEntityName(String oldEntityName) {
        int dotIndex = oldEntityName.lastIndexOf(".");
        String basePackage = oldEntityName.substring(0, dotIndex);
        basePackage = basePackage.concat(".generated").concat(oldEntityName.substring(dotIndex));

        return basePackage.concat("BasePK");
    }

    //Get B Entity
    public Object getBData(PreInsertEvent event) {

        Object newBEntity = null;

        try {
            String entityName = event.getEntityName();
            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] oldState = event.getState();

            Class<?> clazz = null;
            clazz = Class.forName(getBEntityName(entityName));
            Constructor<?> ctor = clazz.getConstructor();
            newBEntity = ctor.newInstance();
            BeanUtils.copyProperties(newBEntity, event.getEntity());

            Map<String, Object> oldDataMap = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                log.info("In getBdata field name={}, value={}", propertyNames[i], oldState[i]);
                if (oldState[i] != null) {
                    oldDataMap.put(propertyNames[i], oldState[i]);
                }
            }
            BeanUtils.copyProperties(newBEntity, oldDataMap);
        } catch (NoSuchMethodException m) {
            m.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException c) {
            c.printStackTrace();
        }
        return newBEntity;
    }

    //Get B PK Entity
    public Object getBasePkData(PreInsertEvent event) {

        Object newBPkEntity = null;

        try {
            String entityName = event.getEntityName();
            String[] propertyNames = event.getPersister().getPropertyNames();
            Object[] oldState = event.getState();

            Class<?> clazz = null;
            clazz = Class.forName(getBasePkEntityName(entityName));
            Constructor<?> ctor = clazz.getConstructor();
            newBPkEntity = ctor.newInstance();
            BeanUtils.copyProperties(newBPkEntity, event.getEntity());

            Map<String, Object> oldDataMap = new HashMap<>();
            for (int i = 0; i < propertyNames.length; i++) {
                log.info("In getBasePkData field name={}, value={}", propertyNames[i], oldState[i]);
                if (oldState[i] != null) {
                    oldDataMap.put(propertyNames[i], oldState[i]);
                }
            }
            BeanUtils.copyProperties(newBPkEntity, oldDataMap);

        } catch (NoSuchMethodException m) {
            m.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException c) {
            c.printStackTrace();
        }
        return newBPkEntity;
    }

    public boolean dateValidation(LocalDate effBeginDt, LocalDate effEndDt) throws Exception {

        if (effBeginDt == null) {
            throw new Exception("Exception : effBeginDt must not be null. ");
        }

        if (effEndDt == null) {
            return true;
        }

        if (effEndDt.isBefore(effBeginDt)) {
            throw new Exception("Exception : The effBeginDt must be before the effEndDt.");
        }


        return true;
    }


}
