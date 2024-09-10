package com.sol.app.members;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * @Test void addTest() throws Exception { MemberVO memberVO = new MemberVO();
	 * memberVO.setUsername("manager");
	 * memberVO.setPassword(passwordEncoder.encode("manager")); Integer result =
	 * memberMapper.pwUpdate(memberVO); assertEquals(1, result); }
	 */
	
	/*
	 * @Test void encodeTest() throws Exception { for(int i = 0; i < 10000; i++) {
	 * log.info(passwordEncoder.encode("admin")); } }
	 */

}
