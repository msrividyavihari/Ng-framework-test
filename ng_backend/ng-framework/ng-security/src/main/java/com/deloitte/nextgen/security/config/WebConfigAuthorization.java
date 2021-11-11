package com.deloitte.nextgen.security.config;


import com.deloitte.nextgen.security.autoconfig.SecurityPropertiesBean;
import com.deloitte.nextgen.security.interceptors.AuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(SecurityPropertiesBean.class)
@Slf4j
public class WebConfigAuthorization implements WebMvcConfigurer {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations and resource handler requests.
     * Interceptors can be registered to apply to all requests or be limited
     * to a subset of URL patterns.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("WebConfigAuthorization -- addInterceptors -- before add");

        log.info("WebConfigAuthorization -- addInterceptors -- added");
    }
}
