package com.hutter.front.site.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.hutter.front.site.freemarker.BlockDirective;
import com.hutter.front.site.freemarker.ExtendsDirective;
import com.hutter.front.site.freemarker.OverrideDirective;
import com.hutter.front.site.freemarker.SuperDirective;

import freemarker.template.utility.XmlEscape;

@Configuration
public class FreemarkerAutoConfiguration {
	
	@Autowired
	private FreeMarkerProperties props;
	
	@Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setOrder(1);
        resolver.setCache(props.isCache());
        resolver.setSuffix(props.getSuffix());
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths(props.getTemplateLoaderPath());
		configurer.setDefaultEncoding(props.getCharsetName());
		
		Properties settings = new Properties();
		settings.setProperty("locale", "zh_CN");
		settings.setProperty("url_escaping_charset", "0");
		configurer.setFreemarkerSettings(settings);
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("xml_escape", xmlEscape());
		variables.put("extends", extendsDirective());
		variables.put("override", overrideDirective());
		variables.put("block", blockDirective());
		variables.put("super", superDirective());
		configurer.setFreemarkerVariables(variables);
		return configurer;
	}
	
	@Bean
	public XmlEscape xmlEscape() {
		return new XmlEscape();
	}
	
	@Bean
	public BlockDirective blockDirective() {
		return new BlockDirective();
	}
	
	@Bean
	public ExtendsDirective extendsDirective() {
		return new ExtendsDirective();
	}
	
	@Bean
	public OverrideDirective overrideDirective() {
		return new OverrideDirective();
	}
	
	@Bean
	public SuperDirective superDirective() {
		return new SuperDirective();
	}

}
