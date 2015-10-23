package ru.rti.model.ref.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.rti.model.Message;
import ru.rti.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class EnumJpaSynchronizerTest {

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	MessageService messageService;

	@Test
	public void testBeanScan() {
		for (String beanName: applicationContext.getBeanDefinitionNames())
			Assert.assertTrue(!beanName.toLowerCase().contains("messagestatus"));
		for (Message message: messageService.findAll()) {
			System.out.println(message);
		}
	}

}