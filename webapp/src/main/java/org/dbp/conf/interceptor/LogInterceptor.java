package org.dbp.conf.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor 
implements HandlerInterceptor{

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler) throws Exception {
		MDC.put("MarcaTiempo", ""+System.currentTimeMillis());//NOPMD
		return true;
	}

	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response,final Object handler,
			final ModelAndView modelAndView) throws Exception {
		MDC.remove("MarcaTiempo");
	}

	@Override
	public void afterCompletion(final HttpServletRequest request,
			final HttpServletResponse response,final Object handler,final Exception exception)
			throws Exception {
	}

}
