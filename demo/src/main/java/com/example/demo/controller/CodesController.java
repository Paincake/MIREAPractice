package com.example.demo.controller;

import com.example.demo.model.CurrencyExchange;
import com.example.demo.repository.H2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class CodesController {
    @Autowired
    private H2Repository repository;

    @GetMapping("currency/code")
    public ResponseEntity<List<String>> getAllCodes() {
        List<String> res = new ArrayList<>();
        List<CurrencyExchange> tmp = repository.findAll();
        for(CurrencyExchange cur : tmp){
            res.add(cur.getCurrencyCode());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



}
