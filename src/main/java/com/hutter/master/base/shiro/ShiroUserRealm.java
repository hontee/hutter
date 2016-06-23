package com.hutter.master.base.shiro;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户认证与授权
 * @author Administrator
 * @date 2016-06-14
 */
public class ShiroUserRealm extends AuthorizingRealm {
	
	Logger logger = LoggerFactory.getLogger(ShiroUserRealm.class);
	private ShiroUserAware shiroUserAware;
	
	public void setShiroUserAware(ShiroUserAware shiroUserAware) {
		this.shiroUserAware = shiroUserAware;
	}

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String principal = (String)principals.getPrimaryPrincipal();
		logger.info("为登录成功的用户：{}，添加角色和权限", principal);
	    SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
	    
	    Set<String> roles = shiroUserAware.getRoles();
	    if (CollectionUtils.isNotEmpty(roles)) {
	    	logger.info("设置用户角色：{}", roles.toString());
	    	authorization.setRoles(roles);
		}
	    
	    Set<String> permissions = shiroUserAware.getPermissions();
	    if (CollectionUtils.isNotEmpty(permissions)) {
	    	logger.info("设置用户权限：{}", permissions.toString());
	    	authorization.setStringPermissions(permissions);
		}
	    
		return authorization;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("用户身份认证: {}", token.getPrincipal());
		ShiroUser shiroUser = shiroUserAware.findUser((String) token.getPrincipal());
		return new SimpleAuthenticationInfo(shiroUser.getUsername(), shiroUser.getPassword(),
				ByteSource.Util.bytes(shiroUser.getSalt()), getName());
	}
	
}
