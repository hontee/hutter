package com.hutter.master.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "title.policy")
public class TitlePolicy {

	/**
	 * 基本名称
	 */
	private String name;
	
	/**
	 * 首页
	 */
	private String home;
	
	/**
	 * 开发者推荐
	 */
	private String recommend;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

}
