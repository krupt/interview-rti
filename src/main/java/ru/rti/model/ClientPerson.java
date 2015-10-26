package ru.rti.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Person")
@Table(name = "tClientPerson")
public class ClientPerson extends Client {

	@Column(nullable = false, length = 100)
	private String fio;

	@Column(unique = true, nullable = false, length = 50)
	private String passport;

	private char sex;

	@Column(nullable = false, length = 50)
	private String translit;

	private Date birthday;

	@Column(length = 50)
	private String email;

	@Column(length = 20)
	private String phone;

	@Column(length = 20)
	private String mobilePhone;

	private String secretQuestion;

	private String secretAnswer;

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getTranslit() {
		return translit;
	}

	public void setTranslit(String translit) {
		this.translit = translit;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

}