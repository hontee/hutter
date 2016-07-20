package com.hutter.front.site.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.hutter.front.core.security.ShiroCredentialsMatcher;
import com.hutter.front.core.security.ShiroUserRealm;
import com.hutter.front.core.service.UserService;
import com.hutter.front.core.service.impl.UserServiceImpl;

@Configuration
public class ShiroAutoConfiguration {
	
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		registration.addInitParameter("targetFilterLifecycle", "true");
		registration.addUrlPatterns("/*");
		registration.setEnabled(true);
		return registration;
	}

	@Bean
	public ShiroCredentialsMatcher credentialsMatcher() {
		return new ShiroCredentialsMatcher();
	}
	
	/**
	 * 须指定Bean的名称，和{@link UserServiceImpl}保持一致，防止出现多个同类型的Bean。
	 */
	@Bean(name = "userServiceImpl")
	public UserService userServiceImpl() {
		return new UserServiceImpl();
	}
	
	@Bean
	public ShiroUserRealm userRealm() {
		ShiroUserRealm realm = new ShiroUserRealm(userServiceImpl());
		realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactory() {
		ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
		shiroFilterFactory.setSecurityManager(securityManager());
		shiroFilterFactory.setLoginUrl("/login");
		shiroFilterFactory.setFilterChainDefinitions("/cms/**=authc");
		return shiroFilterFactory;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		return securityManager;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor advisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}

}
