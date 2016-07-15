package com.hutter.front.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutter.front.core.domain.User;
import com.hutter.front.core.service.UserService;
import com.hutter.front.shiro.ShiroUser;
import com.hutter.front.shiro.ShiroUserAware;

@Service
public class ShiroUserAwareImpl implements ShiroUserAware {

	@Autowired
	private UserService userS;
	
	@Override
	public ShiroUser findUser(String username) {
		ShiroUser su = new ShiroUser();
		User sign = (User)getUser(username);
		su.setId(sign.getId());
		su.setPassword(sign.getPassword());
		su.setSalt(sign.getSalt());
		su.setType(sign.getType());
		su.setUsername(sign.getName());
		return su;
	}

	@Override
	public Object getUser(String username) {
		return userS.findOne(username);
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
