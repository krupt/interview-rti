package ru.rti.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import ru.rti.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@EntityGraph(value = "userRoles", type = EntityGraphType.FETCH)
	User findByEmail(String email);

}