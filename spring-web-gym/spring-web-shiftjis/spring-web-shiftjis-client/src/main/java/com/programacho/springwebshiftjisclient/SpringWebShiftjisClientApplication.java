package com.programacho.springwebshiftjisclient;

import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SpringWebShiftjisClientApplication {

    private final Logger log = LoggerFactory.getLogger(SpringWebShiftjisClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebShiftjisClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner requestCheck(RestTemplate restTemplate) {
        return args -> {
            String url = "http://localhost:8080/request-check";

            // Default
            log.info("Request check with no charset");

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    RequestEntity.post(url)
                            .body("文字列"),
                    String.class
            );

            log.info("- Charset (Default): {}{}", responseEntity.getBody(), System.lineSeparator());
        };
    }

    @Bean
    public CommandLineRunner requestCheckShiftjis(RestTemplate restTemplate) {
        return args -> {
            String url = "http://localhost:8080/request-check";

            // Shift_JIS
            log.info("Request check with Shift_JIS");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("Shift_JIS")));

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    RequestEntity.post(url)
                            .headers(headers)
                            .body("文字列"),
                    String.class
            );

            log.info("- Charset (Shift_JIS): {}{}", responseEntity.getBody(), System.lineSeparator());
        };
    }

    @Bean
    public CommandLineRunner requestCheckUtf8(RestTemplate restTemplate) {
        return args -> {
            String url = "http://localhost:8080/request-check";

            // UTF-8
            log.info("Request check with UTF-8");

            HttpHeaders headersAsUtf8 = new HttpHeaders();
            headersAsUtf8.setContentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8));

            ResponseEntity<String> responseEntityAsUtf8 = restTemplate.exchange(
                    RequestEntity.post(url)
                            .headers(headersAsUtf8)
                            .body("文字列"),
                    String.class
            );

            log.info("- Charset (UTF-8): {}{}", responseEntityAsUtf8.getBody(), System.lineSeparator());
        };
    }

    @Bean
    public CommandLineRunner responseShiftjisWithoutCharset(RestTemplate restTemplate) {
        return args -> {
            log.info("Response Shift_JIS without charset");
            String url = "http://localhost:80/Shift_JIS.txt";

            ResponseEntity<byte[]> responseEntityAsBytes = restTemplate.getForEntity(url, byte[].class);
            printResponseEntityAsBytes(responseEntityAsBytes);

            ResponseEntity<String> responseEntityAsString = restTemplate.getForEntity(url, String.class);
            printResponseEntityAsString(responseEntityAsString);
        };
    }

    @Bean
    public CommandLineRunner responseShiftjisWithCharset(RestTemplate restTemplate) {
        return args -> {
            log.info("Response Shift_JIS with charset");
            String url = "http://localhost:80/shift-jis/Shift_JIS.txt";

            ResponseEntity<byte[]> responseEntityAsBytes = restTemplate.getForEntity(url, byte[].class);
            printResponseEntityAsBytes(responseEntityAsBytes);

            ResponseEntity<String> responseEntityAsString = restTemplate.getForEntity(url, String.class);
            printResponseEntityAsString(responseEntityAsString);
        };
    }

    @Bean
    public CommandLineRunner responseUtf8withoutCharset(RestTemplate restTemplate) {
        return args -> {
            log.info("Response UTF-8 without charset");
            String url = "http://localhost:80/UTF-8.txt";

            ResponseEntity<byte[]> responseEntityAsBytes = restTemplate.getForEntity(url, byte[].class);
            printResponseEntityAsBytes(responseEntityAsBytes);

            ResponseEntity<String> responseEntityAsString = restTemplate.getForEntity(url, String.class);
            printResponseEntityAsString(responseEntityAsString);
        };
    }

    @Bean
    public CommandLineRunner responseUtf8WithCharset(RestTemplate restTemplate) {
        return args -> {
            log.info("Response UTF-8 with charset");
            String url = "http://localhost:80/utf-8/UTF-8.txt";

            ResponseEntity<byte[]> responseEntityAsBytes = restTemplate.getForEntity(url, byte[].class);
            printResponseEntityAsBytes(responseEntityAsBytes);

            ResponseEntity<String> responseEntityAsString = restTemplate.getForEntity(url, String.class);
            printResponseEntityAsString(responseEntityAsString);
        };
    }

    private String detectCharset(byte[] value) {
        UniversalDetector detector = new UniversalDetector();

        detector.handleData(value);
        detector.dataEnd();

        String charset = detector.getDetectedCharset();

        detector.reset();

        return charset;
    }

    private void printResponseEntityAsBytes(ResponseEntity<byte[]> bytes) {
        log.info("- Charset: {}", detectCharset(bytes.getBody()));
    }

    private void printResponseEntityAsString(ResponseEntity<String> string) {
        log.info("- Response Body: {}", string.getBody());
    }
}
