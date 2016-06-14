package com.hutter.master.service;

import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.UserForm;

public interface UserService {

	/**
	 * 添加用户
	 * @return
	 * @throws BaseException
	 */
	User addUser(UserForm form) throws BaseException;
	
	/**
	 * 查询单个用户
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	User findOne(Long id) throws BaseException;
	
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @throws BaseException
	 */
	void login(String name, String password) throws BaseException;
	
}
