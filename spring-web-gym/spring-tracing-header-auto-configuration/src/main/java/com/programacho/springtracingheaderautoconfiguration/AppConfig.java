package com.programacho.springtracingheaderautoconfiguration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate autoConfiguredRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8080")
                .build();
    }

    @Bean
    public RestTemplate selfConfiguredRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .build();
    }

    @Bean
    public WebClient autoConfiguredWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public WebClient selfConfiguredWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
