package ru.rti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ru.rti.model.Card;
import ru.rti.model.pk.CardPrimaryKey;

public interface CardRepository extends PagingAndSortingRepository<Card, CardPrimaryKey> {
/*
 * 
 */
}