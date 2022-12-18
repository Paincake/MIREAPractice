package com.example.offerserver.controller;

import com.example.offerserver.offerservice.task1.Client;
import com.example.offerserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/client")
public class ClientListController {
    @Autowired
    private ClientRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Client>> getClientList(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id")UUID id){
        List<Client> all = repository.findAll();
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }
}
