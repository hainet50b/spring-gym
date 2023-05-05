package com.programacho.springwebhttpinterface.exception;

import org.springframework.web.service.annotation.GetExchange;

public interface ExceptionClient {

    @GetExchange("/timeout")
    void timeout();

    @GetExchange("/bad-request")
    void badRequest();

    @GetExchange("/internal-server-error")
    void internalServerError();
}
