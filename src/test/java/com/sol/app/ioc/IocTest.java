package com.sol.app.ioc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sol.app.notice.NoticeVO;
import com.sol.app.robot.Robot;

@SpringBootTest
class IocTest {

	@Autowired
	private Robot robot;
	
	@Test
	void test() {
		System.out.println("Test Case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
	}

}
