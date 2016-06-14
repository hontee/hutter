package com.hutter.master.base.shiro;

import java.util.Set;

/**
 * 需要注入的用户数据
 * @author Administrator
 * @date 2016-06-14
 */
public interface ShiroUserAware {
	
	ShiroUser findUser(String username);
	
	Set<String> getRoles();
	
	Set<String> getPermissions();

}
