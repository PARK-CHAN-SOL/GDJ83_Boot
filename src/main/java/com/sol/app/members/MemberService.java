package com.sol.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean memberValidate(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check = false;
		
		//0. 기본 검증값 (Annotation 검증의 결과값)
		check = bindingResult.hasErrors();
		
		//1. password 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			//에러메세지
			//bindingResult.rejectValue(
			bindingResult.rejectValue("passwordCheck", "memberVO.pw.notEqual");
		}
		
		//2. ID 중복 검사
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
			check = true;
			bindingResult.rejectValue("username", "memberVO.id.exist");
		}
		return check;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Integer add(MemberVO memberVO) throws Exception {
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		Integer result = memberMapper.add(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 1);
		
		result = memberMapper.addRole(map);
		
		if(result==0) {
			throw new Exception();
		}
		
		return result;
	}
	
	public MemberVO detail(MemberVO memberVO) throws Exception {
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())) {
				return result;
			}
		}
		return null;
	}
	
}
