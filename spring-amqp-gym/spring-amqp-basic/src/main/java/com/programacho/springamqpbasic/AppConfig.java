package com.programacho.springamqpbasic;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Queue springAmqpBasic() {
        return new Queue("spring-amqp-basic");
    }
}
