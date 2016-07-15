package com.hutter.front.core.service;

import com.hutter.front.core.domain.User;
import com.hutter.front.core.form.SettingsForm;
import com.hutter.front.core.form.UserForm;

public interface UserService {

	/**
	 * 添加用户
	 * @return
	 * @throws BaseException
	 */
	User addUser(UserForm form);
	
	/**
	 * 查询单个用户
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	User findOne(Long id);
	
	/**
	 * 根据用户名或邮箱查询
	 * @param nameOrEmail
	 * @return
	 */
	User findOne(String nameOrEmail);
	
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @throws BaseException
	 */
	void login(String name, String password);
	
	/**
	 * 用户设置
	 * @param id
	 * @param form
	 * @throws BaseException
	 */
	User settings(Long id, SettingsForm form);
	
}
