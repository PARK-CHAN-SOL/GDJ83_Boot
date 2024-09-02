package com.sol.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	public List<NoticeVO> getList()	throws Exception;
	public Integer add(NoticeVO noticeVO) throws Exception;
}
