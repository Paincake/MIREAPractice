package ru.ryazhev.task3;

import ru.ryazhev.task1.Client;
import ru.ryazhev.task1.Offer;
import ru.ryazhev.task1.Stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatCounter {
    private static StatCounter instance;

    private final List<Offer> offers = new ArrayList<>();
    private final Map<Stuff, Integer> stuffOffers = new HashMap<>();
    private final Map<Client, Integer> clientOffers = new HashMap<>();
    private final Set<String> clientSurnames = new HashSet<>();

    // If you want singleton pattern -> add private constructor
    private StatCounter(){

    }

    public static StatCounter getInstance(){
        if (instance == null) {
            instance = new StatCounter();
        }
        return instance;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer){
        offers.add(offer); // If offers were created with emptyList() -> offers.add() will produce UnsupportedOperationException because
        // emptyList() create IMMUTABLE (you cannot change (mutate)) list (the same rule applies for emptySet() and emptyMap())
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
