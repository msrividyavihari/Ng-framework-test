package com.deloitte.nextgen.framework.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author nishmehta on 29/10/2020 1:13 PM
 * @project ng-framework
 */

@Data
@ConfigurationProperties(prefix = "nextgen", ignoreUnknownFields = false)
@PropertySource(value = "classpath:nextgen.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:META-INF/build-info.properties", ignoreResourceNotFound = true)
public class ApplicationProperties {

    @Getter
    private String jsonPrefix = DefaultApplicationProperties.JSON_PREFIX;

    @Getter
    private Boolean trustedUser = DefaultApplicationProperties.TRUSTED_USER;

    @Getter
    private final Http http = new Http();

    @Data
    public static class Http {

        @Getter
        private final Cache cache = new Cache();

        @Data
        public static class Cache {

            private int timeToLiveInDays = DefaultApplicationProperties.Http.Cache.TIME_TO_LIVE_IN_DAYS;

        }
    }
    /////////// HTTP Configurations - END ////////////

    @Getter
    private final Cors cors = new Cors();

    @Data
    public static class Cors {

        private String[] allowedOrigins = DefaultApplicationProperties.Cors.ALLOWED_ORIGINS;

        private List<String> allowedOriginPatterns = DefaultApplicationProperties.Cors.ALLOWED_ORIGIN_PATTERNS;

        private String[] allowedMethods = DefaultApplicationProperties.Cors.ALLOWED_METHODS;

        private String[] allowedHeaders = DefaultApplicationProperties.Cors.ALLOWED_HEADERS;

        private String[] exposedHeaders = DefaultApplicationProperties.Cors.EXPOSED_HEADERS;

        private long maxAge = DefaultApplicationProperties.Cors.MAX_AGE;
    }


    /////////// Reference Table Manager Configuration - START ////////////
    @Getter
    private final ReferenceTable referenceTable = new ReferenceTable();

    @Data
    public static class ReferenceTable {

        private String baseUrl = DefaultApplicationProperties.ReferenceTable.BASE_URL;
        private String serviceUrl = DefaultApplicationProperties.ReferenceTable.SERVICE_URL;
        private String serviceUrlForPreLoad = DefaultApplicationProperties.ReferenceTable.SERVICE_URL_FOR_PRE_LOAD;
        private List<String> preloadList = DefaultApplicationProperties.ReferenceTable.PRELOAD_LIST;
        private boolean preload = DefaultApplicationProperties.ReferenceTable.PRELOAD;

    }
    /////////// Reference Table Manager Configuration - END ////////////


    /////////// JWT Security Configuration - START ////////////
    @Getter
    private final JwtSecurity jwtSecurity = new JwtSecurity();

    @Data
    public static class JwtSecurity {
        private Boolean enableJwtSecurity = DefaultApplicationProperties.JwtSecurity.ENABLE_JWT_SECURITY;
        private String userValidationMode = DefaultApplicationProperties.JwtSecurity.USER_VALIDATION_MODE;
        private String userValidationURL = DefaultApplicationProperties.JwtSecurity.USER_VALIDATION_URL;
    }
    /////////// JWT Security Congiguration - END ////////////


    /////////// JMS Configuration - START ////////////
    @Getter
    private final JMS jms = new JMS();

    @Data
    public static class JMS {

        private String brokerUrl = DefaultApplicationProperties.JMS.BROKER_URL;
        private String user = DefaultApplicationProperties.JMS.USER;
        private String password = DefaultApplicationProperties.JMS.PASSWORD;

        @Getter
        private final Queue queue = new Queue();

        @Data
        public static class Queue {

            @Getter
            private final Audit audit = new Audit();

            @Getter
            private final Error error = new Error();

            @Data
            public static class Error {
                private String name = DefaultApplicationProperties.JMS.Queue.Error.NAME;
            }

            @Data
            public static class Audit {
                private String name = DefaultApplicationProperties.JMS.Queue.Audit.NAME;
            }

        }

    }

    /////////// JMS Configuration - END ////////////

    @Getter
    private final ResourceDocInfo resourceDocInfo = new ResourceDocInfo();

    @Data
    public static class ResourceDocInfo {

        private String title = DefaultApplicationProperties.ResourceDoc.TITLE;

        private String description = DefaultApplicationProperties.ResourceDoc.DESCRIPTION;

        private String version = DefaultApplicationProperties.ResourceDoc.VERSION;

        private String termsOfServiceUrl = DefaultApplicationProperties.ResourceDoc.TERMS_OF_SERVICE_URL;

        private String contactName = DefaultApplicationProperties.ResourceDoc.CONTACT_NAME;

        private String contactUrl = DefaultApplicationProperties.ResourceDoc.CONTACT_URL;

        private String contactEmail = DefaultApplicationProperties.ResourceDoc.CONTACT_EMAIL;

        private String license = DefaultApplicationProperties.ResourceDoc.LICENSE;

        private String licenseUrl = DefaultApplicationProperties.ResourceDoc.LICENSE_URL;

        private String includePattern = DefaultApplicationProperties.ResourceDoc.DEFAULT_INCLUDE_PATTERN;

        private String managementIncludePattern = DefaultApplicationProperties.ResourceDoc.MANAGEMENT_INCLUDE_PATTERN;

        private String host = DefaultApplicationProperties.ResourceDoc.HOST;

        private String[] protocols = DefaultApplicationProperties.ResourceDoc.PROTOCOLS;

        private Server[] servers = {};

        private boolean useDefaultResponseMessages = DefaultApplicationProperties.ResourceDoc.USE_DEFAULT_RESPONSE_MESSAGES;

        @Data
        public static class Server {

            private String name;

            private String url;

            private String description;
        }
    }

    @Getter
    private final TimeTravel timeTravel = new TimeTravel();

    @Data
    public static class TimeTravel {

        private Boolean enabled = false;
    }

    @Getter
    public static Jackson jackson = new Jackson();

    @Data
    public static class Jackson {

        private String datePattern = "MM-dd-yyyy";
        private String TimePattern = "HH:mm:ss";
    }

}
