package com.hutter.master.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hutter.master.mvc.base.BaseController;

@Controller
@Scope("prototype")
public class OAuthController extends BaseController {

	Logger logger = LoggerFactory.getLogger(OAuthController.class);
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@CookieValue(required=false, name="rememberMe") String rememberMe) {
		logger.info("into login page, rememberMe={}", rememberMe);
		
		if (isAuthenticated()) {
			return "redirect:/";
		}
		
		return "oauth/login";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		if (isAuthenticated()) {
			getSubject().logout();
		}
		return "redirect:/";
	}
}
