package com.deloitte.nextgen.framework.exceptions;

import com.deloitte.nextgen.framework.exceptions.handlers.AbstractExceptionHandler;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author nishmehta on 07/06/2021 12:10 PM
 * @project ng-framework
 */

@Slf4j
@Configuration
@AutoConfigureBefore({ValidationAutoConfiguration.class})
public class ExceptionAutoConfiguration {

    public ExceptionAutoConfiguration() {
        log.info(LogMarker.EXCEPTION, "Created ExceptionAutoConfiguration");
    }


    /**
     * Configures ErrorResponseComposer if missing
     */
    @Bean
    @ConditionalOnMissingBean(ErrorResponseComposer.class)
    public <T extends Throwable> ErrorResponseComposer<T> errorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {
        log.info(LogMarker.EXCEPTION, "Configuring ErrorResponseComposer");
        return new ErrorResponseComposer<>(handlers);
    }


}
