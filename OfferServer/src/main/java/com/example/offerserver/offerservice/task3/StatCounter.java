package com.example.offerserver.offerservice.task3;

import com.example.offerserver.offerservice.task1.Offer;
import com.example.offerserver.offerservice.task1.Stuff;
import com.example.offerserver.offerservice.task1.Client;

import java.util.*;

public class StatCounter {
    public static List<Offer> offers = new ArrayList<>();
    public static Map<Stuff, Integer> stuffOffers = new HashMap<>();
    private static Map<Client, Integer> clientOffers = new HashMap<>();
    private static Set<String> clientSurnames = new HashSet<>();
    private static StatCounter instance;

    private StatCounter(){};
    public static StatCounter getInstance(){
        if(instance == null){
            instance = new StatCounter();
        }
        return instance;
    }
    public List<Offer> getOffers() {
        return offers;
    }
    public void addOffer(Offer offer){
        offers.add(offer);
        Stuff offerStuff = offer.getOfferStuff();
        Client offerClient = offer.getOfferClient();

        if(stuffOffers.containsKey(offerStuff)){
            stuffOffers.put(offerStuff, stuffOffers.get(offerStuff) + 1);
        }
        else{
            stuffOffers.put(offer.getOfferStuff(), 1);
        }

        if(clientOffers.containsKey(offerClient)){
            clientOffers.put(offerClient, clientOffers.get(offerClient) + 1);
        }
        else{
            clientOffers.put(offer.getOfferClient(), 1);
        }
        clientSurnames.add(offer.getOfferClient().getSurname());

    }

    public Integer getStuffOffersCount(Stuff stuff){
        return stuffOffers.get(stuff);
    }
    public Integer getClientOffersCount(Client client){
        return clientOffers.get(client);
    }
    public boolean containsSurname(String surname){
        return clientSurnames.contains(surname);
    }

}
