package com.hy.oauth2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LDZ   
 * @date 2015年11月2日 上午10:15:33 
 */
public class Interceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object curuser =  request.getSession().getAttribute("user");
		Object token =  request.getSession().getAttribute("_token");
		if("login".equals(request.getRequestURI())){
			return true;
		}
		
		if(curuser != null||token!=null){
			return true;
		}
		
		throw new RuntimeException("请先登陆!");
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
