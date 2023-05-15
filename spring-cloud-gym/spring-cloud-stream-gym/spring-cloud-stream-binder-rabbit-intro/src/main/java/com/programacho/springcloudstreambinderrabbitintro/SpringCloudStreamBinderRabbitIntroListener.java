package com.programacho.springcloudstreambinderrabbitintro;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class SpringCloudStreamBinderRabbitIntroListener {

    @Bean
    public Consumer<String> log() {
        return m -> {
            // Let's make a great code!
            System.out.println(m);
        };
    }
}
