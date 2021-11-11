package com.deloitte.nextgen.audit.consumers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class AuditConsumer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuditConsumer.class);
    }

    public static void main(String[] args) {

        log.info("Starting with {}", "Framework Error Log Service");
        SpringApplication app = new SpringApplication(AuditConsumer.class);
        app.run(args).getEnvironment();
    }
}
