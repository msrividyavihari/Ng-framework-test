package com.deloitte.nextgen.framework.service.errorlog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;

//@Configuration
//@EnableJms
public class ActiveMQConfig {
/*
	@Value("${activemq.user}")
	private String BROKER_USERNAME;

	@Value("${activemq.password}")
	private String BROKER_PASSWORD;

	@Value("${activemq.broker-url}")
	private String BROKER_URL;
	


	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setPassword(BROKER_USERNAME);
		connectionFactory.setUserName(BROKER_PASSWORD);
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setMessageConverter(messageConverter());
		return template;
	}

*/

	@Value("${spring.activemq.queue.name}")
	private String QUEUE_NAME;

	@Bean
	public Queue queue() {
		//return new ActiveMQQueue(QUEUE_NAME);
		return () -> QUEUE_NAME;
	}

	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
