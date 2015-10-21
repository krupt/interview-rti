package ru.rti.model.ref.core;

import javax.persistence.*;

@MappedSuperclass
public class Reference {

	public static final String PREFIX = "tReference";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, length = 32)
	private String javaCode;

	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJavaCode() {
		return javaCode;
	}

	public void setJavaCode(String javaCode) {
		this.javaCode = javaCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}