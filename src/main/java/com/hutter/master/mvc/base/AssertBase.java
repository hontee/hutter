package com.hutter.master.mvc.base;

import org.springframework.validation.BindingResult;

import com.google.common.base.Preconditions;

/**
 * 断言验证前置请求
 * @author Administrator
 */
public abstract class AssertBase {
	
	public void checkAssert(BindingResult bindingResult) {
		Preconditions.checkArgument(!bindingResult.hasErrors(), "Request parameter error.");
	}
	
}
