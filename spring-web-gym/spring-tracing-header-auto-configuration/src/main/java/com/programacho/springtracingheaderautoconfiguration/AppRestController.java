package com.programacho.springtracingheaderautoconfiguration;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    @PostMapping("/")
    public void printHeaders(@RequestHeader HttpHeaders headers) {
        headers.forEach((k, v) -> System.out.printf("%s: %s\n", k, v));
    }
}
