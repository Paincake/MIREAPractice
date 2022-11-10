package com.example.demo;

import com.example.demo.model.CurrencyExchange;
import com.example.demo.repository.H2Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@EnableJpaRepositories
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner initDb(H2Repository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new CurrencyExchange(100, 100.2, "Шёл" ),
                    new CurrencyExchange(200, 100.3, "Мужик" ),
                    new CurrencyExchange(300, 100.4, "По" ),
                    new CurrencyExchange(400, 100.5, "Лесу" ),
                    new CurrencyExchange(500, 100.6, "Видит" ),
                    new CurrencyExchange(600, 100.7, "Шляпа лежит" ),
                    new CurrencyExchange(700, 100.8, "Надел её" ),
                    new CurrencyExchange(800, 100.9, "А она" ),
                    new CurrencyExchange(900, 200.1, "Ему" ),
                    new CurrencyExchange(1000, 200.2, "Как раз" )
            ));
        };
    }
}
