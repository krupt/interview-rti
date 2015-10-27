package ru.rti.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(Client.BANK)
@Table(name = "tClientBank")
public class ClientBank extends Client {

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false, length = 20)
	private String inn;

	@Column(nullable = false)
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}