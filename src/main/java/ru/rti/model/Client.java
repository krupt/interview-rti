package ru.rti.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 10)
@Table(name = "tClient")
public abstract class Client extends EntityByLong {

	private Date created;

	@PrePersist
	public void onCreate() {
		setCreated(new Date());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}