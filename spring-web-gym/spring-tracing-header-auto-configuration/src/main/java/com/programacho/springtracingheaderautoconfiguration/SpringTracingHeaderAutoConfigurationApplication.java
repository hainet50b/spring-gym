package com.programacho.springtracingheaderautoconfiguration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringTracingHeaderAutoConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication
                .run(SpringTracingHeaderAutoConfigurationApplication.class, args)
                .close();
    }

    @Bean
    public CommandLineRunner useAutoConfiguredRestTemplate(RestTemplate autoConfiguredRestTemplate) {
        return args -> {
            System.out.println("[AutoConfiguredRestTemplate]");
            autoConfiguredRestTemplate.postForEntity("/", null, String.class);
        };
    }

    @Bean
    public CommandLineRunner useSelfConfiguredRestTemplate(RestTemplate selfConfiguredRestTemplate) {
        return args -> {
            System.out.println("[SelfConfiguredRestTemplate]");
            selfConfiguredRestTemplate.postForEntity("/", null, String.class);
        };
    }

    @Bean
    public CommandLineRunner useAutoConfiguredWebClient(WebClient autoConfiguredWebClient) {
        return args -> {
            System.out.println("[AutoConfiguredWebClient]");
            autoConfiguredWebClient
                    .post()
                    .uri("/")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        };
    }

    @Bean
    public CommandLineRunner useSelfConfiguredWebClient(WebClient selfConfiguredWebClient) {
        return args -> {
            System.out.println("[SelfConfiguredWebClient]");
            selfConfiguredWebClient
                    .post()
                    .uri("/")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        };
    }
}
