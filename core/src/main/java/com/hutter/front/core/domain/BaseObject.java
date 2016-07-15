package com.hutter.front.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 对象实体基类，封装了7个通用属性<br>
 * 添加{@link @MappedSuperclass}注解，让子类可以使用<br>
 * 添加{@link @EntityListeners}注解，设置{@link AuditorAware<T>}监听<br>
 * 
 * <li>id: 对象ID</li>
 * <li>name: 名称，唯一键，为后期实现基于名称的URL访问做铺垫</li>
 * <li>title: 标题，显示名称</li>
 * <li>description: 描述内容</li>
 * <li>state: 状态</li>
 * <li>created: 创建时间，自动添加{@link @CreatedDate}</li>
 * <li>lastModified: 更新时间，自动添加 {@link @LastModifiedDate}</li>
 * 
 * @author larry.qi
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Length(max = 64)
	@Column(unique = true)
	private String name;

	@NotNull
	@Length(max = 128)
	private String title;

	@Length(max = 1024)
	private String description;
	
	@NotNull
	@Column(columnDefinition = "tinyint default 1")
	private Byte state;
	
	@NotNull
	@CreatedDate
	private Date created;
	
	@NotNull
	@LastModifiedDate
	@Column(name = "last_modified")
	private Date lastModified;
	
	public BaseObject(String title) {
		this.title = title;
	}

	public BaseObject(Long id) {
		this.id = id;
	}

	public BaseObject() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * 生成UUID值
	 * @return
	 */
	public String randomUUID() {
		return UUID.randomUUID().toString();
	}

}
