package com.sol.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO 	Data Transfer  Object
//VO	Value object (읽기 전용)

/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString*/
@Data
public class NoticeVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
}
