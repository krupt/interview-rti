package ru.rti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rti.model.Card;
import ru.rti.model.pk.CardPrimaryKey;
import ru.rti.repository.CardRepository;
import ru.rti.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;

	@Autowired
	public CardServiceImpl(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public Iterable<Card> findAll(Sort sort) {
		return cardRepository.findAll(sort);
	}

	public Page<Card> findAll(Pageable pageable) {
		return cardRepository.findAll(pageable);
	}

	public <S extends Card> S save(S entity) {
		return cardRepository.save(entity);
	}

	public <S extends Card> Iterable<S> save(Iterable<S> entities) {
		return cardRepository.save(entities);
	}

	public Card findOne(CardPrimaryKey id) {
		return cardRepository.findOne(id);
	}

	public boolean exists(CardPrimaryKey id) {
		return cardRepository.exists(id);
	}

	public Iterable<Card> findAll() {
		return cardRepository.findAll();
	}

	public Iterable<Card> findAll(Iterable<CardPrimaryKey> ids) {
		return cardRepository.findAll(ids);
	}

	
	public long count() {
		return cardRepository.count();
	}

	
	public void delete(CardPrimaryKey id) {
		cardRepository.delete(id);
	}

	
	public void delete(Card entity) {
		cardRepository.delete(entity);
	}

	
	public void delete(Iterable<? extends Card> entities) {
		cardRepository.delete(entities);
	}

	
	public void deleteAll() {
		cardRepository.deleteAll();
	}

}