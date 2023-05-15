package com.programacho.springcloudstreambinderrabbitintro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCloudStreamBinderRabbitIntroRestController {

    private final SpringCloudStreamBinderRabbitIntroProducer producer;

    public SpringCloudStreamBinderRabbitIntroRestController(SpringCloudStreamBinderRabbitIntroProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/produce")
    public void produce(@RequestBody String message) {
        producer.log(message);
    }
}
