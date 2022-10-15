package ru.ryazhev.task1;
import java.time.LocalDateTime;
import java.util.UUID;

public class Offer {
    public Offer() {}

    public Offer(UUID id,
                 String serialNumber,
                 LocalDateTime signDate,
                 LocalDateTime endingDate,
                 LocalDateTime startingDate,
                 Office offerOffice,
                 Client offerClient,
                 Stuff offerStuff) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.signDate = signDate;
        this.endingDate = endingDate;
        this.startingDate = startingDate;
        this.offerOffice = offerOffice;
        this.offerClient = offerClient;
        this.offerStuff = offerStuff;
    }

    private UUID id;
    private String serialNumber;
    private LocalDateTime signDate;
    private LocalDateTime endingDate;
    private LocalDateTime startingDate;
    private Office offerOffice;
    private Client offerClient;
    private Stuff offerStuff;

    public Office getOfferOffice() {
        return offerOffice;
    }

    public void setOfferOffice(Office offerOffice) {
        this.offerOffice = offerOffice;
    }

    public Stuff getOfferStuff() {
        return offerStuff;
    }

    public void setOfferStuff(Stuff offerStuff) {
        this.offerStuff = offerStuff;
    }

    public Client getOfferClient() {
        return offerClient;
    }

    public void setOfferClient(Client offerClient) {
        this.offerClient = offerClient;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDateTime signDate) {
        this.signDate = signDate;
    }

    public LocalDateTime getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDateTime endingDate) {
        this.endingDate = endingDate;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }
}