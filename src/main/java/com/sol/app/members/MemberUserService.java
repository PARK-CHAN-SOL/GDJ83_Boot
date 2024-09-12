package com.sol.app.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberUserService extends DefaultOAuth2UserService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberMapper.detail(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (memberVO == null) {
			throw new InternalAuthenticationServiceException("존재하지 않는 사용자입니다");
		}

		return memberVO;
	}

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		// return super.loadUser(userRequest);
		log.error("userRequest_getAccessToken: {}", userRequest.getAccessToken());
		ClientRegistration reg = userRequest.getClientRegistration();
		log.error("ClientRegistration_ClientId: {}", reg.getClientId());

		String sns = reg.getRegistrationId();
		OAuth2User auth2User = super.loadUser(userRequest);
		if (sns.equals("kakao")) {
			auth2User = this.useKakao(userRequest);
		} else if (sns.equals("naver")) {

		}

		return auth2User;
	}

	private OAuth2User useKakao(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User auth2User = super.loadUser(userRequest);
		
		log.error("==============================================");
		log.error("ID: {}", auth2User.getName());
		log.error("Attributes: {}", auth2User.getAttributes());
		log.error("Authorities: {}", auth2User.getAuthorities());
		
		MemberVO memberVO = new MemberVO();
		
		Map<String, Object> attributes = auth2User.getAttributes();
		Map<String, Object> properties = (Map<String, Object>)attributes.get("properties");
		
		memberVO.setUsername(auth2User.getName() + "_" + properties.get("nickname"));
		
		List<RoleVO> roles = new ArrayList<>();
		
		RoleVO role = new RoleVO();
		role.setRoleName("ROLE_USER");
		
		roles.add(role);
		
		memberVO.setRoles(roles);
		
		memberVO.setSns(userRequest.getClientRegistration().getRegistrationId());
		
		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		
		memberVO.setAttributes(attributes);
		
		return memberVO;
	}

}
