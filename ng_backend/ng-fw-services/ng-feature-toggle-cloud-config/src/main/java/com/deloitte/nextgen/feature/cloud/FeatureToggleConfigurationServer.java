package com.deloitte.nextgen.feature.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author nishmehta on 03/03/2021 7:37 PM
 * @project ng-feature-toggle-cloud-config
 */
@SpringBootApplication
@EnableConfigServer
public class FeatureToggleConfigurationServer {

    public static void main(String[] args) {
        SpringApplication.run(FeatureToggleConfigurationServer.class, args);
    }
}
