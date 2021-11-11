package com.deloitte.nextgen.framework.persistence.generators;

import com.deloitte.nextgen.framework.commons.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.internal.StandardDialectResolver;
import org.hibernate.engine.jdbc.dialect.spi.DatabaseMetaDataDialectResolutionInfoAdapter;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.tuple.ValueGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * The abstract class with default logic to generate NG Persistence related sequence values
 *
 * @author mukepatel on 24/07/2021 12:45 PM
 * @project ng-framework
 */
@Slf4j
public abstract class NGSequenceGenerator implements ValueGenerator<Long>  {

    private final DialectResolver dialectResolver;

    private Integer refreshCount = 0;

    private ConcurrentHashMap<String, Long> hashMap;

    protected NGSequenceGenerator() {
        this.dialectResolver = new StandardDialectResolver();
        this.hashMap = new ConcurrentHashMap<>();
    }

    protected abstract String generateSequenceName(String className);

    @Override
    public Long generateValue(Session session, Object owner) {

        //TODO change to table name instead of classname
        String sequenceName = generateSequenceName(owner.getClass().getSimpleName());

        log.info("Sequence Name: " + sequenceName);

        Long sequenceValue = hashMap.get(sequenceName);

        log.info("Sequence Value in Hash Map: " + sequenceValue);

        refreshCount = refreshCount + 1;

        Integer defaultSeqCacheSize = Constants.DEFAULT_SEQ_CACHE_SIZE;

        log.info("refreshCount : " + refreshCount);

        if ((sequenceValue == null) || (refreshCount.equals(defaultSeqCacheSize))) {

//          Put the Sequence Name and Sequence Value in the map after fetching from DB Sequence

            ReturningWork<Long> returningId = connection -> {

                DatabaseMetaDataDialectResolutionInfoAdapter adapter = new DatabaseMetaDataDialectResolutionInfoAdapter(connection.getMetaData());
                Dialect dialect = dialectResolver.resolveDialect(adapter);

                try (
                        PreparedStatement preparedStatement = connection.prepareStatement(dialect.getSequenceNextValString(sequenceName));
                        ResultSet resultSet = preparedStatement.executeQuery();

                ) {
                    resultSet.next();

                    return resultSet.getLong(1);

                }
            };

            if (sequenceValue == null) {
                hashMap.put(sequenceName, session.doReturningWork(returningId));
            } else {
                hashMap.computeIfPresent(sequenceName, (k, v) -> session.doReturningWork(returningId));
            }

            refreshCount =0;

            log.info("Sequence Value after refreshing from DB: " + hashMap.get(sequenceName));

            return hashMap.get(sequenceName);

        } else {

            hashMap.computeIfPresent(sequenceName, (k, v) -> sequenceValue + 1);

            log.info("Sequence Value taken from Hashmap: " + hashMap.get(sequenceName));
            return hashMap.get(sequenceName);
        }
    }
}
