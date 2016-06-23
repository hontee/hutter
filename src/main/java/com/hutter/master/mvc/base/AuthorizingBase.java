package com.hutter.master.mvc.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hutter.master.data.domain.User;

/**
 * 用户授权信息
 * @author Administrator
 */
public abstract class AuthorizingBase {

	/**
	 * 获取登录对象
	 * @return
	 */
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * Session
	 * @return
	 */
	public Session getSession() {
		return getSubject().getSession();
	}
	
	/**
	 * flush Session
	 * @param user
	 */
	public void flushSession(User user) {
		getSubject().getSession(true).setAttribute("oauth", user);
	}

	/**
	 * CurrentUser
	 * @return
	 */
	public User getCurrentUser() {
		
		if (!isAuthenticated()) {
			throw new AuthenticationException();
		}
		
		return (User)getSubject().getSession().getAttribute("oauth");
	}

	/**
	 * 用户ID
	 * @return
	 */
	public Long getUserId() {
		return getCurrentUser().getId();
	}

	/**
	 * Principal
	 * @return
	 */
	public String getUserName() {
		return getCurrentUser().getName();
	}
	
	public String getEmail() {
		return getCurrentUser().getEmail();
	}
	
	/**
	 * 是否登录
	 * @return
	 */
	public boolean isAuthenticated() {
		return getSubject().isAuthenticated();
	}
	
	/**
	 * 邮箱是否验证
	 * @return
	 */
	public boolean isEmailSet() {
		return getCurrentUser().getIsEmailSet() == 1;
	}

	/**
	 * 是否为管理员
	 * @return
	 */
	public boolean isAdmin() {
		return getCurrentUser().getType() == 2;
	}
}
