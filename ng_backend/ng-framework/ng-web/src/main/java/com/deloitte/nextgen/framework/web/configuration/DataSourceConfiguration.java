package com.deloitte.nextgen.framework.web.configuration;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @PersistenceContext
    private EntityManager em;

    public DataSourceConfiguration(EntityManager em) {
        this.em = em;
    }

    @Bean
    public JPQLQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }

}
