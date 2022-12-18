package com.example.offerserver.controller;

import com.example.offerserver.offerservice.task1.Client;
import com.example.offerserver.offerservice.task1.Position;
import com.example.offerserver.offerservice.task1.Stuff;
import com.example.offerserver.repository.PositionRepository;
import com.example.offerserver.repository.StuffRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/stuff")
public class StuffListController {
    @Autowired
    StuffRepository repository;
    @Autowired
    PositionRepository repo;
    @GetMapping("")
    public ResponseEntity<List<Stuff>> getStuffList(){
        List<Position> pos = repo.findAll();
        List<Stuff> stuff = repository.findAll();
        return new ResponseEntity<>(stuff, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Stuff> getStuffById(@PathVariable("id") UUID id){
        List<Position> pos = repo.findAll();
        List<Stuff> all = repository.findAll();
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }
}
