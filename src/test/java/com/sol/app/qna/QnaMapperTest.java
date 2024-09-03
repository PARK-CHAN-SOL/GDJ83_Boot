package com.sol.app.qna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sol.app.util.Pager;

@SpringBootTest
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	
	/*
	 * @Test void addTest() throws Exception { QnaVO qnaVO = new QnaVO(); for(int i
	 * = 1; i < 110; i++) { qnaVO.setBoardContents("c"+i);
	 * qnaVO.setBoardTitle("t"+i); qnaVO.setBoardWriter("w"+i);
	 * qnaVO.setRef((long)i); qnaVO.setStep(0L); qnaVO.setDepth(0L); Integer result
	 * = qnaMapper.add(qnaVO); if(i%10==0) { Thread.sleep(100); } } }
	 */
	
	@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.makeRow();
		pager.setKind("k3");
		pager.setSearch("2");
		List<QnaVO> list = qnaMapper.getList(pager);
		assertNotEquals(list.size(), 0);
	}

}
