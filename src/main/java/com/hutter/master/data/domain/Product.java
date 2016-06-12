package com.hutter.master.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

/**
 * 产品信息
 * @author larry.qi
 */
@Entity
@Table(name = "T_PRODUCT")
public class Product extends AuditorObject {

	private static final long serialVersionUID = 1L;

	@URL
	@NotNull
	@Length(max = 1024)
	private String url;
	
	@Column(columnDefinition="int default 0")
	private Integer hit;
	
	public Product() {
		super();
	}

	public Product(Long id) {
		super(id);
	}

	public Product(String title) {
		super(title);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

}
