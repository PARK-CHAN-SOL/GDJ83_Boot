package com.sol.app.configs.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sol.app.members.MemberVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SecurityLogoutSucessHandler implements LogoutSuccessHandler{
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//	https://kapi.kakao.com/v1/user/logout
		// https://kauth.kakao.com/oauth/logout
		log.error(authentication.toString());
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		if(memberVO.getAccessToken() != null && memberVO.getSns().equals("kakao")) {
//			WebClient webClient = WebClient
//									.builder()
//									.baseUrl("https://kapi.kakao.com/v1/user/logout")
//									.build();
//			Mono<Map> id = webClient
//									.post()
//									.header("Authorization", "Bearer " + memberVO.getAccessToken())
//									.retrieve()
//									.bodyToMono(Map.class);
//			
//			log.error(id.block().get("id").toString());
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<MultiValueMap<String, Object>> req = new HttpEntity<MultiValueMap<String,Object>>(headers);
			headers.add("Authorization", "Bearer " + memberVO.getAccessToken());
			Map<String, Long> res = restTemplate.postForObject("https://kapi.kakao.com/v1/user/logout", req, Map.class);
			log.error(res.get("id").toString());
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		request.setAttribute("msg", "로그아웃 성공");
		request.setAttribute("path", "/");
		dispatcher.forward(request, response);
	}
}
