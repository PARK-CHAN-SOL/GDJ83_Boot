package com.sol.app.home.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sol.app.members.MemberVO;
import com.sol.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		
		if(memberVO == null) {
			response.sendRedirect("/");
			return false;
		}
		
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_ADMIN");
		roleVO.setRoleNum(3L);
		if(memberVO.getRoles().contains(roleVO)) {
			return true;
		} else {
			request.setAttribute("msg", "관리자 권한이 필요합니다");
			request.setAttribute("path", "/");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
			view.forward(request, response);
			return false;
		}
		
	}
}
