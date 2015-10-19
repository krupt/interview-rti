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

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public long count() {
		return userRepository.count();
	}

	public void delete(Long id) {
		userRepository.delete(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public void delete(Iterable<? extends User> users) {
		userRepository.delete(users);
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}

	public boolean exists(Long id) {
		return userRepository.exists(id);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public Iterable<User> findAll(Iterable<Long> ids) {
		return userRepository.findAll(ids);
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public <S extends User> Iterable<S> save(Iterable<S> entities) {
		return userRepository.save(entities);
	}

	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	public User findByEmailIgnoreCase(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	public Iterable<User> findByIdNot(Long id) {
		return userRepository.findByIdNot(id);
	}

}