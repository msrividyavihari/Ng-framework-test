package com.deloitte.nextgen.framework.web.filters;

import com.deloitte.nextgen.framework.web.filters.plugin.ClientIPPlugin;
import com.deloitte.nextgen.framework.web.filters.plugin.RequestIDGeneratorPlugin;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Configuration
@Order(1)
public class MDCFilter implements Filter {

    @Value("${spring.application.name}")
    private String projectName;

    private static final String APP_NAME = "appName";
    private static final String CLIENT_IP = "clientIP";
    private static final String REQUEST_ID = "requestId";

    private static final List<FilterPlugin> plugins = new ArrayList<>();

    static {
        plugins.add(new ClientIPPlugin(CLIENT_IP));
        plugins.add(new RequestIDGeneratorPlugin(REQUEST_ID));
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Do nothing because there is nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // process plugins
        for (FilterPlugin plugin : plugins) {
            plugin.addToDigest(request);
        }

        if (StringUtils.isNotEmpty(projectName)) {
            MDC.put(APP_NAME, projectName);
        } else {
            MDC.put(APP_NAME, "DefaultAppName");
        }

        // forward to the chain for processing
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

    @Override
    public void destroy() {
        // Do nothing because there is nothing to destroy
    }
}
