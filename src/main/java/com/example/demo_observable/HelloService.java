package com.example.demo_observable;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    @Observed(name = "hello-service", contextualName = "do-sth")
    public void doSth() {
        log.info("Called doSth()");
    }

}
