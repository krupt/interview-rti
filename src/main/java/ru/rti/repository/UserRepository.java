package ru.rti.repository;

import org.springframework.data.repository.CrudRepository;

import ru.rti.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}