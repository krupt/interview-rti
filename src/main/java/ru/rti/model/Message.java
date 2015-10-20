package ru.rti.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tMessage",
	indexes = {@Index(columnList = "recipient"),
		@Index(columnList = "sender")
	}
)
@NamedEntityGraph(name = "users",
	attributeNodes = {@NamedAttributeNode("sender"),
		@NamedAttributeNode("recipient")
	}
)
public class Message {

	public enum Status {
		NEW,
		READED;
	}

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

	@Column(nullable = false, updatable = false)
	private Date created;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Status status;

	@PrePersist
	public void onCreate() {
		setCreated(new Date());
		setStatus(Status.NEW);
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
				.append(message).append(", ")
				.append(created).append(", ")
				.append(status)
			.append("]").toString();
	}

}