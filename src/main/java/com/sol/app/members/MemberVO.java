package com.sol.app.members;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private Boolean enabled;
	private List<RoleVO> roles;
}
