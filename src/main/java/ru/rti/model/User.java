package ru.rti.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tUser")
@NamedEntityGraph(name = "userRoles",
	attributeNodes = @NamedAttributeNode("roles")
)
public class User extends EntityByLong {

	@Column(unique = true, nullable = false, length = 50)
	private String email;

	@Column(nullable = false)
	private String description;

	@JsonIgnore
	@Column(nullable = false, length = 60)
	private String password;

	@JsonIgnore
	private boolean enabled;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserRole> roles = new HashSet<UserRole>(0);

	public User() {
		
	}

	public User(long id) {
		setId(id);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return new StringBuilder("User[").append(getId()).append(", ")
				.append(email).append(", ")
				.append(description).append("]")
			.toString();
	}

}