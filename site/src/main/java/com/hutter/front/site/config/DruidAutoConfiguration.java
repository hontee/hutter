package com.hutter.front.site.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.hutter.front.site.properties.DruidProperties;

/**
 * DruidDataSource Auto Configuration
 * @author larry.qi
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableJpaRepositories(
	basePackages = "com.hutter.front.core.repository", 
	entityManagerFactoryRef = "entityManagerFactory", 
	transactionManagerRef = "transactionManager",
	repositoryImplementationPostfix = "Impl",
	queryLookupStrategy = Key.CREATE_IF_NOT_FOUND,
	includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class) },
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
	})
public class DruidAutoConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(DruidAutoConfiguration.class);
	
	@Autowired
	private DruidProperties druidP;
	
	@Bean(name = "druidDataSource")
	public DataSource druidDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(druidP.getUrl());
		dataSource.setUsername(druidP.getUsername());
		dataSource.setPassword(druidP.getPassword());
		dataSource.setMaxActive(druidP.getMaxActive());
		dataSource.setInitialSize(druidP.getInitialSize());
		dataSource.setMaxWait(druidP.getMaxWait());
		dataSource.setMinIdle(druidP.getMinIdle());
		dataSource.setTimeBetweenEvictionRunsMillis(druidP.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(druidP.getMinEvictableIdleTimeMillis());
		
		dataSource.setTestWhileIdle(druidP.isTestWhileIdle());
		dataSource.setTestOnBorrow(druidP.isTestOnBorrow());
		dataSource.setTestOnReturn(druidP.isTestOnReturn());
		dataSource.setPoolPreparedStatements(druidP.isPoolPreparedStatements());
		dataSource.setMaxOpenPreparedStatements(druidP.getMaxOpenPreparedStatements());
		
		try {
			dataSource.setFilters(druidP.getFilters());
		} catch (SQLException e) {
			logger.warn("Filters Settings Error: {}", e.getMessage());
			e.printStackTrace();
		}
		return dataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("com.hutter.front.core.domain");
		factory.setDataSource(druidDataSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
	
	@Bean
	public ServletRegistrationBean druidServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean();
		registration.setServlet(new StatViewServlet());
		registration.addUrlMappings("/druid/*");
		return registration;
	}

}
