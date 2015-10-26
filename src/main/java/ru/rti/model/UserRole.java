package ru.rti.model;

import javax.persistence.*;

@Entity
@Table(name = "tUserRole", 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"userId", "role"})
	})
public class UserRole extends EntityByLong {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column(nullable = false, length = 30)
	private String role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("Role[").append(getId()).append(", ")
				.append(user).append(", ")
				.append(role).append("]")
			.toString();
	}

}