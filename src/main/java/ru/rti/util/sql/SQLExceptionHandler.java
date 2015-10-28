package ru.rti.util.sql;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Component;

@Component
public class SQLExceptionHandler {

	public static final String DEFAULT_MESSAGE = "Непредвиденная ошибка. Пожалуйста, обратитесь к аднимистратору для уточнения причины";

	private static SQLExceptionTranslator sqlExceptionTranslator;
	private static Logger log = LoggerFactory.getLogger(SQLExceptionHandler.class);

	@Autowired
	public SQLExceptionHandler(SQLExceptionTranslator sqlExceptionTranslator) {
		SQLExceptionHandler.sqlExceptionTranslator = sqlExceptionTranslator;
	}

	public static String getMessage(NestedRuntimeException exception) {
		StringBuilder message = new StringBuilder();
		if (exception.getRootCause() != null && exception.getRootCause() instanceof SQLException) {
			SQLException sqlException = (SQLException) exception.getRootCause();
			message.append(sqlExceptionTranslator.translate("", "", sqlException).getMessage());
			int lastIndex = message.lastIndexOf("Detail: ");
			message.delete(0, lastIndex + 8);
		} else {
			message.append(DEFAULT_MESSAGE);
			log.error("", exception);
		}
		return message.toString();
	}

}