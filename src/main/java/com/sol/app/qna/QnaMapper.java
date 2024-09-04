package com.sol.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sol.app.util.Pager;

@Mapper
public interface QnaMapper {
	public List<QnaVO> getList(Pager pager) throws Exception;

	public Integer add(QnaVO qnaVO) throws Exception;

	public Integer refUpdate(QnaVO qnaVO) throws Exception;

	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
	
	public Integer addFile(QnaFileVO qnaFileVO) throws Exception;
	
	public List<QnaFileVO> getFileList(QnaVO qnaVO) throws Exception;
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception;
}
