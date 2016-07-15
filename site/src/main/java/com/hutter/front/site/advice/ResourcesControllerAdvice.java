package com.hutter.front.site.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@ControllerAdvice
public class ResourcesControllerAdvice {
	
	@Autowired
	private ResourceUrlProvider provider;
	
	@ModelAttribute("urls")
	public ResourceUrlProvider urls() {
		return this.provider;
	}

}
