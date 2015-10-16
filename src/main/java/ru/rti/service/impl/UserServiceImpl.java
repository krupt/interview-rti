package ru.rti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rti.model.User;
import ru.rti.repository.UserRepository;
import ru.rti.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		repository = userRepository;
	}

	public long count() {
		return repository.count();
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public void delete(User user) {
		repository.delete(user);
	}

	public void delete(Iterable<? extends User> users) {
		repository.delete(users);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public boolean exists(Long id) {
		return repository.exists(id);
	}

	public Iterable<User> findAll() {
		return repository.findAll();
	}

	public Iterable<User> findAll(Iterable<Long> ids) {
		return repository.findAll(ids);
	}

	public User findOne(Long id) {
		return repository.findOne(id);
	}

	public <S extends User> Iterable<S> save(Iterable<S> entities) {
		return repository.save(entities);
	}

	public <S extends User> S save(S entity) {
		return repository.save(entity);
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

}