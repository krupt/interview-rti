package ru.rti.model;

import org.junit.Assert;
import org.junit.Test;

public class IdentifiedByLongTest {

	@Test
	public void testEntityEquals() {
		User user = new User(1);
		user.setEmail("123@mail.com");
		User user2 = new User(2);
		User user1 = new User(1);
		user1.setEmail("sadsadasd");
		Message message = new Message();
		message.setId(1);
		message.setTopic("Привет");
		Assert.assertFalse(user.equals(message));
		Assert.assertFalse(message.equals(user));
		Assert.assertFalse(user.equals(user2));
		Assert.assertTrue(user.equals(user1));
	}

}