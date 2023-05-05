package com.programacho.springwebhttpinterface;

import com.programacho.springwebhttpinterface.exception.ExceptionClient;
import com.programacho.springwebhttpinterface.user.UserClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(3_000, TimeUnit.MILLISECONDS))
                );

        WebClient webClient = builder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:8080")
//                .baseUrl("http://192.0.2.1:8080") // Connect timed out
                .build();

        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .blockTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    public UserClient userClient(HttpServiceProxyFactory factory) {
        return factory.createClient(UserClient.class);
    }

    @Bean
    public ExceptionClient exceptionClient(HttpServiceProxyFactory factory) {
        return factory.createClient(ExceptionClient.class);
    }
}
