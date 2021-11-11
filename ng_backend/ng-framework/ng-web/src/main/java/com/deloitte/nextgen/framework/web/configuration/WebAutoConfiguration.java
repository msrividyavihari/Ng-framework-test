package com.deloitte.nextgen.framework.web.configuration;

import com.deloitte.nextgen.framework.commons.spi.AuditLogWriter;
import com.deloitte.nextgen.framework.commons.spi.ExceptionWriter;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.commons.spi.internal.writer.ConsoleAuditLogWriter;
import com.deloitte.nextgen.framework.commons.spi.internal.writer.ConsoleExceptionWriter;
import com.deloitte.nextgen.framework.exceptions.ErrorResponseComposer;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.nextgen.framework.properties.DefaultApplicationProperties;
import com.deloitte.nextgen.framework.web.advice.DefaultExceptionAdviceHandler;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Configuration
@ComponentScan(value = DefaultApplicationProperties.BASE_PACKAGE)
@EnableSpringDataWebSupport
@AutoConfigureAfter({ApplicationProperties.class, ReferenceTableAutoConfiguration.class, JmsAutoConfiguration.class})
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
public class WebAutoConfiguration {


    private final ApplicationProperties properties;

    private final ReferenceTable referenceTable;

    private final ObjectMapper jacksonObjectMapper;

    public WebAutoConfiguration(ApplicationProperties properties, ReferenceTable referenceTable, ObjectMapper jacksonObjectMapper) {
        this.properties = properties;
        this.referenceTable = referenceTable;
        this.jacksonObjectMapper = jacksonObjectMapper;
        log.info(LogMarker.WEB, "Configuring Web");
    }


    @Bean
    @ConditionalOnProperty(prefix = "nextgen", name = "json-prefix")
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {

        log.info(LogMarker.WEB, "Configuring JSON vulnerability prefix");
        log.info(LogMarker.WEB, "ReferenceTable :: " +  this.referenceTable);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setJsonPrefix(properties.getJsonPrefix());

        return converter;
    }


    @Bean
    @ConditionalOnMissingBean(DefaultExceptionAdviceHandler.class)
    public <T extends Throwable> DefaultExceptionAdviceHandler<T> defaultExceptionHandlerControllerAdvice(
            ErrorResponseComposer<T> errorResponseComposer, ExceptionWriter exceptionWriter) {

        log.info(LogMarker.WEB, "Configuring DefaultExceptionAdviceHandler");
        return new DefaultExceptionAdviceHandler<>(errorResponseComposer, exceptionWriter);
    }

    /*@Bean
    @ConditionalOnMissingBean(ErrorAttributes.class)
    public <T extends Throwable> ErrorAttributes errorAttributes(ErrorResponseComposer<T> errorResponseComposer) {

        log.info(LogMarker.WEB, "Configuring ErrorAttributes");
        return new NextGenErrorAttributes<>(errorResponseComposer);
    }*/

    @Bean
    @ConditionalOnMissingBean(ExceptionWriter.class)
    public ExceptionWriter exceptionWriter() {
        log.info(LogMarker.WEB, "Configuring console exception writer since no other writer found.");
        return new ConsoleExceptionWriter();
    }


    @Bean
    @ConditionalOnMissingBean(AuditLogWriter.class)
    public AuditLogWriter auditLogWriter() {
        log.info(LogMarker.WEB, "Configuring console audit log writer since no other writer found.");
        return new ConsoleAuditLogWriter(jacksonObjectMapper);
    }

    @Bean
    @ConditionalOnMissingBean(CorsConfigurationSource.class)
    public CorsConfigurationSource corsConfigurationSource(ApplicationProperties properties) {

        log.info("Configuring NextGen CorsConfigurationSource");
        return new DefaultCorsConfiguration(properties);
    }

//    @Bean
//    @ConditionalOnMissingBean(AuditService.class)
//    public AuditService defaultAuditLogService() {
//
//        log.info(LogMarker.WEB, "Configuring DefaultExceptionAdviceHandler");
//        return new AuditServiceImpl(auditLogWriter());
//    }


    /*@Bean
    @ConditionalOnMissingBean(AuditorInfo.class)
    public AuditorInfo auditorAware() {

        log.info("Configuring Nextgen Auditor");
        return new AuditorInfoImpl(applicationProperties);
    }

    @Override
    public Validator getValidator() {
        ValidatorFactoryBean factoryBean = new ValidatorFactoryBean(recreate(), iReferenceTableManager);
        return factoryBean;
    }



    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ClockProvider recreate(){
        log.info("reloading date");
        Instant instant = Instant.parse("2018-08-19T16:02:42.00Z");

        // create ZoneId object for Asia/Calcutta zone
        ZoneId zoneId = ZoneId.of("Asia/Calcutta");
        return new TimeTravelProvider(instant, zoneId);
    }*/
}
