package com.deloitte.nextgen.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author nishmehta on 09/03/2021 11:01 AM
 * @project ng-consumers
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class ErrorConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        log.info("Starting with {}", "Framework Error Log Service");
        SpringApplication app = new SpringApplication(ErrorConsumerApplication.class);
        app.run(args).getEnvironment();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ErrorConsumerApplication.class);
    }
}
