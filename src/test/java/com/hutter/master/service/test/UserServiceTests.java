package com.hutter.master.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hutter.master.HutterApplicationTests;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.UserForm;
import com.hutter.master.service.UserService;

public class UserServiceTests extends HutterApplicationTests {

	@Autowired
	private UserService userS;
	
	@Test
	public void addUser() throws BaseException {
		UserForm form = new UserForm();
		form.setEmail("admin@hutter.com");
		form.setName("admin");
		form.setPassword("123456");
		User record = userS.addUser(form);
		System.out.println(record.getTitle());
	}
	
	@Test
	public void findOne() throws BaseException {
		User record = userS.findOne(1L);
		System.out.println(record.getTitle());
	}
}
