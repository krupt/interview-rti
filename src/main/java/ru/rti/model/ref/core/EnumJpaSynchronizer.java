package ru.rti.model.ref.core;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	@PostConstruct
	@SuppressWarnings("unchecked")
	private void doSynchronize() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		log.info("Synchronization started");
		ClassPathScanningCandidateComponentProvider enumScanner = new ClassPathScanningCandidateComponentProvider(false);
		enumScanner.addIncludeFilter(new AnnotationTypeFilter(MappedEnum.class));
		Set<BeanDefinition> candidates = enumScanner.findCandidateComponents(basePackage);
		int candidatesCount = candidates.size(),
			index = 1;
		for (BeanDefinition finded: candidates) {
			log.info("Processing (" + index + " of " + candidatesCount + ")");
			Class<? extends Reference> mappedClass = Reference.class;
			try {
				mappedClass = (Class<? extends Reference>) Class.forName(finded.getBeanClassName());
				log.info("Mapped " + mappedClass);
			} catch (ClassNotFoundException | ClassCastException e) {
				throw e;
			}
			Class<? extends Enum<?>> enumClass = mappedClass.getAnnotation(MappedEnum.class).value();
			log.info("Founded linked " + enumClass);
			printEnumValues(enumClass);
			createForeignKey(mappedClass, enumClass);
			insertEnumValues(mappedClass, enumClass);
			fillEnumIdentifiers(mappedClass, enumClass);
			printEnumValues(enumClass);
			log.info("(" + index++ + " of " + candidatesCount + ") Linked " + enumClass + " synchronized");
		}
		log.info("Synchronization successfully completed");
	}

	private void printEnumValues(Class<? extends Enum<?>> enumClass) {
		StringBuilder values = new StringBuilder("\nList values of ")
				.append(enumClass).append(":");
			for (Enum<?> enumValue: enumClass.getEnumConstants())
				values.append("\n").append(enumValue)
					.append(" is ").append(enumValue.ordinal());
			log.debug(values.toString());
	}

	private void insertEnumValues(Class<? extends Reference> mappedClass, Class<? extends Enum<?>> enumClass) {
		log.debug("Inserting enum values");
		for (Enum<?> constant: enumClass.getEnumConstants()) {
			try {
				TypedQuery<? extends Reference> selectQuery = entityManager.createQuery("SELECT e FROM " + mappedClass.getSimpleName() + " e WHERE javaCode = ?1", mappedClass);
				selectQuery.setParameter(1, constant.toString());
				try {
					selectQuery.getSingleResult();
					continue;
				} catch (NoResultException e) {
					/*
					 * Нужно добавить значение
					 */
				}
				Reference newInstance = mappedClass.newInstance();
				newInstance.setJavaCode(constant.toString());
				TransactionTemplate template = new TransactionTemplate(transactionManager);
				TransactionCallback<Object> callback = (status) -> {
					entityManager.persist(newInstance);
					return null;
				};
				template.execute(callback);
			} catch (Exception e) {
				log.warn(e.getMessage());
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillEnumIdentifiers(Class<? extends Reference> mappedClass, Class<? extends Enum<?>> enumClass) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		TypedQuery<? extends Reference> selectQuery = entityManager.createQuery("SELECT e FROM " + mappedClass.getSimpleName() + " e", mappedClass);
		List<? extends Reference> referenceList = selectQuery.getResultList();
		for (Reference reference: referenceList)
			setEnumOrdinal(Enum.valueOf((Class<? extends Enum>) enumClass, reference.getJavaCode()), reference.getId());
		Object valuesArray = Array.newInstance(enumClass, referenceList.size());
		int index = 0;
        for (Enum enumValue : enumClass.getEnumConstants())
            Array.set(valuesArray, index++, enumValue);
        Field field = ReflectionUtils.findField(enumClass, "ENUM$VALUES");
        ReflectionUtils.makeAccessible(field);
        Field modifiersField = ReflectionUtils.findField(Field.class, "modifiers");
        ReflectionUtils.makeAccessible(modifiersField);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, valuesArray);
	}

	private void setEnumOrdinal(Enum<?> enumObject, int ordinal) throws IllegalArgumentException, IllegalAccessException {
		Field field = ReflectionUtils.findField(enumObject.getClass(), "ordinal");
		ReflectionUtils.makeAccessible(field);
		field.set(enumObject, ordinal);
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