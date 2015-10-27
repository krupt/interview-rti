package ru.rti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rti.model.Client;
import ru.rti.repository.ClientRepository;
import ru.rti.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Iterable<Client> findAll(Sort sort) {
		return clientRepository.findAll(sort);
	}

	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	
	public <S extends Client> S save(S entity) {
		return clientRepository.save(entity);
	}

	
	public <S extends Client> Iterable<S> save(Iterable<S> entities) {
		return clientRepository.save(entities);
	}

	public Client findOne(Long id) {
		return clientRepository.findOne(id);
	}

	public boolean exists(Long id) {
		return clientRepository.exists(id);
	}

	public Iterable<Client> findAll() {
		return clientRepository.findAll();
	}

	public Iterable<Client> findAll(Iterable<Long> ids) {
		return clientRepository.findAll(ids);
	}

	public long count() {
		return clientRepository.count();
	}

	public void delete(Long id) {
		clientRepository.delete(id);
	}

	public void delete(Client entity) {
		clientRepository.delete(entity);
	}

	
	public void delete(Iterable<? extends Client> entities) {
		clientRepository.delete(entities);
	}

	
	public void deleteAll() {
		clientRepository.deleteAll();
	}

}