package com.hutter.master.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.hutter.master.base.shiro.ShiroUser;
import com.hutter.master.base.shiro.ShiroUserAware;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.repository.UserRepository;

@Service
public class ShiroUserAwareImpl implements ShiroUserAware {

	@Autowired
	private UserRepository userR;
	
	@Override
	public ShiroUser findUser(String username) {
		Preconditions.checkNotNull(username, "username is null.");
		User sign = new User();
		
		if (username.contains("@")) {
			sign = userR.findByEmail(username);
		} else {
			sign = userR.findByName(username);
		}
		
		ShiroUser su = new ShiroUser();
		su.setId(sign.getId());
		su.setPassword(sign.getPassword());
		su.setSalt(sign.getSalt());
		su.setType(sign.getType());
		su.setUsername(sign.getName());
		return su;
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
