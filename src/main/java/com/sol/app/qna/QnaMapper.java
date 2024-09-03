package com.sol.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sol.app.util.Pager;

@Mapper
public interface QnaMapper {
	public List<QnaVO> getList(Pager pager) throws Exception;
	public Integer add(QnaVO qnaVO) throws Exception;
}
