package com.learn.microservices.currency_exchange.controllers;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    static int count = 0;

    @GetMapping("/sample-api")
    @Retry(name="sample-api", fallbackMethod = "hardCodedResponse")
    public String sampleAPI(){
        logger.info("Sample APi triggered for time -> {}", ++count);
        ResponseEntity<String> test = new RestTemplate().getForEntity("http://localhost:8080/dummy-url",String.class);
        return test.getBody();
    }

    public String hardCodedResponse(Exception ex){
        return "Fallback Response";
    }
}
