package com.hutter.front.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.hutter.front.site.base.BaseController;

@Controller
@Scope("prototype")
public class OAuthController extends BaseController {

	Logger logger = LoggerFactory.getLogger(OAuthController.class);
	
	@GetMapping("login")
	public String login(@CookieValue(required=false, name="rememberMe") String rememberMe) {
		logger.info("into login page, rememberMe={}", rememberMe);
		
		if (isAuthenticated()) {
			return "redirect:/";
		}
		
		return "oauth/login";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		if (isAuthenticated()) {
			getSubject().logout();
		}
		return "redirect:/";
	}
}
