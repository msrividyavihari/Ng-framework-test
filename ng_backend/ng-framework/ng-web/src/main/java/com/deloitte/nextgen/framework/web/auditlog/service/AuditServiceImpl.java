package com.deloitte.nextgen.framework.web.auditlog.service;


import com.deloitte.nextgen.framework.commons.constants.Constants;
import com.deloitte.nextgen.framework.commons.enums.AuditContextType;
import com.deloitte.nextgen.framework.commons.enums.AuditLogType;
import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;
import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogDto;
import com.deloitte.nextgen.framework.commons.spi.AuditLogWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditLogWriter auditLogWriter;

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {
            ObjectMapper mapper = new ObjectMapper();

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            try {
                stringBuilder.append(mapper.writeValueAsString(body));
            } catch (JsonProcessingException e) {
                log.warn(e.toString());
            }
        }
        AuTxnLogDto auTxnLogDto = new AuTxnLogDto();
        auTxnLogDto.setActionName(httpServletRequest.getMethod());

        auTxnLogDto.setIpAddress(httpServletRequest.getRemoteAddr());
        auTxnLogDto.setServiceName(httpServletRequest.getRequestURI());
        if (!parameters.isEmpty()) {
            auTxnLogDto.setAuditData(stringBuilder.toString());
        }

        if (body != null) {
            auTxnLogDto.setAuditData(stringBuilder.toString());
        }

        auTxnLogDto.setModeCode(AuditLogType.RE);
        AuTxnLogContextDto contextDto = new AuTxnLogContextDto();
        contextDto.setAuTxnLog(auTxnLogDto);
        contextDto.setContextType(AuditContextType.RR);
        contextDto.setCorrelationId(httpServletRequest.getAttribute(Constants.Headers.CORRELATION_ID).toString());

        auditLogWriter.writeLog(contextDto);
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse,
                            Object body) {
        try {
            AuTxnLogDto auTxnLogDto = new AuTxnLogDto();

            StringBuilder stringBuilder = new StringBuilder();

            auTxnLogDto.setActionName(httpServletRequest.getMethod());
            auTxnLogDto.setIpAddress(httpServletRequest.getRemoteAddr());
            auTxnLogDto.setServiceName(httpServletRequest.getRequestURI());

            writeBodyToStringBuilder(stringBuilder, body);

            auTxnLogDto.setModeCode(AuditLogType.RS);
            AuTxnLogContextDto contextDto = new AuTxnLogContextDto();
            auTxnLogDto.setAuditData(stringBuilder.toString());
            contextDto.setCorrelationId(buildHeadersMap(httpServletResponse).get(Constants.Headers.CORRELATION_ID));
            contextDto.setAuTxnLog(auTxnLogDto);
            contextDto.setContextType(AuditContextType.RR);
            auditLogWriter.writeLog(contextDto);


        } catch (Exception e) {
            log.warn(e.toString());
            e.printStackTrace();
        }
    }

    private void writeBodyToStringBuilder(StringBuilder stringBuilder, Object body) {

        if (body != null) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            try {
                stringBuilder.append(mapper.writeValueAsString(body));
            } catch (JsonProcessingException e) {
                log.warn(e.toString());
            }
        }
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }

}
