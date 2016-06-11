package com.hutter.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.UserForm;
import com.hutter.master.data.repository.UserRepository;
import com.hutter.master.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userR;

	@Override
	public User addUser(UserForm form) throws BaseException {
		
		User entity = new User();
		entity.setIsEmailSet((byte)0);
		entity.setState((byte)1);
		entity.setType((byte)1);
		entity.setSalt(entity.randomSalt());
		entity.setDescription("");
		
		entity.setName(form.getName());
		entity.setEmail(form.getEmail());
		entity.setPassword(form.getPassword());
		entity.setTitle(entity.getName());
		return userR.saveAndFlush(entity);
	}

	@Override
	public User findOne(Long id) throws BaseException {
		return userR.findOne(id);
	}

	@Override
	public void login(String name, String password) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
