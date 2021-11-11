package com.deloitte.nextgen.security.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(SecurityPropertiesBean.class)
@AutoConfigureAfter(SecurityProperties.class)
public class SecurityAutoConfig {

    private final SecurityProperties.Security security;

    public SecurityAutoConfig(SecurityProperties security) {
        this.security = security.getSecurity();
        log.info("Security Properties Value :");
        log.info("SecurityUri : {}", this.security.getSecurityUri());
        log.info("Refresh : {}", this.security.getSecurityUri() + this.security.getRefresh());
        log.info("valid : {}", this.security.getSecurityUri() + this.security.getValid());

        log.info("Testing the Application without Security : {}", this.security.getTesting());

    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityPropertiesBean securityConfig() {
        return new SecurityPropertiesBean(this.security);
    }
}
