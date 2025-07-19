package com.learn.microservices.currency_exchange.repository;

import com.learn.microservices.currency_exchange.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
