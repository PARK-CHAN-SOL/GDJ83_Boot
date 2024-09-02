package com.sol.app.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest() throws Exception {
		List<NoticeVO> list = noticeMapper.getList();
		for(NoticeVO noticeVO : list) {
			System.out.println(noticeVO);
		}
		assertEquals(list.size(), 100);
	}
	
	/*
	 * @Test void addTest() throws Exception { for(Long i = 3L; i <= 100L; i++) {
	 * NoticeVO noticeVO = new NoticeVO(); noticeVO.setBoard_num(i);
	 * noticeVO.setBoard_writer("w"+i); noticeVO.setBoard_title("t"+i);
	 * noticeVO.setBoard_contents("c"+i); noticeDAO.add(noticeVO); } List<NoticeVO>
	 * list = noticeDAO.getList(); assertEquals(list.size(), 100); }
	 */

}
