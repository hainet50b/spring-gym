package com.programacho.springcloudstreambinderkinesisgym;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@Component
public class LogSource {

    private final BlockingQueue<Map<String, String>> logEvent = new LinkedBlockingQueue<>();

    @Bean
    public Supplier<Map<String, String>> produceLog() {
        return logEvent::poll;
    }

    public void sendLog(Map<String, String> log) {
        logEvent.add(log);
    }
}
