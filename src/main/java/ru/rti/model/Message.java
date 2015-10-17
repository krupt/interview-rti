package ru.rti.model;

import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "tMessage")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender", nullable = false)
	private User sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recipient", nullable = false)
	private User recipient;

	@Column
	private String topic;

	@Column(length = 4000, nullable = false)
	private String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Message)
			return this.id == ((Message) obj).id;
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder("Message[")
				.append(id).append(", ")
				.append(sender).append(", ")
				.append(recipient).append(", ")
				.append(topic).append(", ")
				.append(message).append("]")
			.toString();
	}

}