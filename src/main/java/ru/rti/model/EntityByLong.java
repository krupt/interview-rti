package ru.rti.model;

import javax.persistence.*;

@MappedSuperclass
public class EntityByLong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass().equals(obj.getClass()))
			return id == ((EntityByLong) obj).id;
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
				.append("[").append(id).append("]")
			.toString();
	}

}