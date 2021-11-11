package com.deloitte.nextgen.framework.web.configuration;

import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class DefaultCorsConfiguration implements CorsConfigurationSource {

    private final ApplicationProperties.Cors cors;

    public DefaultCorsConfiguration(ApplicationProperties properties) {
        this.cors = properties.getCors();
    }

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList(cors.getAllowedHeaders()));
        config.setAllowedMethods(Arrays.asList(cors.getAllowedMethods()));
        config.setAllowedOrigins(Arrays.asList(cors.getAllowedOrigins()));
        config.setAllowedOriginPatterns(cors.getAllowedOriginPatterns());
        config.setExposedHeaders(Arrays.asList(cors.getExposedHeaders()));
        config.setMaxAge(cors.getMaxAge());

        return config;
    }
}
