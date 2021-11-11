package com.deloitte.nextgen.framework.persistence.internal.integrator;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.persistence.internal.EntityTypeProcess;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.CorePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.query.Query;

import javax.persistence.FlushModeType;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
@Slf4j
public class EntityTypePreInsertListener extends BaseEntityTypeListener implements PreInsertEventListener {

    private static final long serialVersionUID = 103986359813401L;
    private static final String ROWID_PROPERTY = "rowid";
    private static final String EFF_END_DT_PROPERTY = "effEndDt";
    private static final String EFF_BEGIN_DT_PROPERTY = "effBeginDt";

    public EntityTypePreInsertListener(CorePersistenceService corePersistenceService) {
        super(corePersistenceService);
        log.info(LogMarker.PERSISTENCE, "Initiated Entity Pre Insert Listener");
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event)  {
        String entityName = event.getEntityName();
        try {
            if (getCorePersistenceService().getEntityConfigurations().containsEntityTypeAnnotation(entityName)) {
                touchCreatedBy(event);
            }


            EntityTypeProcess process = getCorePersistenceService().getEntityTypeManager().get(event.getSession());
            if (getCorePersistenceService().getEntityConfigurations().containsEntityTypeAnnotation(entityName)) {

                if (getCorePersistenceService().getEntityConfigurations().isTypeTwo(entityName)) {
                    Long totalMainRecordCount = countOfMainTableRecords(event);
                    log.info("Inside PreInsert Listener: count of Main records ={}", totalMainRecordCount);
                    log.info("Inside PreInsert Listener: count of Main entityName ={}", entityName);

                    updatePreviousMainRecord(event, totalMainRecordCount);
                    touchHistNavIndInsert(event, totalMainRecordCount);

                    //Persist B table record
                    log.info("Create a _B table for Type2 Table");

                    //Get B Entity
                    Object bData = getBData(event);
                    //Get B PK Entity to search/get if B table record exists
                    Object bPkData = getBasePkData(event);

                    Class clazz = Class.forName(getBEntityName(entityName));
                    Constructor<?> ctor = clazz.getConstructor();
                    Object basePkEntity = ctor.newInstance();
                    basePkEntity = event.getSession().get(clazz, (Serializable) bPkData);
                    int dotIndex = entityName.lastIndexOf(".");
                    String tableName = entityName.substring(dotIndex + 1).concat("B");
                    tableName = camelToSnake(tableName);
                    //Only save if it is a new B table record
                    if (basePkEntity == null) {
                        log.info("B record DOES NOT exists, save B record");


                        String insertBRecordSql = "INSERT INTO " + tableName;
                        String columnClause = " (";
                        String valuesClause = " VALUES ( ";
                        //Field[] allBTableFields = bData.getClass().getDeclaredFields();
                        Field[] allBTableFields = FieldUtils.getAllFields(bData.getClass());
                        for (int idx = 0; idx < allBTableFields.length; idx++) {
                            String javaFieldName = allBTableFields[idx].getName();
                            String dbFieldName = camelToSnake(javaFieldName);
                            if ((!ROWID_PROPERTY.equalsIgnoreCase(javaFieldName)) &&
                                    !(EFF_END_DT_PROPERTY.equalsIgnoreCase(javaFieldName))
                            ) {
                                columnClause = columnClause + dbFieldName + ",";
                                valuesClause = valuesClause + "?,";
                            }
                        }
                        //Remove last comma and add a close bracket
                        columnClause = columnClause.substring(0, columnClause.length() - 1) + ") ";
                        valuesClause = valuesClause.substring(0, valuesClause.length() - 1) + ") ";

                        insertBRecordSql = insertBRecordSql + columnClause + valuesClause;
                        log.info("B table insert SQL={}", insertBRecordSql);

                        //Add parameters

                        Query sqlQuery = event.getSession().createNativeQuery(insertBRecordSql);
                        for (int idx = 0, j = 1; idx < allBTableFields.length; idx++) {
                            String fieldName = allBTableFields[idx].getName();
                            if ((!ROWID_PROPERTY.equalsIgnoreCase(fieldName)) &&
                                    (!EFF_END_DT_PROPERTY.equalsIgnoreCase(fieldName))
                            ) {
                                Object fieldValue = PropertyUtils.getProperty(bData, fieldName);
                                System.err.println(fieldName+" - "+fieldValue);
                                sqlQuery.setParameter(j, fieldValue);
                                j++;
                            }
                        }
                        System.err.println(sqlQuery);
                        int insertCount = sqlQuery.setFlushMode(FlushModeType.COMMIT)
                                .executeUpdate();
                        log.info("Count of B table record inserted={}", insertCount);

                    } else {
                        log.info("B record already exists then Update ");

                        StringBuilder builder = new StringBuilder();
                        builder.append("UPDATE ");
                        builder.append(tableName);
                        builder.append(" SET ");
                        builder.append(camelToSnake(EFF_BEGIN_DT_PROPERTY));
                        builder.append(" = ?");
                        builder.append(" WHERE ");
                        int index = 0;
                        //dateValidation(null, null);
                        Field[] allBTablePKFields = basePkEntity.getClass().getDeclaredFields();
                        for (int idx = 0; idx < allBTablePKFields.length; idx++) {
                            String fieldName = allBTablePKFields[idx].getName();
                            builder.append(camelToSnake(fieldName));
                            builder.append(" = ? AND ");

                        }
                        System.err.println("EFF_BEGIN_DT_PROPERTY " + PropertyUtils.getProperty(bData, EFF_BEGIN_DT_PROPERTY));
                        System.err.println("EFF_END_DT_PROPERTY " + PropertyUtils.getProperty(bData, EFF_END_DT_PROPERTY));
                        dateValidation((LocalDate) PropertyUtils.getProperty(bData, EFF_BEGIN_DT_PROPERTY), (LocalDate) PropertyUtils.getProperty(bData, EFF_END_DT_PROPERTY));
                        Query sqlQuery = event.getSession().createNativeQuery(builder.substring(0, builder.length() - 4));
                        sqlQuery.setParameter(++index, PropertyUtils.getProperty(bData, EFF_BEGIN_DT_PROPERTY));

                        for (int idx = 0; idx < allBTablePKFields.length; idx++) {
                            String fieldName = allBTablePKFields[idx].getName();
                            Object val = PropertyUtils.getProperty(bPkData, allBTablePKFields[idx].getName());
                            sqlQuery.setParameter(++index, val);

                        }
                        int insertCount = sqlQuery.setFlushMode(FlushModeType.COMMIT)
                                .executeUpdate();
                        log.info("Count of B table record UPDATED={}", insertCount);

                    }


                } //Main if loop for Type 2
            }
            //Main if loop for @EntityType Annotation

        } catch (Exception e ) {
            log.error(e.getMessage(), e);
            return true;  // opration is vetoed

        }


        return false;
    }
}
