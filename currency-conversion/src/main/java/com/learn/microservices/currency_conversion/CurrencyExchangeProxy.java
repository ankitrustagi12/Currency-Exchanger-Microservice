package com.learn.microservices.currency_conversion;

import com.learn.microservices.currency_conversion.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Still, we are calling the  url, to be fixed with Eureka
//@FeignClient(name="currency-exchange", url = "localhost:8000")

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
