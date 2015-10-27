package ru.rti.model;

import java.util.Date;

import javax.persistence.*;

import ru.rti.model.core.IdentifiedByLong;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 10)
@Table(name = "tClient")
public abstract class Client extends IdentifiedByLong {

	private Date created;

	private Date updated;

	@PrePersist
	public void onCreate() {
		setCreated(new Date());
	}

	@PreUpdate
	public void onUpdate() {
		setUpdated(new Date());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}