package com.deloitte.nextgen.framework.web.filters.plugin;

import com.deloitte.nextgen.framework.commons.CorrelationIdHolder;
import com.deloitte.nextgen.framework.web.filters.FilterPlugin;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nishmehta on 10/02/2021 11:42 AM
 * @project ng-framework
 */
public class RequestIDGeneratorPlugin implements FilterPlugin {

    private String requestId;

    public RequestIDGeneratorPlugin(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public void addToDigest(HttpServletRequest request) {

        MDC.put(requestId, CorrelationIdHolder.getId());
    }
}
