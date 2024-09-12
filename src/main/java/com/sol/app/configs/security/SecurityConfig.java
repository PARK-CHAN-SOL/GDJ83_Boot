package com.sol.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sol.app.members.MemberUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler securityLoginSuccessHandler;
	
	@Autowired
	private SecurityLoginFailureHandler securityLoginFailureHandler;
	
	@Autowired
	private SecurityLogoutSucessHandler securityLogoutSucessHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//Security 에서 무시할 것들
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**")
						;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security
			.cors()
			.and()
			.csrf()
			.disable();

		//권한에 관련된 설정
		security
			.authorizeHttpRequests(
				(authorizeRequest)->{
					authorizeRequest
						.requestMatchers("/").permitAll()
						.requestMatchers("/qna/list").permitAll()
						.requestMatchers("/qna/**").authenticated()
						.requestMatchers("/notice/list", "/notice/detail").permitAll()
						.requestMatchers("/notice/**").hasRole("ADMIN")
						.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
						.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
						.requestMatchers("/member/**").authenticated()
						.anyRequest().permitAll();
				}
			) //authorizeHttpRequests End
		
		//form login 관련 설정
			.formLogin(
				(login) -> {
					login
						//개발자가 만든 로그인 페이지 사용
						.loginPage("/member/login")
						//.defaultSuccessUrl("/")
						.successHandler(securityLoginSuccessHandler)
						.failureHandler(securityLoginFailureHandler)
						//아이디에 해당하는 파라미터 이름이 username이 아니라면 해당 이름으로 바꿔야함
						//.usernameParameter("id")
						//패스워드에 해당하는 파라미터 이름이 password가 아니라면 해당 이름으로 바꿔야함
						//.passwordParameter("pw")
						.permitAll()
						;
				}
			) //form login 관련 설정
			
			//logout 관련 설정
			.logout(
				(logout)->{
					logout.logoutUrl("/member/logout") // 로그아웃 URL 지정 방법 1
					.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))// 로그아웃 URL 지정 방법 2
					.logoutSuccessHandler(securityLogoutSucessHandler)
					.invalidateHttpSession(true)//true: session 만료, false: session 만료 X
					//.deleteCookies(null)
					;
				}
			)
			
			.rememberMe(
				(remember)->{
					remember
						// Parameter 이름
						.rememberMeParameter("rememberMe")
						// token 유효시간
						.tokenValiditySeconds(60)
						// token에 사용되는 값 (필수, 개발자가 값 설정)
						.key("rememberMe")
						// 인증절차(로그인)진행할 UserDetailService 객체
						.userDetailsService(memberUserService)
						.authenticationSuccessHandler(securityLoginSuccessHandler)
						.useSecureCookie(false)
						;
				}
			)
			
			.sessionManagement(
				(sessionManager)->{
					sessionManager
						//최대 허용 개수, -1이면 무한대
						.maximumSessions(1)
						//false: 이전 사용자 만료, true: 현재 사용자 로그인 제한
						.maxSessionsPreventsLogin(false)
						//session이 만료되었을 경우 redirect할 URL
						.expiredUrl("/member/check")
						;
				}
			)
			
			.oauth2Login(
				(oauth2)->{
					oauth2.userInfoEndpoint(
						(userInfo)->{
							userInfo.userService(memberUserService);
						}
					);
				}
			)
		;
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
