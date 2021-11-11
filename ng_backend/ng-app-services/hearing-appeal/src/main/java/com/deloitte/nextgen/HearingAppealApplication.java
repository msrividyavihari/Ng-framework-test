package com.deloitte.nextgen;

//import com.deloitte.ng.autoconfigure.ReferenceTableAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
@EnableConfigurationProperties
@EnableAutoConfiguration
		//(exclude={ReferenceTableAutoConfiguration.class})
public class HearingAppealApplication {
	public static void main(String[] args) {
		SpringApplication.run(HearingAppealApplication.class, args);
	}
}