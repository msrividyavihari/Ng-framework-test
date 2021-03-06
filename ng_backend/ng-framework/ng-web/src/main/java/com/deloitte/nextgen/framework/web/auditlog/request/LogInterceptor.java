package com.deloitte.nextgen.framework.web.auditlog.request;

import com.deloitte.nextgen.framework.web.auditlog.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ketan Kulkarni on 10/06/2021 03:50 PM
 * @project ng-framework / audit consumer
 * @implNote LogInterceptor to log HTTP GET Method
 */

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    AuditService loggingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {


        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            loggingService.logRequest(request, null);
            // response.getOutputStream().
        }

        return true;
    }


}
