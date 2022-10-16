package ru.ryazhev.task1;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {
	public static void main(String[] args) {
		OfferService service = LeasingOfferService.getInstance();
		Client client = new Client();
		client.setId(UUID.randomUUID());
		client.setName("Name");
		client.setSex(true);
		// ... fill client
		Stuff stuff = new Stuff();
		stuff.setId(UUID.randomUUID());
		stuff.setName("StuffName");
		stuff.setSex(false);
		// ... fill stuff
		service.signNewOffer(LocalDateTime.now(), LocalDateTime.now().plusYears(1L), client, stuff);
	}
}
