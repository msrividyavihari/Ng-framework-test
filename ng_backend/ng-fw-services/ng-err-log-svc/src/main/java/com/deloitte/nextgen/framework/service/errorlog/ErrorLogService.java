package com.deloitte.nextgen.framework.service.errorlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ErrorLogService extends SpringBootServletInitializer {
    public static void main(String[] args) {

        log.info("Starting with {}", "Framework Error Log Service");
        SpringApplication app = new SpringApplication(ErrorLogService.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ErrorLogService.class);
    }
}
