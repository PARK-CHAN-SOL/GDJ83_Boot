package com.sol.app.qna;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QnaVO{
	private Long boardNum;
	@NotBlank // (message = "필수 입력사항입니다")
	private String boardWriter;
	@Size(min = 2, max = 20)
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> ar;
}
