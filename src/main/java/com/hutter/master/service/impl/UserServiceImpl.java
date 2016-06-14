package com.hutter.master.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.base.exceptions.ErrorCode;
import com.hutter.master.base.shiro.EncryptHelper;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.UserForm;
import com.hutter.master.data.repository.UserRepository;
import com.hutter.master.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userR;

	@Override
	public User addUser(UserForm form) throws BaseException {
		Preconditions.checkNotNull(form, "User form is null.");
		
		if (userR.findByName(form.getName()) != null) {
			logger.error("username exists.");
			throw new BaseException(ErrorCode.OAUTH_USERNAME_EXISTS);
		}
		
		if (userR.findByEmail(form.getEmail()) != null) {
			logger.error("email exists.");
			throw new BaseException(ErrorCode.OAUTH_EMAIL_EXISTS);
		}
		
		User entity = new User();
		entity.setIsEmailSet((byte)0);
		entity.setState((byte)1);
		entity.setType((byte)1);
		entity.setSalt(entity.randomSalt());
		entity.setDescription("");
		
		// 设置密码，用户名和邮箱
		String password = EncryptHelper.encrypt(form.getPassword(), entity.getSalt());
		entity.setPassword(password);
		entity.setName(form.getName());
		entity.setEmail(form.getEmail());
		entity.setTitle(entity.getName());
		return userR.saveAndFlush(entity);
	}

	@Override
	public User findOne(Long id) throws BaseException {
		return userR.findOne(id);
	}

	@Override
	public void login(String name, String password) throws BaseException {
		Preconditions.checkNotNull(name, "username is null.");
		Preconditions.checkNotNull(password, "password is null.");
		
		User sign = null;
		if (name.contains("@")) {
			sign = userR.findByEmail(name);
		} else {
			sign = userR.findByName(name);
		}
		
		if (sign == null) {
			logger.warn("username or email is not found. {}", name);
			throw new BaseException(ErrorCode.OAUTH_USERNAME_PASSWORD_ERROR);
		}

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(sign.getName(), password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("oauth", sign);
		} catch (Exception e) {
			logger.error("User [{}] login failed: {}", name, Throwables.getStackTraceAsString(e));
			throw new BaseException(ErrorCode.OAUTH_USERNAME_PASSWORD_ERROR, e);
		} 
	}

}
