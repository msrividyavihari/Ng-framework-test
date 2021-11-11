package com.deloitte.nextgen.framework.rt;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author nishmehta on 22/04/2021 4:06 PM
 * @project ng-fw-service
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ReferenceTableServiceApplication.class);
    }
}
