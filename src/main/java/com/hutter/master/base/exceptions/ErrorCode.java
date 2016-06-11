package com.hutter.master.base.exceptions;

public enum ErrorCode {

	// OAUTH
	OAUTH_UNAUTHORIZED("没有授权"),
	OAUTH_USERNAME_EXISTS("用户名已注册"),
	OAUTH_USERNAME_NOT_EXISTS("用户名不存在"),
	OAUTH_EMAIL_EXISTS("邮箱已存在"),
	OAUTH_EMAIL_NOT_EXISTS("邮箱不存在"),
	OAUTH_USERNAME_PASSWORD_ERROR("用户名或密码错误"),
	OAUTH_EXCESSIVE_ATTEMPTS("登录过于频繁"),
	
	// 
	NOT_FOUND("对象不存在"),
	
	// Recommend
	RECOMMEND_URL_EXISTS("推荐的网址已存在"),
	
	// PRODUCT
	PRODUCT_URL_EXISTS("添加的网址已存在"),
	PRODUCT_NOT_EXISTS("产品不存在"),
	;
	
	private String name;
	
	private ErrorCode(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
