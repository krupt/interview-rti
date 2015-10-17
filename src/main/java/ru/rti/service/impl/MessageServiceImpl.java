package ru.rti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rti.model.Message;
import ru.rti.repository.MessageRepository;
import ru.rti.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;

	@Autowired
	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Iterable<Message> findAll(Sort sort) {
		return messageRepository.findAll(sort);
	}

	public Page<Message> findAll(Pageable pageable) {
		return messageRepository.findAll(pageable);
	}

	public <S extends Message> S save(S entity) {
		return messageRepository.save(entity);
	}

	public <S extends Message> Iterable<S> save(Iterable<S> entities) {
		return messageRepository.save(entities);
	}

	public Message findOne(Long id) {
		return messageRepository.findOne(id);
	}

	public boolean exists(Long id) {
		return messageRepository.exists(id);
	}

	public Iterable<Message> findAll() {
		return messageRepository.findAll();
	}

	public Iterable<Message> findAll(Iterable<Long> ids) {
		return messageRepository.findAll(ids);
	}

	public long count() {
		return messageRepository.count();
	}

	public void delete(Long id) {
		messageRepository.delete(id);
	}

	public void delete(Message entity) {
		messageRepository.delete(entity);
	}

	public void delete(Iterable<? extends Message> entities) {
		messageRepository.delete(entities);
	}

	public void deleteAll() {
		messageRepository.deleteAll();
	}

}