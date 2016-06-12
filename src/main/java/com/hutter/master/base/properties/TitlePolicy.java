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
	 * 分隔符 · 
	 */
	private String divide;
	
	/**
	 * 首页
	 */
	private String home;
	
	/**
	 * 推荐新产品
	 */
	private String recommend;
	
	/**
	 * 推荐确认
	 */
	private String recommendConfirm;
	
	/**
	 * 推荐提交
	 */
	private String recommendSubmit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDivide() {
		return divide;
	}

	public void setDivide(String divide) {
		this.divide = divide;
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

	public String getRecommendConfirm() {
		return recommendConfirm;
	}

	public void setRecommendConfirm(String recommendConfirm) {
		this.recommendConfirm = recommendConfirm;
	}

	public String getRecommendSubmit() {
		return recommendSubmit;
	}

	public void setRecommendSubmit(String recommendSubmit) {
		this.recommendSubmit = recommendSubmit;
	}
	
}
