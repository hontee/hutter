package com.hutter.front.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hutter.front.site.interceptor.GlobalInterceptor;

@Configuration
public class ApplicationConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 配置全局拦截器
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
