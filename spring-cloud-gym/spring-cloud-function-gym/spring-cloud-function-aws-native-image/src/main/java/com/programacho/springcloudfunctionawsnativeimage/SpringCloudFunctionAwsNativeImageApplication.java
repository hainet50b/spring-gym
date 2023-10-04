package com.programacho.springcloudfunctionawsnativeimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class SpringCloudFunctionAwsNativeImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionAwsNativeImageApplication.class, args);
    }

    @Bean
    public Function<Map<String, String>, String> hello() {
        return v -> String.format("Hello %s. Welcome to Spring Cloud Function!", v.get("name"));
    }
}
