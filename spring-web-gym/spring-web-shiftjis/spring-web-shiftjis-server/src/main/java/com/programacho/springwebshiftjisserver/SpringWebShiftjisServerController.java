package com.programacho.springwebshiftjisserver;

import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringWebShiftjisServerController {

    @PostMapping("/request-check")
    public String requestCheck(@RequestBody byte[] requestBody) {
        return detectCharset(requestBody);
    }

    @PostMapping("/request")
    public void request(
            @RequestHeader("Content-Type") String contentType,
            @RequestBody String requestBody) {
        System.out.println("Content-Type: " + contentType);
        System.out.println("Request Body: " + requestBody);
    }

    @GetMapping("/response/shift-jis")
    public String responseShiftjis() {
        return "しふとじす";
    }

    @GetMapping(path = "/response/utf-8", produces = "text/plain; charset=UTF-8")
    public String responseUtf8() {
        return "ゆーてぃーえふえいと";
    }

    private String detectCharset(byte[] value) {
        UniversalDetector detector = new UniversalDetector();

        detector.handleData(value);
        detector.dataEnd();

        String charset = detector.getDetectedCharset();

        detector.reset();

        return charset;
    }
}
