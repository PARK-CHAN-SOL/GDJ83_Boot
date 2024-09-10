package com.sol.app.members;

import java.sql.Date;
import java.util.List;

import com.sol.app.validate.MemberAddGroup;
import com.sol.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVO {
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
	
	private Boolean enabled;
	
	private List<RoleVO> roles;
}
