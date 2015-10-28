package ru.rti.service;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.rti.model.ClientPerson;
import ru.rti.util.sql.SQLExceptionHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ClientServiceTest {

	@Autowired
	ClientService clientService;
	
	@Autowired
	CardService cardService;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void lazyLoadTest() {
		/*for (String beanName: applicationContext.getBeanDefinitionNames())
			System.out.println("***** " + beanName + " = " + applicationContext.getBean(beanName));*/
		ClientPerson person1 = new ClientPerson();
		person1.setBirthday(new GregorianCalendar(1989, 1, 25).getTime());
		person1.setFio("КОВАЛЕВ АНРДЕЙ ЮРЬЕВИЧ");
		person1.setPassport("6509654185");
		person1.setTranslit("KOVALEV ANDREY");
		person1.setSex('М');
		try {
			clientService.save(person1);
		} catch (DataIntegrityViolationException e) {
			/*if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				SQLErrorCodeSQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator();
				DataAccessException translate = translator.translate("", "", sqlEx);
				System.out.println("***** " + translate);
			}*/
			System.out.println("***** " + SQLExceptionHandler.getMessage(e));
		}
		/*Card newCard = new Card();
		newCard.setId(new CardPrimaryKey("4116240112468762"));
		Client client1 = new ClientPerson();
		client1.setId(1);
		newCard.setClient(client1);
		newCard.setExpDate(new GregorianCalendar(2018, 9, 31).getTime());
		cardService.save(newCard);*/
		/*for (Card card: cardService.findAll())
			System.out.println(card);*/
		/*for (Client client: clientService.findAll())
			System.out.println(client);*/
	}

}