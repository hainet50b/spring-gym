package com.programacho.springresourceinterface;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

@SpringBootApplication
public class SpringResourceInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringResourceInterfaceApplication.class, args);
    }

    @Bean
    public CommandLineRunner classPathResource() {
        return args -> {
            try (InputStream in = new ClassPathResource("/foo.txt").getInputStream()) {
                System.out.println("[ClassPathResource]");
                System.out.println(StreamUtils.copyToString(in, Charset.defaultCharset()));
            }
        };
    }

    @Bean
    public CommandLineRunner urlResource() {
        return args -> {
            try (InputStream in = new UrlResource("https://pages.programacho.com/index.md").getInputStream()) {
                System.out.println("[UrlResource]");
                System.out.println(StreamUtils.copyToString(in, Charset.defaultCharset()));
            }
        };
    }

    @Bean
    public CommandLineRunner resourceLoader(ResourceLoader resourceLoader) {
        return args -> {
            try (InputStream in = resourceLoader.getResource("classpath:/foo.txt").getInputStream()) {
                System.out.println("[ResourceLoader]");
                System.out.println(StreamUtils.copyToString(in, Charset.defaultCharset()));
            }
        };
    }
}
