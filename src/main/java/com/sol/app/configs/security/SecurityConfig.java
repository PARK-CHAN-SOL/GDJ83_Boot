package com.sol.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
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
						.requestMatchers("/member/add", "/member/login").permitAll()
						.requestMatchers("/member/**").authenticated()
						.anyRequest().permitAll();
				}
			) //authorizeHttpRequests End
		
		//form login 관련 설정
			.formLogin(
				(login) -> {
					login
						.loginPage("/member/login")
						.defaultSuccessUrl("/")
						.failureUrl("/member/login")
						//아이디에 해당하는 파라미터 이름이 username이 아니라면 해당 이름으로 바꿔야함
						//.usernameParameter("id")
						//패스워드에 해당하는 파라미터 이름이 password가 아니라면 해당 이름으로 바꿔야함
						//.passwordParameter("pw")
						.permitAll();
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
