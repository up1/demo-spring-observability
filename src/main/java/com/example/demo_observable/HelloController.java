package com.example.demo_observable;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);


    @GetMapping("/hello")
    public String sayHi() {
        log.info("Called function sayHi");
        return "Hello Spring Boot";
    }

}
