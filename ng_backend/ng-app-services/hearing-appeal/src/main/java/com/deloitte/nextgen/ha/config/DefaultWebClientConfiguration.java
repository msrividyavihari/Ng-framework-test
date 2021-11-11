package com.deloitte.nextgen.ha.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TODO: Unable to made it work with {@link org.springframework.boot.web.reactive.function.client.WebClientCustomizer}
 */
@Configuration
@AutoConfigureBefore(ClientHttpConnectorAutoConfiguration.class)
@ConditionalOnClass(WebClient.class)
@Slf4j
@SuppressWarnings("unused")
public class DefaultWebClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    HttpClient httpClient(HttpClientConfiguration httpClientConfig) {
        log.info("Creating HttpClient with {} ", httpClientConfig);
        // Must assign the httpClient after each operation .. doesn't work with fluent pattern
        HttpClient httpClient = HttpClient.create();
        for (Map.Entry<String, Object> entrySet : httpClientConfig.getChannelOptions().entrySet()) {
            httpClient = httpClient.option(ChannelOption.valueOf(entrySet.getKey().toUpperCase()), entrySet.getValue());
        }
        return httpClient
                .responseTimeout(httpClientConfig.getResponseTimeOut())
                .doOnConnected(c -> c.addHandlerLast(new ReadTimeoutHandler(httpClientConfig.getReadTimeOut().toMillis(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(httpClientConfig.getWriteTimeOut().toMillis(), TimeUnit.MILLISECONDS)));
    }

    @Bean
    @ConditionalOnMissingBean
    ReactorClientHttpConnector reactorClientHttpConnector(HttpClient httpClient) {
        return new ReactorClientHttpConnector(httpClient);
    }

    @Bean
    @ConditionalOnMissingBean
    WebClient webClient(WebClient.Builder builder, ReactorClientHttpConnector restClientHttpConnector) {
        log.info("Creating WebClient using WebClient.Builder of {} with {} ", builder, restClientHttpConnector);
        return builder
                .clientConnector(restClientHttpConnector)
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    // Supports unknown status codes returned by the client unlike clientResponse.statusCode().isError()
                    if (HttpStatus.Series.CLIENT_ERROR.equals(HttpStatus.Series.resolve(clientResponse.rawStatusCode()))
                            || HttpStatus.Series.SERVER_ERROR.equals(HttpStatus.Series.resolve(clientResponse.rawStatusCode()))
                       ) {
                        return clientResponse
                                .createException()
                                .flatMap(Mono::error);
                    }
                    return Mono.just(clientResponse);
                }))
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    HttpClientConfiguration httpClientConfiguration(HttpClientConfiguration.HttpClientConfigurationBuilder builder) {
        HttpClientConfiguration params = builder.build();
        log.info("Creating instance of HttpClientConfiguration with {} ", params);
        return params;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConfigurationProperties(prefix = "http-client-config")
    @ConditionalOnMissingBean
    HttpClientConfiguration.HttpClientConfigurationBuilder httpClientConfigurationBuilder() {
        return HttpClientConfiguration.builder();
    }

    @Builder(setterPrefix = "set")
    @Value
    @EqualsAndHashCode
    public static class HttpClientConfiguration {

        private static final int DEFAULT_TIME_OUT = 30;

        @Builder.Default
        Map<String, Object> channelOptions = Collections.singletonMap("CONNECT_TIMEOUT_MILLIS", 5000);
        @Builder.Default
        Duration readTimeOut = Duration.ofSeconds(DEFAULT_TIME_OUT);
        @Builder.Default
        Duration writeTimeOut = Duration.ofSeconds(DEFAULT_TIME_OUT);
        @Builder.Default
        Duration responseTimeOut = Duration.ofSeconds(DEFAULT_TIME_OUT);
    }
}