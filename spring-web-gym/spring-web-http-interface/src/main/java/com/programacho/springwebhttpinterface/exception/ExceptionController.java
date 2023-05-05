package com.programacho.springwebhttpinterface.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ExceptionController {

    @GetMapping("/timeout")
    public void timeout() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/bad-request")
    public ResponseEntity<Void> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/internal-server-error")
    public ResponseEntity<Void> internalServerError() {
        return ResponseEntity.internalServerError().build();
    }
}
