package com.hutter.master.mvc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.form.UserForm;
import com.hutter.master.mvc.base.BaseRest;
import com.hutter.master.mvc.base.Response;
import com.hutter.master.service.UserService;

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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestParam String username, @RequestParam String password) {
		logger.info("username login: {}", username);
		Response body = new Response();
		
		try {
			userS.login(username, password);
			body.setSuccess(true);
			body.setMessage("登录成功");
			body.setResult("/");
		} catch (BaseException e) {
			body.setMessage(e.getErrorCode().getValue());
			logger.error("User [{}] login failed：{}", username, e.getMessage());
			e.printStackTrace();
		}
		
		return buildResponse(body);
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Response> logout() {
		Response body = new Response();
		super.getSubject().logout();
		body.setMessage("已退出登录");
		body.setSuccess(true);
		return buildResponse(body);
	}
	
}
