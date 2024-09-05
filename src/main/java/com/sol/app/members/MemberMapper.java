package com.sol.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	public Integer add(MemberVO memberVO) throws Exception;
	public MemberVO detail(MemberVO memberVO) throws Exception;
	public Integer addRole(Map<String, Object> map) throws Exception;
}
