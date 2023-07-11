package kz.asmirnov.ffin.ffincurrencynotifier;

import kz.asmirnov.ffin.ffincurrencynotifier.logging.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class FFinCurrencyNotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(FFinCurrencyNotifierApplication.class, args);
    }

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
