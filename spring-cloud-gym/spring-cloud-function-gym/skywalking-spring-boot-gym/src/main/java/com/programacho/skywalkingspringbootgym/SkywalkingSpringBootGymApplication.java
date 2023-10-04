package com.programacho.skywalkingspringbootgym;

import org.apache.skywalking.apm.meter.micrometer.SkywalkingConfig;
import org.apache.skywalking.apm.meter.micrometer.SkywalkingMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SkywalkingSpringBootGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingSpringBootGymApplication.class, args);
    }

    @Bean
    public SkywalkingMeterRegistry meterRegistry() {
        SkywalkingConfig config = SkywalkingConfig.DEFAULT;

        return new SkywalkingMeterRegistry(config);
    }
}
