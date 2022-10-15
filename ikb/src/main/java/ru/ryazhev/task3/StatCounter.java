package ru.ryazhev.task3;

import ru.ryazhev.task1.Client;
import ru.ryazhev.task1.Offer;
import ru.ryazhev.task1.Stuff;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatCounter {
    private List<Offer> offers = Collections.emptyList();
    private Map<Stuff, Integer> stuffOffers = Collections.emptyMap();
    private Map<Client, Integer> clientOffers = Collections.emptyMap();
    private Set<String> clientSurnames = Collections.emptySet();
    private static StatCounter instance = new StatCounter();
    public static StatCounter getInstance(){
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
