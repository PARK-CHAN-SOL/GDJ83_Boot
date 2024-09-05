package com.sol.app.qna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.sol.app.util.Pager;

@SpringBootTest
@Transactional // 모든 테스트 실행 후 롤백 처리
@ActiveProfiles("dev")
class QnaMapperTest {

	@Autowired
	private QnaMapper qnaMapper;

	@Test
	void addTest() throws Exception {
		for (int i = 7; i < 110; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setBoardContents("c" + i);
			qnaVO.setBoardTitle("t" + i);
			qnaVO.setBoardWriter("w" + i);
			qnaVO.setRef((long) i);
			qnaVO.setStep(0L);
			qnaVO.setDepth(0L);
			Integer result = qnaMapper.add(qnaVO);
			if (i % 10 == 0) {
				Thread.sleep(100);
			}
		}
	}

	@Test
	@Rollback(false) // 메서드 실행 후 rollback 하지 않음
	void getDetailTest() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		assertNotNull(qnaVO);
	}

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
