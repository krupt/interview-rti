package ru.rti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ru.rti.model.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
}