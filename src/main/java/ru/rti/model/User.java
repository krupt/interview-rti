package ru.rti.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tUser")
@NamedEntityGraph(name = "userRoles",
	attributeNodes = @NamedAttributeNode("roles")
)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

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
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> roles = new HashSet<UserRole>(0);

	public User() {
		
	}

	public User(long id) {
		setId(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	public int hashCode() {
		return (int) id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof User)
			return this.id == ((User) obj).id;
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder("User[").append(id).append(", ")
				.append(email).append(", ")
				.append(description).append("]")
			.toString();
	}

}