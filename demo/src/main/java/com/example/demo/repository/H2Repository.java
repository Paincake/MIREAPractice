package com.example.demo.repository;

import com.example.demo.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends JpaRepository<CurrencyExchange, String> {

}
