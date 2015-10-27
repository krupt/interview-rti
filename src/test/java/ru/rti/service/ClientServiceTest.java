package ru.rti.service;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.rti.model.Card;
import ru.rti.model.Client;
import ru.rti.model.ClientBank;
import ru.rti.model.ClientPerson;
import ru.rti.model.pk.CardPrimaryKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ClientServiceTest {

	@Autowired
	ClientService clientService;
	
	@Autowired
	CardService cardService;

	@Test
	public void lazyLoadTest() {
		/*Card newCard = new Card();
		newCard.setId(new CardPrimaryKey("4116240112468715"));
		Client client1 = new ClientPerson();
		client1.setId(1);
		newCard.setClient(client1);
		newCard.setExpDate(new GregorianCalendar(2018, 9, 31).getTime());
		cardService.save(newCard);*/
		for (Card card: cardService.findAll())
			System.out.println(card);
		/*for (Client client: clientService.findAll())
			System.out.println(client);*/
	}

}