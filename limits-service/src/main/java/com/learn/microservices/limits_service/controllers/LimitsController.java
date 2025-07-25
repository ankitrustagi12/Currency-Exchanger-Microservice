package com.learn.microservices.limits_service.controllers;

import com.learn.microservices.limits_service.config.LimitsConfiguration;
import com.learn.microservices.limits_service.entity.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfiguration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
        //return new Limits(1,1000);
    }
}
