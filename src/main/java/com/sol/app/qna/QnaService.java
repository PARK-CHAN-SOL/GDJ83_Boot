package com.sol.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sol.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		return qnaMapper.getList(pager);
	}
	
	public Integer add(QnaVO qnaVO) throws Exception {
		log.info("===========Insert Before BoardNum: {}", qnaVO.getBoardNum());
		Integer result = qnaMapper.add(qnaVO);
		log.info("===========Insert After BoardNum: {}", qnaVO.getBoardNum());
		result = qnaMapper.refUpdate(qnaVO);
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
}
