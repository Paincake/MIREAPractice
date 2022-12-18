package com.example.offerserver.controller;

import com.example.offerserver.offerservice.task1.Position;
import com.example.offerserver.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/position")
public class PoisitionListController {
    @Autowired
    PositionRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Position>> getPositionList(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Position>> getPositionById(@PathVariable("id") UUID id){
        List<Position> positions = repository.findAll();
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }
}
