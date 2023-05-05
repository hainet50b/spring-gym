package com.programacho.springamqpbasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = QueueConfig.SPRING_AMQP_BASIC)
public class SpringAmqpBasicConsumer {

    private static final Logger log = LoggerFactory.getLogger(SpringAmqpBasicConsumer.class);

    @RabbitHandler
    public void consume(String message) {
        log.info("Received a message: {}", message);
    }
}
