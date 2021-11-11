package com.deloitte.nextgen.framework.autoconfigure.messaging;

import com.deloitte.nextgen.framework.autoconfigure.messaging.jms.JmsAuditLogWriter;
import com.deloitte.nextgen.framework.autoconfigure.messaging.jms.JmsExceptionWriter;
import com.deloitte.nextgen.framework.commons.spi.AuditLogWriter;
import com.deloitte.nextgen.framework.commons.spi.ExceptionWriter;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import java.util.Arrays;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "nextgen.jms", name = "broker-url")
@AutoConfigureAfter(ApplicationProperties.class)
public class JmsAutoConfiguration {

    private final ApplicationProperties applicationProperties;

    public JmsAutoConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        log.info("Configuring JMS");
    }

    @Bean("errorQueue")
    public Queue errorQueue() {
        return () -> applicationProperties.getJms().getQueue().getError().getName();
    }

    @Bean("auditQueue")
    public Queue auditQueue() {
        return () -> applicationProperties.getJms().getQueue().getAudit().getName();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setTrustedPackages(Arrays.asList("com.deloitte.nextgen"));
        connectionFactory.setBrokerURL(applicationProperties.getJms().getBrokerUrl());
        connectionFactory.setPassword(applicationProperties.getJms().getUser());
        connectionFactory.setUserName(applicationProperties.getJms().getPassword());
        return connectionFactory;
    }

    private JmsTemplate getJmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(messageConverter());
        return template;
    }


    @Bean("errorJmsTemplate")
    public JmsTemplate errorJmsTemplate() {
        JmsTemplate template = getJmsTemplate();
        template.setDefaultDestination(errorQueue());
        return template;
    }


    @Bean("auditJmsTemplate")
    public JmsTemplate auditJmsTemplate() {
        JmsTemplate template = getJmsTemplate();
        template.setDefaultDestination(auditQueue());
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ExceptionWriter exceptionWriter() {
        return new JmsExceptionWriter(errorJmsTemplate(), errorQueue());
    }

    @Bean
    public AuditLogWriter auditLogWriter() {
        return new JmsAuditLogWriter(auditJmsTemplate(), auditQueue());
    }
}
