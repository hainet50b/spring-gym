package com.programacho.springamqpintro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAmqpIntroListener {

    @Bean
    public SpringAmqpIntroConsumer consumer() {
        return new SpringAmqpIntroConsumer();
    }
}
