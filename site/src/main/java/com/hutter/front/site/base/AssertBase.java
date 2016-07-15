package com.hutter.front.site.base;

import org.springframework.validation.BindingResult;

import com.google.common.base.Preconditions;

/**
 * 断言验证前置请求
 * @author Administrator
 */
public abstract class AssertBase extends AuthorizingBase {
	
	/**
	 * Spring MVC数据验证
	 * @param bindingResult
	 */
	public void checkAssert(BindingResult bindingResult) {
		Preconditions.checkArgument(!bindingResult.hasErrors(), "Request parameter error.");
	}
	
}
