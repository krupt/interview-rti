package ru.rti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommonAppTest {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testBeanScan() {
		for (String beanName: applicationContext.getBeanDefinitionNames()) {
			System.out.println(beanName + " is " + applicationContext.getBean(beanName).getClass());
		}
	}

}