package com.sol.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info(exception.toString());
		log.info(exception.getLocalizedMessage());
		
		String msg = "로그인 실패";
		
		if(exception instanceof InternalAuthenticationServiceException) {
			msg = "아이디가 틀렸습니다.";
		} else if (exception instanceof BadCredentialsException) {
			msg = "비밀번호가 틀렸습니다.";
		} else if (exception instanceof DisabledException) {
			msg = "유효하지 않은 사용자입니다.";
		} else if (exception instanceof CredentialsExpiredException) {
			msg = "자격 증명 유효 기간이 만료되었습니다.";
		} else if (exception instanceof LockedException) {
			msg = "사용자 계정이 잠겨 있습니다.";
		} else if (exception instanceof AccountExpiredException) {
			msg = "사용자 계정의 유효 기간이 만료 되었습니다.";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("path", "/member/login");
		dispatcher.forward(request, response);
	}
	
}
