package ru.rti.model;

import org.junit.Assert;
import org.junit.Test;

public class EntityByLongTest {

	@Test
	public void testEntityEquals() {
		User user = new User(1);
		user.setEmail("123@mail.com");
		Message message = new Message();
		message.setId(1);
		message.setTopic("Привет");
		Assert.assertFalse(user.equals(message));
		Assert.assertFalse(message.equals(user));
	}

}