package com.example.demo.controller;

import com.example.demo.model.CurrencyExchange;
import com.example.demo.repository.H2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ValueController {
    @Autowired
    private H2Repository repository;

    @GetMapping("currencies/value")
    public ResponseEntity<List<CurrencyExchange>> valuteMax(@RequestParam(value="maxValue", required = false)Double maxValue){
        List<CurrencyExchange> tmp = repository.findAll();
        List<CurrencyExchange> res = new ArrayList<>();
        for(CurrencyExchange cur : tmp){
            if(cur.getValue() < maxValue){
                res.add(cur);
            }
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
