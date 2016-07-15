package com.hutter.front.site.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hutter.front.site.annotation.Token;
import com.hutter.front.toolkit.http.HttpUtil;

public class GlobalInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (annotation != null) {
				if (annotation.add()) {
					request.getSession().setAttribute("token", UUID.randomUUID().toString());
				}
				
				if (annotation.delete()) {
					if (isRequestSubmit(request)) {
						logger.warn("Please don't repeat submit: {}", HttpUtil.getRequestPath(request));
						response.sendRedirect("/");
						return false;
					}
					
					request.getSession().removeAttribute("token");
				}
			}
			return true;
		}
		
		return preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	/**
	 * 是否为请求提交
	 * @param request
	 * @return
	 */
	private boolean isRequestSubmit(HttpServletRequest request) {
		String serverToken = (String)request.getSession().getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (clientToken == null) {
			return true;
		}
		if (!serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}

}
