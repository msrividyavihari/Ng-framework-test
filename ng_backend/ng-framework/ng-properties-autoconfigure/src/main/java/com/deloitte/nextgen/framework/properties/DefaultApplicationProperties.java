package com.deloitte.nextgen.framework.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DefaultApplicationProperties {

    public static final boolean TRUSTED_USER = true;
    public static final String JSON_PREFIX = ")]}',\n";
    public static final String BASE_PACKAGE = "com.deloitte.nextgen.framework";
    private static final String UTILITY_CLASS = "Utility class";
    private DefaultApplicationProperties() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static final class Http {

        private Http() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final class Cache {

            public static final int TIME_TO_LIVE_IN_DAYS = 1; // NOSONAR - Ignored because

            private Cache() {
                throw new IllegalStateException(UTILITY_CLASS);
            }
        }
    }

    public static final class Cors {

        public static final String[] ALLOWED_ORIGINS = {"http://localhost:5000", "http://15.207.142.199"};
        //Using Immutable List instead of Array, since arrays are not Immutable ...
        public static final List<String> ALLOWED_ORIGIN_PATTERNS = Collections.unmodifiableList(Arrays.asList("https?:\\/\\/localhost(?::\\d+)?", "https?:\\/\\/127\\.0\\.0\\.1(?::\\d+)?"));
        public static final String[] ALLOWED_METHODS = {"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH"};
        public static final String[] ALLOWED_HEADERS = {
                "Accept",
                "Accept-Encoding",
                "Accept-Language",
                "Cache-Control",
                "Connection",
                "Content-Length",
                "Content-Type",
                "Cookie",
                "Host",
                "Origin",
                "Pragma",
                "Referer",
                "User-Agent",
                "x-requested-with",
                "Authorization"};
        public static final String[] EXPOSED_HEADERS = {
                "Cache-Control",
                "Connection",
                "Content-Type",
                "Date",
                "Expires",
                "Pragma",
                "Server",
                "Set-Cookie",
                "Transfer-Encoding",
                "X-Content-Type-Options",
                "X-XSS-Protection",
                "X-Frame-Options",
                "X-Application-Context"};
        public static final long MAX_AGE = 3600L;

        private Cors() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }

    public static final class ReferenceTable {

        public static final String BASE_URL = "http://localhost:8080/rtservice/";
        public static final String SERVICE_URL = "getReferenceTable/{referencaTableName}";
        public static final String SERVICE_URL_FOR_PRE_LOAD = "getReferenceTables/";
        public static final List<String> PRELOAD_LIST = Arrays.asList("ABSENTPARENTADDRTYPE", "PROGRAM");
        public static final Boolean PRELOAD = false;
        private ReferenceTable() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }

    public static final class JwtSecurity {

        public static final Boolean ENABLE_JWT_SECURITY = true;
        public static final String USER_VALIDATION_MODE = "DAO";
        public static final String USER_VALIDATION_URL = "http://localhost:8083/validateUserName";
        private JwtSecurity() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }

    public static final class JMS {

        public static final String BROKER_URL = "tcp://localhost:61616?jms.useAsyncSend=true";
        public static final String USER = "admin";
        public static final String PASSWORD = "admin";
        private JMS() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final class Queue {

            private Queue() {
                throw new IllegalStateException(UTILITY_CLASS);
            }

            public static final class Error {

                public static final String NAME = "error.log";

                private Error() {
                    throw new IllegalStateException(UTILITY_CLASS);
                }
            }

            public static final class Audit {

                public static final String NAME = "audit.log";

                private Audit() {
                    throw new IllegalStateException(UTILITY_CLASS);
                }
            }

            public static final class AuditLog {

                public static final String NAME = "audit.queue";

                private AuditLog() {
                    throw new IllegalStateException(UTILITY_CLASS);
                }
            }
        }
    }

    public static final class ResourceDoc {

        public static final String TITLE = "Application API";
        public static final String DESCRIPTION = "This documentation provide api exposed by application";
        public static final String VERSION = "0.0.1";
        public static final String TERMS_OF_SERVICE_URL = null;
        public static final String CONTACT_NAME = "Deloitte";
        public static final String CONTACT_URL = null;
        public static final String CONTACT_EMAIL = null;
        public static final String LICENSE = null;
        public static final String LICENSE_URL = null;
        public static final String DEFAULT_INCLUDE_PATTERN = "(?!\\/error)\\/.*";
        public static final String MANAGEMENT_INCLUDE_PATTERN = "/management/.*";
        public static final String HOST = null;
        public static final String[] PROTOCOLS = {};
        public static final boolean USE_DEFAULT_RESPONSE_MESSAGES = true;
        public static final String RESOURCE_DOCUMENT_BASE_PACKAGE = "com.deloitte.nextgen";
        private ResourceDoc() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

    }
}
