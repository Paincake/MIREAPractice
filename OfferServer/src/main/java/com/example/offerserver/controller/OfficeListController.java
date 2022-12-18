package com.example.offerserver.controller;

import com.example.offerserver.offerservice.task1.Client;
import com.example.offerserver.offerservice.task1.Office;
import com.example.offerserver.repository.ClientRepository;
import com.example.offerserver.repository.OfficeRepository;
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
@RequestMapping("/api/v1/office")
public class OfficeListController {
    @Autowired
    private OfficeRepository repository;

    @GetMapping("")
    public ResponseEntity<List<Office>> getClientList(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getOfficeById(@PathVariable("id") UUID id){
        List<Office> all = repository.findAll();
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }
}
