package com.programacho.springwebhttpinterface;

import com.programacho.springwebhttpinterface.exception.ExceptionClient;
import com.programacho.springwebhttpinterface.user.User;
import com.programacho.springwebhttpinterface.user.UserClient;
import io.netty.channel.ConnectTimeoutException;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@SpringBootApplication
public class SpringWebHttpInterfaceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebHttpInterfaceApplication.class, args);
        SpringApplication.exit(context);
    }

    private final Logger log = LoggerFactory.getLogger(SpringWebHttpInterfaceApplication.class);

    private final UserClient userClient;

    private final ExceptionClient exceptionClient;

    public SpringWebHttpInterfaceApplication(
            UserClient userClient,
            ExceptionClient exceptionClient) {
        this.userClient = userClient;
        this.exceptionClient = exceptionClient;
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // ユーザー検索
            log.info("Users: {}", userClient.findAll());
            log.info("User by id=1: {}", userClient.findById("1"));

            // ユーザー追加
            User user = new User("3", "Haine Takano");
            log.info("Save user: {}", user);
            userClient.save(user);

            // ユーザー検索
            log.info("Users: {}", userClient.findAll());
            log.info("User by id=3: {}", userClient.findById("3"));

            // 各種エラー時の挙動
            handle(exceptionClient::timeout);
            handle(exceptionClient::badRequest);
            handle(exceptionClient::internalServerError);
        };
    }

    private void handle(Runnable runnable) {
        try {
            runnable.run();
        } catch (WebClientRequestException e) {
            if (e.getCause() instanceof ConnectTimeoutException) {
                log.info("Exception when Connect timed out", e);
            } else if (e.getCause() instanceof ReadTimeoutException) {
                log.info("Exception when Read timed out", e);
            } else {
                log.info("Other WebClientRequestException", e);
            }
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                log.info("Exception when 400", e);
            } else if (e.getStatusCode().is5xxServerError()) {
                log.info("Exception when 500", e);
            } else {
                log.info("Other WebClientResponseException", e);
            }
        } catch (RuntimeException e) {
            log.info("Other exception", e);
        }
    }
}
