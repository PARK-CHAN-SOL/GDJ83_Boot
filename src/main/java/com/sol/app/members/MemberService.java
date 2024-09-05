package com.sol.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Transactional(rollbackFor = Exception.class)
	public Integer add(MemberVO memberVO) throws Exception {
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
