package ru.ryazhev.task1;

import java.time.LocalDateTime;

public interface OfferService {
    public Offer signNewOffer(LocalDateTime startDate,
                              LocalDateTime endingDate,
                              Client client,
                              Stuff stuff);
}
