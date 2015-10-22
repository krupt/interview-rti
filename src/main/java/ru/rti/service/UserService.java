package ru.rti.service;

import org.springframework.data.repository.NoRepositoryBean;

import ru.rti.repository.UserRepository;
import ru.rti.service.util.CurrentUser;

@NoRepositoryBean
public interface UserService extends UserRepository {

	CurrentUser getCurrentUser();

}