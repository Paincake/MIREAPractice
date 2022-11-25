package ru.ryazhev.task3;

import ru.ryazhev.task1.Offer;
import ru.ryazhev.task1.Stuff;

import java.util.List;

public class OfferLookup {
    public static Offer bySeriesLookup(List<Offer> offers, String number){
        for(Offer i : offers){
            if(i.getSerialNumber().equals(number)) return i;
        }
        return null;
    }
    public static Offer bySurnameLookup(List<Offer> offers, String surname){
        for(Offer i : offers){
            if(i.getOfferClient().getSurname().equals(surname)) return i;
        }
        return null;
    }
    // byClientLookup but you send worker as search parameter? WAT?
    public static Offer byClientLookup(List<Offer> offers, Stuff worker){
        for(Offer i : offers){
            if (i.getOfferStuff().equals(worker)) return i;
            // If you want check two custom objects, You need override equals method in custom class
            // Open Stuff.java file for example
            // Same rules applies for Client.javaa and Offer.java
        }
        return null;
    }

}
