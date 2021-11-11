package com.deloitte.nextgen.framework.autoconfigure.validator;

import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.commons.spi.internal.PortalKairosImpl;
import com.deloitte.nextgen.framework.logging.LogMarker;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.nextgen.framework.validator.provider.DefaultMessageInterpolator;
import com.deloitte.nextgen.framework.validator.provider.MessageService;
import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.DefaultClockProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ClockProvider;
import javax.validation.ConstraintValidator;
import javax.validation.MessageInterpolator;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Configuration
@AutoConfigureBefore({ValidationAutoConfiguration.class})
@AutoConfigureAfter({ApplicationProperties.class, ReferenceTableAutoConfiguration.class})
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Import(OverridePrimaryValidatorProcessor.class)
public class ValidatorAutoConfiguration {

    @Bean
    public static LocalValidatorFactoryBean validator(Optional<HibernateValidatorStrategy> hibernateValidatorConfigurationStrategy,
                                                      List<? extends ConstraintValidator<?, ?>> validators, final ClockProvider clockProvider, MessageInterpolator interpolator) {
        HibernateValidatorStrategy strategy = hibernateValidatorConfigurationStrategy.orElseGet(() -> configuration -> {
            configuration.clockProvider(clockProvider);
            configuration.messageInterpolator(interpolator);
        });
        return new DefaultLocalValidatorFactoryBean(strategy, validators);
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageInterpolator messageInterpolator(ReferenceTable referenceTable) {
        log.info(LogMarker.AUTOCONFIGURE, "Configuring NextGen messageInterpolator");
        MessageService messageService = new DefaultMessageService(referenceTable);
        MessageInterpolator interpolator = new DefaultMessageInterpolator(messageService);
        return interpolator;
    }


    @Bean
    @ConditionalOnMissingBean({RequestContextListener.class, RequestContextFilter.class})
    @ConditionalOnMissingFilterBean(RequestContextFilter.class)
    public RequestContextFilter requestContextFilter() {
        return new OrderedRequestContextFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public ClockProvider defaultClockProvider() {
        log.info(LogMarker.AUTOCONFIGURE, "Configuring NextGen default clock provider");
        return DefaultClockProvider.INSTANCE;
    }

    @Configuration
    @ConditionalOnProperty(prefix = "nextgen", name = "time-travel.enabled", havingValue = "true")
    static class ClockProviderConfiguration {

        @Bean
        @RequestScope
        @ConditionalOnWebApplication
        public ClockProvider portalClockProvider(HttpServletRequest request) {
            log.info(LogMarker.AUTOCONFIGURE, "Configuring NextGen portalClockProvider");
            return new PortalKairosImpl(request);
        }

        @Bean
        @ConditionalOnNotWebApplication
        public ClockProvider batchClockProvider() {
            log.info(LogMarker.AUTOCONFIGURE, "Configuring NextGen batchClockProvider");
            final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
            return () -> clock;
        }
    }

}
