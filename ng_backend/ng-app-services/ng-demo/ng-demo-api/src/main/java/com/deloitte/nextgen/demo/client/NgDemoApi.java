package com.deloitte.nextgen.demo.client;

import com.deloitte.nextgen.demo.client.api.DefaultDemoApiOperations;
import com.deloitte.nextgen.framework.automate.annotations.ImportOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author nishmehta on 14/06/2021 2:17 PM
 * @project ng-demo
 */

@Slf4j
@ImportOperations(DefaultDemoApiOperations.class)
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class NgDemoApi extends SpringBootServletInitializer {

    public static void main(String[] args) {

        log.info("Starting {}", "Demo API");

        SpringApplication.run(NgDemoApi.class, args);
    }
}
