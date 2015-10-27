package ru.rti.model;

import java.util.Date;

import javax.persistence.*;

import ru.rti.model.core.IdentifiedByLong;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", length = 3)
@Table(name = "tClient")
public abstract class Client extends IdentifiedByLong {

	public static final String PERSON = "4";
	public static final String BANK = "16";

	@Column(insertable = false, updatable = false)
	private String type;

	@Column(nullable = false)
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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