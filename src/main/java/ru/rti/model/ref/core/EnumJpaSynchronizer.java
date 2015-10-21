package ru.rti.model.ref.core;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

@Component
@Transactional
public class EnumJpaSynchronizer {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final EntityManager entityManager;
	private String basePackage = "ru.rti.model.ref";

	@Autowired
	public EnumJpaSynchronizer(EntityManager entityManager) throws ClassNotFoundException {
		this.entityManager = entityManager;
		doSynchronize();
	}

	@SuppressWarnings("unchecked")
	private void doSynchronize() throws ClassNotFoundException {
		log.info("Synchronization started");
		ClassPathScanningCandidateComponentProvider enumScanner = new ClassPathScanningCandidateComponentProvider(false);
		enumScanner.addIncludeFilter(new AnnotationTypeFilter(MappedEnum.class));
		for (BeanDefinition finded: enumScanner.findCandidateComponents(basePackage)) {
			Class<? extends Reference> mappedClass = Reference.class;
			try {
				mappedClass = (Class<? extends Reference>) Class.forName(finded.getBeanClassName());
				log.info("Processing mapped[" + mappedClass + "]");
			} catch (ClassNotFoundException | ClassCastException e) {
				throw e;
			}
			Class<? extends Enum<?>> enumClass = mappedClass.getAnnotation(MappedEnum.class).value();
			log.debug("Founded linked enum[" + enumClass + "]");
			checkForeignKey(mappedClass, enumClass);
			TypedQuery<? extends Reference> selectQuery = entityManager.createQuery("SELECT e FROM " + mappedClass.getSimpleName() + " e", mappedClass);
			List<? extends Reference> resultList = selectQuery.getResultList();
			log.debug(resultList.toString());
			log.info("Mapped[" + mappedClass + "] processed");
		}
		log.info("Synchronization successfully completed");
	}

	private void checkForeignKey(Class<? extends Reference> mappedClass, Class<? extends Enum<?>> enumClass) {
		Class<?> enclosingClass = enumClass.getEnclosingClass();
		log.debug("Enclosed by: " + enclosingClass);
		Field mappedField = ReflectionUtils.findField(enclosingClass, null, enumClass);
		log.debug("Column: " + mappedField);
		Column columnAnnotation = AnnotationUtils.findAnnotation(mappedField, Column.class);
		if (columnAnnotation != null) {
			log.debug(columnAnnotation.name());
		}
	}

}