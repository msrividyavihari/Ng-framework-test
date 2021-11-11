package com.deloitte.nextgen.security.response.authorize;

import com.deloitte.nextgen.security.autoconfig.SecurityPropertiesBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ResponseAuthorization implements ResponseBodyAdvice {


    @Autowired
    WebClient.Builder builder;

    @Autowired
    SecurityPropertiesBean securityPropertiesBean;

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.info("ResponseAuthorization -- supports");
        return !securityPropertiesBean.getTesting();
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("ResponseAuthorization -- beforeBodyWrite");
        List<String> authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION);

        String authToken = "";
        if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
            authToken = authorizationHeader.get(0); //jwt / smal
        }
        String authHeader = builder.build()
                .put()
                .uri(securityPropertiesBean.getRefresh())
                .body(BodyInserters.fromObject(authToken.substring(7)))
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .retrieve().bodyToMono(String.class)
                .block();
        response.getHeaders().add(HttpHeaders.AUTHORIZATION, authHeader);
        return body;
    }
}
