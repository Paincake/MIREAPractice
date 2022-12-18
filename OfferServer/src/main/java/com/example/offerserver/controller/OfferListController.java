package com.example.offerserver.controller;


import com.example.offerserver.dto.OfferStatistics;
import com.example.offerserver.offerservice.task1.*;
import com.example.offerserver.offerservice.task3.StatCounter;
import com.example.offerserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/api/v1/offer")
public class OfferListController {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    StuffRepository stuffRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PositionRepository positionRepository;

    @GetMapping("")
    public ResponseEntity<List<Offer>> getOfferList() {
        List<Position> pos = positionRepository.findAll();
        List<Stuff> stuff = stuffRepository.findAll();
        List<Office> office = officeRepository.findAll();
        List<Client> client = clientRepository.findAll();
        List<Offer> result = offerRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id") UUID id) {
        List<Position> pos = positionRepository.findAll();
        List<Stuff> stuff = stuffRepository.findAll();
        List<Office> office = officeRepository.findAll();
        List<Client> client = clientRepository.findAll();
        List<Offer> result = offerRepository.findAll();
        return new ResponseEntity(offerRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<OfferStatistics> getOfferStatistic() {
        List<Position> pos = positionRepository.findAll();
        List<Stuff> stuff = stuffRepository.findAll();
        List<Client> client = clientRepository.findAll();
        StatCounter counter = StatCounter.getInstance();

        List<Offer> RZHAKA = StatCounter.offers;
        Map<Stuff, Integer> KEK = StatCounter.stuffOffers;
        List<Offer> PRIKOL = counter.getOffers();

        Map<String, Integer> stuffStat = new HashMap<>();
        Map<String, Integer> clientStat = new HashMap<>();
        Set<String> clientSurnames = new HashSet<>();

        for (Stuff s : stuff) {
            Integer res = KEK.get(s);
            stuffStat.put(String.valueOf(s.getId()), res);
        }
        for (Client c : client) {
            clientStat.put(String.valueOf(c.getId()), counter.getClientOffersCount(c));
            clientSurnames.add(c.getSurname());
        }
        return new ResponseEntity<>(new OfferStatistics(stuffStat, clientStat, clientSurnames), HttpStatus.OK);
    }
}
