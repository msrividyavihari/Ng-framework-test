package com.deloitte.nextgen.framework.persistence.boot;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * The database specific {@link MetadataBuilderContributor} to register the functions
 * that were supported by JPA but not in underlying database.
 *
 * Register using below in application.yml
 * spring.jpa.properties.hibernate.metadata_builder_contributor=com.deloitte.nextgen.framework.persistence.boot.OracleMetadataBuilderContributor
 */
public class OracleMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("diff_days", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1-?2"));
    }
}
