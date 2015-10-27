package ru.rti.model;

import java.util.Date;

import javax.persistence.*;

import ru.rti.model.pk.CardPrimaryKey;

@Entity
@Table(name = "tCard")
public class Card {

	@EmbeddedId
	private CardPrimaryKey id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientId", nullable = false)
	private Client client;

	@Column(nullable = false, updatable = false)
	private Date created;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date expDate;

	@Column(length = 10)
	private String pinOffset;

	@PrePersist
	public void onCreate() {
		setCreated(new Date());
	}

	public String getPan() {
		return getId().getPan();
	}

	public byte getMbr() {
		return getId().getMbr();
	}

	public CardPrimaryKey getId() {
		return id;
	}

	public void setId(CardPrimaryKey id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getPinOffset() {
		return pinOffset;
	}

	public void setPinOffset(String pinOffset) {
		this.pinOffset = pinOffset;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Card)
			return id.equals(((Card)obj).id);
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder("Card[")
				.append(id).append(", ")
				.append(expDate).append(", ")
				.append(client).append("]")
			.toString();
	}

}