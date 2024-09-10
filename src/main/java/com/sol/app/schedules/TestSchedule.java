package com.sol.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sol.app.qna.QnaMapper;
import com.sol.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	//@Scheduled(fixedDelay = 1000L, initialDelay = 2000L)
	public void test1() throws Exception {
		log.error("Schedule Test");
	}
	
	//@Scheduled(fixedRate = 2000L, initialDelay = 5000L)
	public void test2() throws Exception {
		log.error("========= Schedule Test =========");
	}
	
	//@Scheduled(cron = "*/5 * * * * * ")
	public void test3() throws Exception {
		log.error("Schedule Test");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("Schedule Test");
		qnaVO.setBoardTitle("Title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}
}
