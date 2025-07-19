package com.learn.microservices.currency_conversion.controllers;

import com.learn.microservices.currency_conversion.CurrencyExchangeProxy;
import com.learn.microservices.currency_conversion.entity.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        Map<String,String> uriVariables = Map.of("from",from,"to",to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        //new CurrencyConversion(1L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE);
        //currencyExchangeRepository.findByFromAndTo(from,to);

    //      new CurrencyConversion(1L,from,to, BigDecimal.valueOf(85) );

        if( null == currencyConversion){
            throw new RuntimeException("Unable to find data");
        }

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(), currencyConversion.getConversionMultiple().multiply( quantity ), currencyConversion.getEnvironment() );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveExchangeValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from,to);

        if( null == currencyConversion){
            throw new RuntimeException("Unable to find data");
        }

        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(), currencyConversion.getConversionMultiple().multiply( quantity ), currencyConversion.getEnvironment() );
    }
}
