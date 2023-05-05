package com.programacho.springamqpbasic;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String SPRING_AMQP_BASIC = "spring-amqp-basic";

    @Bean
    public Queue springAmqpBasic() {
        return new Queue(SPRING_AMQP_BASIC);
    }
}
