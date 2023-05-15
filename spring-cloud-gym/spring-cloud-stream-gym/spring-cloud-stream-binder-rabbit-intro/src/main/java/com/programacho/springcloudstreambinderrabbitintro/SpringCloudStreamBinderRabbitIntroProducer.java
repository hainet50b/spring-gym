package com.programacho.springcloudstreambinderrabbitintro;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class SpringCloudStreamBinderRabbitIntroProducer {

    private final StreamBridge streamBridge;

    public SpringCloudStreamBinderRabbitIntroProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void log(String message) {
        streamBridge.send("log-out-0", message);
    }
}
