package com.hutter.front.site.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

	/**
	 * Add Token
	 * @return
	 */
	boolean add() default false;
	
	/**
	 * Delete Token
	 * @return
	 */
	boolean delete() default false;
}
