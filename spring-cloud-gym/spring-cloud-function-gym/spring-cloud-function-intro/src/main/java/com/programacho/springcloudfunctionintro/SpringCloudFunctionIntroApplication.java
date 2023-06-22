package com.programacho.springcloudfunctionintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SpringCloudFunctionIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionIntroApplication.class, args);
    }

    @Bean
    public Function<String, String> hello() {
        return v -> String.format("Hello %s. Welcome to Spring Cloud Function!", v);
    }
}
