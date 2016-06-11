package com.hutter.master.data.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public class ProductForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@URL
	@Length(max = 1024)
	private String url;
	
	@NotNull
	@Length(max = 128)
	private String title;
	
	@NotNull
	@Length(max = 1024)
	private String description;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
