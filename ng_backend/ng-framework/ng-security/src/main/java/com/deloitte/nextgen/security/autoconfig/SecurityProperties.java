package com.deloitte.nextgen.security.autoconfig;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "security", ignoreUnknownFields = false)
@PropertySource(value = "classpath:security.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:META-INF/build-info.properties", ignoreResourceNotFound = true)
public class SecurityProperties {

    @Getter
    private final Security security = new Security();

    @Data
    public static class Security {
        private String securityUri = SecurityDefaults.SECURITY_URI;
        private String valid = SecurityDefaults.VALID;
        private String refresh = SecurityDefaults.REFRESH;
        private Boolean testing = SecurityDefaults.TESTING;
    }

}
