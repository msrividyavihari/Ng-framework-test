package com.deloitte.nextgen.dc;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration()
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
@EnableConfigurationProperties
public class DataCollectionApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataCollectionApplication.class, args);
	}
}
