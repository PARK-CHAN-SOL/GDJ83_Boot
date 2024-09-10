package com.sol.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.sol.app.home.interceptors.AdminCheckInterceptor;
import com.sol.app.home.interceptors.LoginInterceptor;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		 * registry.addInterceptor(loginInterceptor) .addPathPatterns("/qna/**")
		 * .excludePathPatterns("/qna/list");
		 */
		
		
		/*
		 * registry.addInterceptor(adminCheckInterceptor) .addPathPatterns("/qna/add",
		 * "/admin/**");
		 */
		 
		
		registry.addInterceptor(localeChangeInterceptor)
		.addPathPatterns("/**");
	}
}
