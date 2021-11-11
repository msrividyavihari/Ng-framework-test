package com.deloitte.nextgen.framework.web.filters.plugin;

import com.deloitte.nextgen.framework.web.filters.FilterPlugin;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nishmehta on 23/10/2020 1:41 PM
 * @project ng-framework
 */
@Slf4j
public class ClientIPPlugin implements FilterPlugin {

    private String clientIp;

    public ClientIPPlugin(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public void addToDigest(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-FORWARDED-FOR");
        if (forwardedFor == null) {
            forwardedFor = request.getRemoteAddr();
        }
        MDC.put(clientIp, forwardedFor);
    }
}
