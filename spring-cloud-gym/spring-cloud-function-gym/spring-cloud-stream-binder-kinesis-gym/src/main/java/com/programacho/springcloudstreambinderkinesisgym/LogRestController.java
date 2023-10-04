package com.programacho.springcloudstreambinderkinesisgym;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/logs")
public class LogRestController {

    private final LogSource source;

    public LogRestController(LogSource source) {
        this.source = source;
    }

    @PostMapping("")
    public void send() {
        source.sendLog(Map.of(
                "foo", UUID.randomUUID().toString(),
                "bar", UUID.randomUUID().toString(),
                "baz", UUID.randomUUID().toString()
        ));
    }
}
