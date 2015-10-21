package ru.rti.model.ref.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class EnumJpaSynchronizerTest {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testBeanScan() {
		for (String beanName: applicationContext.getBeanDefinitionNames())
			Assert.assertTrue(!beanName.toLowerCase().contains("messagestatus"));
	}

}