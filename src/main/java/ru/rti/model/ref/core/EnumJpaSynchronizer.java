package ru.rti.model.ref.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
			log.debug("Found linked[" + enumClass + "]");
			compareInterfaces(mappedClass, enumClass);
			TypedQuery<? extends Reference> selectQuery = entityManager.createQuery("SELECT e FROM " + mappedClass.getSimpleName() + " e", mappedClass);
			List<? extends Reference> resultList = selectQuery.getResultList();
			log.debug(resultList.toString());
			log.info("Mapped[" + mappedClass + "] processed");
		}
		log.info("Synchronization successfully completed");
	}

	private void compareInterfaces(Class<?> mappedClass, Class<? extends Enum<?>> linkedEnum) {
		HashSet<Class<?>> mappedInterfaces = new HashSet<Class<?>>();
		mappedInterfaces.addAll(Arrays.asList(mappedClass.getInterfaces()));
		mappedInterfaces.addAll(Arrays.asList(mappedClass.getSuperclass().getInterfaces()));
		HashSet<Class<?>> linkedInterfaces = new HashSet<Class<?>>(Arrays.asList(linkedEnum.getInterfaces()));
		log.debug("Mapped interfaces " + mappedInterfaces);
		log.debug("Linked interfaces " + linkedInterfaces);
		if (!mappedInterfaces.equals(linkedInterfaces))
			throw new IllegalArgumentException("Interfaces of " + mappedClass + mappedInterfaces + " not applicable with " + linkedEnum + linkedInterfaces);
	}

}