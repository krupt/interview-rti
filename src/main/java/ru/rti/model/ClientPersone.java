package ru.rti.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Persone")
@Table(name = "tClientPersone")
public class ClientPersone extends Client {

	@Column(nullable = false, length = 100)
	private String fio;

	private char sex;

	@Column(nullable = false, length = 50)
	private String translit;

	
}