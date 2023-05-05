package com.programacho.springamqpintro;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String SPRING_AMQP_INTRO = "spring-amqp-intro";

    @Bean
    public Queue springAmqpIntro() {
        return new Queue(SPRING_AMQP_INTRO);
    }
}
