package com.deloitte.nextgen.security.autoconfig;


public final class SecurityDefaults {

    private SecurityDefaults() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SECURITY_URI = "http://localhost:8083/security/";
    public static final String VALID = "valid";
    public static final String REFRESH = "refreshToken";
    public static final Boolean TESTING = false;
}
