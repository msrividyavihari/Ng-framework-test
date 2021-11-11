package com.deloitte.ng.autoconfigure;

import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.reftables.ReferenceTableManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The ReferenceTableAutoConfiguration class to auto configure ReferenceTableManager
 *
 * @author nishmehta on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
@Configuration
@ConditionalOnClass(ReferenceTableManager.class)
@AutoConfigureAfter(ApplicationProperties.class)
@ImportAutoConfiguration({RestTemplate.class})
public class ReferenceTableAutoConfiguration {

    private final ApplicationProperties.ReferenceTable properties;

    private final RestTemplate restTemplate;

    public ReferenceTableAutoConfiguration(ApplicationProperties properties, RestTemplate restTemplate) {
        this.properties = properties.getReferenceTable();
        this.restTemplate = restTemplate;

        this.properties.setServiceUrl(this.properties.getBaseUrl() + this.properties.getServiceUrl());
        this.properties.setServiceUrlForPreLoad(this.properties.getBaseUrl() + this.properties.getServiceUrlForPreLoad());

        log.info("Reference Table Properties Value :");

        log.info("baseUrl : {}", this.properties.getBaseUrl());
        log.info("serviceUrl : {}", this.properties.getServiceUrl());
        log.info("serviceUrlForPreLoad : {}", this.properties.getServiceUrlForPreLoad());
        log.info("preLoadList : {}", this.properties.getPreloadList());
        log.info("preLoad : {}", this.properties.isPreload());

    }

    @Bean
    //@ConditionalOnMissingBean
    public ReferenceTable referenceTable() {
        return new ReferenceTableManager(properties, restTemplate);
    }
}
