package com.sol.app.members;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sol.app.validate.MemberAddGroup;
import com.sol.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User{
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}", groups = MemberAddGroup.class)
	@NotBlank(groups = MemberAddGroup.class)
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	
	@Past(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	
	private boolean enabled;
	
	private List<RoleVO> roles;
	
	//OauthUser
	//token 정보 저장
	private Map<String, Object> attributes;
	
	private String accessToken;
	
	private String sns;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO : roles) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(authority);
		}
		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		//return UserDetails.super.isAccountNonExpired();
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		//return UserDetails.super.isAccountNonLocked();
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		//return UserDetails.super.isCredentialsNonExpired();
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {

		return this.attributes;
	}
	
	
}

