package com.hutter.front.core.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class SettingsForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Length(max = 16)
	private String title;
	
	@NotNull
	@Email
	private String email;
	
	@Length(max = 1024)
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
}
