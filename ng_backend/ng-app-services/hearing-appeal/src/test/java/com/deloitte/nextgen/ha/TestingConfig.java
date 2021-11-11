package com.deloitte.nextgen.ha;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest(properties = {"hearing-appeal.data-collection.url:http://localhost:10100/data-collection/api123", "hearing-appeal.data-collection.get-individual-end-point:xyz"})
@EnableConfigurationProperties
@ContextConfiguration(classes = TestingConfig.Dummy.class)
public class TestingConfig {

    @Value("${hearing-appeal.data-collection.get-individual-end-point:${hearing-appeal.data-collection.url:'http://localhost:10100/data-collection/api'}/v1/individuals/{individualId}}")
    private String getIndividualEndPoint;

    @Test
    public void testValueAnnotation() {
        log.warn("getIndividualEndPoint " + getIndividualEndPoint);
    }

    public static class Dummy {
    }
}
