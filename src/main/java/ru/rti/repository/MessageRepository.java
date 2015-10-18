package ru.rti.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;

import ru.rti.model.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

	/*
	 * Переопределение стандартного метода для получения всех данных одним запросом
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@EntityGraph(value = "users", type = EntityGraphType.FETCH)
	Iterable<Message> findAll();

}