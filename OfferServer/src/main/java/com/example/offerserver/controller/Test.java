package com.example.offerserver.controller;

import com.example.offerserver.offerservice.task1.Offer;

import java.util.Map;

public class Test {
    public static void main(String[] args){
        Offer one = new Offer();
        Offer two = new Offer();
        Map<Offer, Integer> test = Map.of(one, 10);
        System.out.println(test.get(two));
    }
}
