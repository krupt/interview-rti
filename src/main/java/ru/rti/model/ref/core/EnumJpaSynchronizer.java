package ru.rti.model.ref.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EnumJpaSynchronizer {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private String basePackage = "ru.rti.model.ref";

	public EnumJpaSynchronizer() {
		
	}

}