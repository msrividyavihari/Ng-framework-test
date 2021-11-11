package com.deloitte.nextgen.ha.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(properties = {"http-client-config.channel-options.CONNECT_TIMEOUT_MILLIS:30000", "http-client-config.read-time-out:100s"})
@EnableConfigurationProperties
@ContextConfiguration(classes = DefaultWebClientConfiguration.class)
public class DefaultWebClientConfigurationTest {

    @Autowired
    private DefaultWebClientConfiguration.HttpClientConfiguration parameters;

    @Test
    public void testWebClientConfigInitialization(){
        assertThat(parameters.getReadTimeOut().getSeconds()).isEqualTo(100);
    }
}
