package com.learn.microservices.currency_exchange.controllers;

import com.learn.microservices.currency_exchange.entity.CurrencyExchange;
import com.learn.microservices.currency_exchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);
//      new CurrencyExchange(1L,from,to, BigDecimal.valueOf(85) );

        if( null == currencyExchange ){
            throw new RuntimeException("Unable to find data");
        }

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
