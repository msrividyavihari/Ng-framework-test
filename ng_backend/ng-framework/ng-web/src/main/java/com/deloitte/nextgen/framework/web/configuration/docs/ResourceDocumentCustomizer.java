package com.deloitte.nextgen.framework.web.configuration.docs;

import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Server;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author nishmehta
 * @since 1.0.0
 */


public class ResourceDocumentCustomizer implements DocumentCustomizer, Ordered {

    public static final int DEFAULT_ORDER = 0;

    private int order = DEFAULT_ORDER;

    ApplicationProperties.ResourceDocInfo properties;

    public ResourceDocumentCustomizer(ApplicationProperties.ResourceDocInfo properties) {
        this.properties = properties;
    }

    @Override
    public void customize(Docket docket) {

        Contact contact = new Contact(
                properties.getContactName(),
                properties.getContactUrl(),
                properties.getContactEmail()
        );

        ApiInfo apiInfo = new ApiInfo(
                properties.getTitle(),
                properties.getDescription(),
                properties.getVersion(),
                properties.getTermsOfServiceUrl(),
                contact,
                properties.getLicense(),
                properties.getLicenseUrl(),
                new ArrayList<>()
        );

        for (ApplicationProperties.ResourceDocInfo.Server server : properties.getServers()) {
            docket.servers(new Server(server.getName(), server.getUrl(), server.getDescription(),
                    Collections.emptyList(), Collections.emptyList()));
        }

        docket.host(properties.getHost())
                .protocols(new HashSet<>(Arrays.asList(properties.getProtocols())))
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(properties.isUseDefaultResponseMessages())
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(ServerHttpRequest.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(properties.getIncludePattern()))
                .build();

    }

    public void setOrder(int order) {
        this.order = order;
    }


    @Override
    public int getOrder() {
        return order;
    }
}
