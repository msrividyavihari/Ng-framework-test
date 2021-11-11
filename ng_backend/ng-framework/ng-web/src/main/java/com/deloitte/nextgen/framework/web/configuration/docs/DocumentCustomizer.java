package com.deloitte.nextgen.framework.web.configuration.docs;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@FunctionalInterface
public interface DocumentCustomizer {

    void customize(Docket docket);

}
