package com.programacho.springamqpbasic;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringAmqpBasicProducer {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    public SpringAmqpBasicProducer(
            RabbitTemplate rabbitTemplate,
            Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @PostMapping("/produce")
    public void produce(@RequestBody String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}
