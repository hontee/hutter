package com.hutter.master.data.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Length(min = 5, max = 16)
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Length(min = 6, max = 16)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
