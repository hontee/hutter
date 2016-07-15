package com.hutter.front.site.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hutter.front.core.form.UserForm;
import com.hutter.front.core.service.UserService;
import com.hutter.front.site.base.BaseRest;
import com.hutter.front.site.base.Response;

/**
 * 用户认证与授权
 * @author Administrator
 * @date 2016-06-14
 */
@RestController
@Scope("prototype")
@RequestMapping("oauth")
public class OAuthRest extends BaseRest {
	
	Logger logger = LoggerFactory.getLogger(OAuthRest.class);
	
	@Autowired
	private UserService userS;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@Validated UserForm form, BindingResult bindingResult) {
		Response body = new Response();
		try {
			checkAssert(bindingResult);
			userS.addUser(form);
			body.setSuccess(true);
			body.setMessage("注册成功");
			body.setResult("/");
		} catch (Exception e) {
			body.setMessage("请求参数错误");
			logger.error("reigster failed. {}", e.getMessage());
			e.printStackTrace();
		}
		
		return buildResponse(body);
	}
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam String username, @RequestParam String password) {
		logger.info("username login: {}", username);
		Response body = new Response();
		
		try {
			userS.login(username, password);
			body.setSuccess(true);
			body.setMessage("登录成功");
			body.setResult("/");
		} catch (Exception e) {
			body.setMessage("失败");
			logger.error("User [{}] login failed：{}", username, e.getMessage());
			e.printStackTrace();
		}
		
		return buildResponse(body);
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@PostMapping("/logout")
	public ResponseEntity<Response> logout() {
		Response body = new Response();
		super.getSubject().logout();
		body.setMessage("已退出登录");
		body.setSuccess(true);
		return buildResponse(body);
	}
	
}
