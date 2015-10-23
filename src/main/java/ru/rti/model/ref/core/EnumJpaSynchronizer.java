package ru.rti.model.ref.core;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ReflectionUtils;

@Component
public class EnumJpaSynchronizer {

	public static final String FK_PREFIX = "_krupt_";

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final EntityManager entityManager;
	private final PlatformTransactionManager transactionManager;
	private final DataSource dataSource;
	
	private String basePackage = "ru.rti.model.ref";

	@Autowired
	public EnumJpaSynchronizer(EntityManager entityManager, PlatformTransactionManager transactionManager, DataSource dataSource) throws ClassNotFoundException {
		this.entityManager = entityManager;
		this.transactionManager = transactionManager;
		this.dataSource = dataSource;
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	private void doSynchronize() throws ClassNotFoundException {
		log.info("Synchronization started");
		ClassPathScanningCandidateComponentProvider enumScanner = new ClassPathScanningCandidateComponentProvider(false);
		enumScanner.addIncludeFilter(new AnnotationTypeFilter(MappedEnum.class));
		Set<BeanDefinition> candidates = enumScanner.findCandidateComponents(basePackage);
		int candidatesCount = candidates.size(),
			index = 1;
		for (BeanDefinition finded: candidates) {
			log.info(String.format("Processing (%d of %d)", index, candidatesCount));
			Class<? extends Reference> mappedClass = Reference.class;
			try {
				mappedClass = (Class<? extends Reference>) Class.forName(finded.getBeanClassName());
				log.info("Mapped " + mappedClass);
			} catch (ClassNotFoundException | ClassCastException e) {
				throw e;
			}
			Class<? extends Enum<?>> enumClass = mappedClass.getAnnotation(MappedEnum.class).value();
			log.info("Linked " + enumClass);
			createForeignKey(mappedClass, enumClass);
			insertEnumValues(mappedClass, enumClass);
			checkEnumIdentifiers(mappedClass, enumClass);
			log.info(String.format("(%d of %d) Mapped %s and Linked %s synchronized", index++, candidatesCount, mappedClass, enumClass));
		}
		log.info("Synchronization successfully completed");
	}

	private void insertEnumValues(Class<? extends Reference> mappedClass, Class<? extends Enum<?>> enumClass) {
		log.debug("Inserting enum values");
		for (Enum<?> constant: enumClass.getEnumConstants()) {
			try {
				Reference newInstance = mappedClass.newInstance();
				newInstance.setId(constant.ordinal());
				newInstance.setJavaCode(constant.toString());
				TransactionTemplate template = new TransactionTemplate(transactionManager);
				TransactionCallback<Object> callback = (status) -> {
					entityManager.persist(newInstance);
					return null;
				};
				template.execute(callback);
			} catch (Exception e) {
				/*
				 * JPA выведет ошибку в лог
				 */
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkEnumIdentifiers(Class<? extends Reference> mappedClass, Class<? extends Enum<?>> enumClass) {
		TypedQuery<? extends Reference> selectQuery = entityManager.createQuery("SELECT e FROM " + mappedClass.getSimpleName() + " e", mappedClass);
		List<? extends Reference> referenceList = selectQuery.getResultList();
		for (Reference reference: referenceList) {
			Enum<?> enumObject = Enum.valueOf((Class<? extends Enum>) enumClass, reference.getJavaCode());
			if (reference.getId() != enumObject.ordinal())
				throw new IllegalArgumentException(String.format("%s[%s] ordinal(%d) not equals %s", enumClass, enumObject, enumObject.ordinal(), reference));
		}
	}

	private void createForeignKey(Class<? extends Reference> mappedClass, Class<?> enumClass) {
		Class<?> enclosingClass = enumClass.getEnclosingClass();
		Field mappedField = ReflectionUtils.findField(enclosingClass, null, enumClass);
		Column columnAnnotation = AnnotationUtils.findAnnotation(mappedField, Column.class);
		String mappedColumnName = mappedField.getName();
		if (columnAnnotation != null)
			if (!columnAnnotation.name().equals(""))
				mappedColumnName = columnAnnotation.name();
		StringBuilder sql = new StringBuilder("ALTER TABLE ");
		sql.append(AnnotationUtils.getAnnotation(enclosingClass, Table.class).name())
			.append("\n  ADD CONSTRAINT \"fk")
			.append(FK_PREFIX)
			.append(mappedClass.getSimpleName())
			.append("\" FOREIGN KEY (")
			.append(mappedColumnName)
			.append(")\n  REFERENCES ")
			.append(AnnotationUtils.getAnnotation(mappedClass, Table.class).name())
			.append("(id)");
		log.info("Creating foreign key:\n" + sql);
		try {
			new JdbcTemplate(dataSource).execute(sql.toString());
		} catch (Exception e) {
			log.info(e.getCause().getMessage());
		}
	}

}