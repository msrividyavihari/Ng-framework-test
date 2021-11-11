package com.deloitte.nextgen.framework.web.configuration.docs;

import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.oas.configuration.OpenApiDocumentationConfiguration;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.List;
import java.util.Optional;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Profile("resource-doc")
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({
        ApiInfo.class,
        BeanValidatorPluginsConfiguration.class,
        Docket.class
})
@AutoConfigureAfter(ApplicationProperties.class)
@Import({

        OpenApiDocumentationConfiguration.class,
        Swagger2DocumentationConfiguration.class,
        BeanValidatorPluginsConfiguration.class
})
public class ApiDocumentationAutoConfiguration {

    public ApiDocumentationAutoConfiguration() {
        log.debug(LogMarker.WEB, "Configuring Open API documentation");
    }

    @Bean
    public ResourceDocumentCustomizer resourceDocumentCustomizer(ApplicationProperties properties) {
        return new ResourceDocumentCustomizer(properties.getResourceDocInfo());
    }

    @Bean
    @ConditionalOnMissingBean(name = "openApiDocket")
    public Docket openApiDocket(List<ResourceDocumentCustomizer> springfoxCustomizers,
                                ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {

        Docket docket = createDocket();

        springfoxCustomizers.forEach(customizer -> customizer.customize(docket));

        Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);

        return docket;
    }

    protected Docket createDocket() {
        return new Docket(DocumentationType.OAS_30);
    }

}
