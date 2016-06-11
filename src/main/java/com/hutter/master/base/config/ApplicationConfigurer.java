package com.hutter.master.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hutter.master.mvc.interceptor.GlobalInterceptor;

@Configuration
public class ApplicationConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 配置全局拦截器
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
