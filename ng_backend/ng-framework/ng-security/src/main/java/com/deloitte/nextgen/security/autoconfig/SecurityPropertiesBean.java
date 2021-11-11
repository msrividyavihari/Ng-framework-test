package com.deloitte.nextgen.security.autoconfig;

import lombok.Getter;

@Getter
public class SecurityPropertiesBean {

    private String securityUri;
    private String valid;
    private String refresh;
    private Boolean testing;

    public SecurityPropertiesBean(SecurityProperties.Security security) {
        this.securityUri = security.getSecurityUri();
        this.refresh = security.getSecurityUri() + security.getRefresh();
        this.valid = security.getSecurityUri() + security.getValid();
        this.testing = security.getTesting();
    }
}
