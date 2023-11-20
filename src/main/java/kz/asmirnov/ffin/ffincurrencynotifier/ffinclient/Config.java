package kz.asmirnov.ffin.ffincurrencynotifier.ffinclient;

import kz.asmirnov.ffin.ffincurrencynotifier.ffinclient.logging.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, LoggingInterceptor loggingInterceptor, @Value("${ffin-url}") String ffinUrl) {
        RestTemplate restTemplate = builder
                .rootUri(ffinUrl)
                .additionalInterceptors(loggingInterceptor)
                .build();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
        return restTemplate;
    }
}
