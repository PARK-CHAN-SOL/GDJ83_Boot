package com.sol.app.aops.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class StartTest {
	 
	@Autowired 
	private Start start;

	@Test
	void goTest() {
		start.go();
	}

}
