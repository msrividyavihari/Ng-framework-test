package com.deloitte.nextgen.framework.web.filters;

import com.deloitte.nextgen.framework.commons.CorrelationIdHolder;
import com.deloitte.nextgen.framework.commons.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CorrelationHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing to initialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String currentCorrId = httpServletRequest.getHeader(Constants.Headers.CORRELATION_ID);

        if (currentCorrId == null) {
            currentCorrId = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            httpServletRequest.setAttribute(Constants.Headers.CORRELATION_ID, currentCorrId);
            log.info("No correlationId found in Header. Generated : " + currentCorrId);
        } else {
            log.info("Found correlationId in Header : " + currentCorrId);
        }

        CorrelationIdHolder.setId(currentCorrId);
        try {
            ((HttpServletResponse) response).addHeader(Constants.Headers.CORRELATION_ID, currentCorrId);
            filterChain.doFilter(httpServletRequest, response);
        }
        finally {
            CorrelationIdHolder.removeId();
        }

    }

    @Override
    public void destroy() {
        // Nothing to destroy
    }
}
