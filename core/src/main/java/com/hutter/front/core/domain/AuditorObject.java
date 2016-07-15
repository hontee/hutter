package com.hutter.front.core.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

/**
 * 授权实体基类，基于{@link User}类，通过JPA提供的{@link AuditorAware<T>}接口实现自动注入。
 * <li>添加注解{@link @CreatedBy} 和 {@link @LastModifiedBy}自动注入授权用户实体</li>
 * <li>添加注解{@link @ManyToOne}自动查询授权用户实体</li>
 * @author larry.qi
 */
@MappedSuperclass
public abstract class AuditorObject extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "creator")
	private User creator;

	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name = "editor")
	private User editor;
	
	public AuditorObject() {
		super();
	}

	public AuditorObject(Long id) {
		super(id);
	}

	public AuditorObject(String title) {
		super(title);
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

}
