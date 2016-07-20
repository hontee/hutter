package com.hutter.front.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.base.Preconditions;
import com.hutter.front.core.domain.User;
import com.hutter.front.core.form.SettingsForm;
import com.hutter.front.core.form.UserForm;
import com.hutter.front.core.repository.UserRepository;
import com.hutter.front.core.security.EncryptHelper;
import com.hutter.front.core.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userR;

	@Override
	public User addUser(UserForm form) {
		Assert.notNull(form);
		Assert.notNull(userR.findByName(form.getName()));
		Assert.notNull(userR.findByEmail(form.getEmail()));
		
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
	public User findOne(Long id) {
		return userR.findOne(id);
	}
	
	@Override
	public User findOne(String nameOrEmail){
		Preconditions.checkNotNull(nameOrEmail, "nameOrEmail is null.");
		
		if (nameOrEmail.contains("@")) {
			return userR.findByEmail(nameOrEmail);
		}
		
		return userR.findByName(nameOrEmail);
	}

	@Override
	public void login(String name, String password) {
		Preconditions.checkNotNull(name, "username is null.");
		Preconditions.checkNotNull(password, "password is null.");
		
		User sign = null;
		if (name.contains("@")) {
			sign = userR.findByEmail(name);
		} else {
			sign = userR.findByName(name);
		}
		
		Assert.notNull(sign);
		UsernamePasswordToken token = new UsernamePasswordToken(sign.getName(), password, true);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		Session session = subject.getSession();
		session.setAttribute("oauth", sign);
	}

	@Override
	public User settings(Long id, SettingsForm form) {
		Assert.notNull(form);
		User record = userR.findByEmail(form.getEmail());

		Assert.notNull(record);
		Assert.isTrue(id.equals(record.getId()));

		record = userR.findOne(id);
		record.setTitle(form.getTitle());
		record.setEmail(form.getEmail());
		record.setDescription(form.getDescription());
		return userR.save(record);
	}

	@Override
	public Set<String> getRoles() {
		Set<String> roles = new HashSet<>();
		roles.add("user");
		roles.add("admin");
		return roles;
	}

	@Override
	public Set<String> getPermissions() {
		Set<String> perms = new HashSet<>();
		perms.add("all");
		return perms;
	}

}
