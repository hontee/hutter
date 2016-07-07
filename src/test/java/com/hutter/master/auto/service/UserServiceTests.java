package com.hutter.master.auto.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hutter.master.ApplicationTests;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.User;
import com.hutter.master.service.UserService;

/**
 * 自动化测试：用户服务
 * @author Administrator
 *
 */
public class UserServiceTests extends ApplicationTests {

	@Autowired
	private UserService userS;
	
	@Test
	public void findOne() throws BaseException {
		logger.info("### 查询单个用户");
		User record = userS.findOne(1L);
		Assert.assertNotNull(record);
	}
}
