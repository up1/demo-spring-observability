package com.example.demo_observable;


import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    @Observed(name = "hello-controller", contextualName = "say-hi")
    public String sayHi() {
        log.info("Called function sayHi");
        helloService.doSth();
        return "Hello Spring Boot";
    }

}
