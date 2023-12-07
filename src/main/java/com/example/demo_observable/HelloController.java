package com.example.demo_observable;


import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static net.logstash.logback.argument.StructuredArguments.entries;
import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendFields;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
//    private static final Logger log = LoggerFactory.getLogger("json-log");

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    @Observed(name = "hello-controller", contextualName = "say-hi")
    public String sayHi() {
        // Simple log
        log.info("Called function sayHi");

        // Structured log
        log.info("Order saved {}", keyValue("orderId", "0001"));
        MyData myData = new MyData("value1", "value2");
        log.info("Order saved {}", keyValue("my_data", myData));
        log.info("Order saved {}", keyValue("orderId", "0001"), keyValue("my_data", myData));

        helloService.doSth();
        return "Hello Spring Boot";
    }

}


