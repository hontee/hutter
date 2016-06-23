package com.hutter.master.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutter.master.base.shiro.ShiroUser;
import com.hutter.master.base.shiro.ShiroUserAware;
import com.hutter.master.data.domain.User;
import com.hutter.master.service.UserService;

@Service
public class ShiroUserAwareImpl implements ShiroUserAware {

	@Autowired
	private UserService userS;
	
	@Override
	public ShiroUser findUser(String username) {
		ShiroUser su = new ShiroUser();
		try {
			User sign = (User)getUser(username);
			su.setId(sign.getId());
			su.setPassword(sign.getPassword());
			su.setSalt(sign.getSalt());
			su.setType(sign.getType());
			su.setUsername(sign.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
