package com.hutter.front.core.security;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 1. 限定用户登录次数<br>
 * 2. 自定义密码匹配规则<br>
 * 
 * @author larry.qi
 */
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;

	public ShiroCredentialsMatcher() {
		passwordRetryCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(60, TimeUnit.MINUTES).build();
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authToken, AuthenticationInfo authInfo) {
		// 从AuthenticationToken中获取用户登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String username = (String) token.getPrincipal();
		String password = String.valueOf(token.getPassword());

		// 限定登录次数
		AtomicInteger retryCount = passwordRetryCache.getIfPresent(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}

		if (retryCount.incrementAndGet() > 5) {
			throw new ExcessiveAttemptsException("登录次数过多");
		}

		// 密码匹配
		SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authInfo;
		ByteSource salt = info.getCredentialsSalt(); // SHIRO登录校验时设置的salt值
		Object dbPass = getCredentials(info); // 数据库中存储的密码
		String encryptPass = EncryptHelper.encrypt(password, salt); // 加密处理后的密码
		boolean matches = super.equals(dbPass, encryptPass);
		if (matches) {
			passwordRetryCache.invalidate(username);
		}

		return matches;
	}

}
