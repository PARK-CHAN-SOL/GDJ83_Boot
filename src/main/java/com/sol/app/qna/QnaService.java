package com.sol.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sol.app.util.FileManager;
import com.sol.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		log.info("Upload Path: {}",upload);
		return qnaMapper.getList(pager);
	}
	
	public Integer add(QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		log.info("===========Insert Before BoardNum: {}", qnaVO.getBoardNum());
		Integer result = qnaMapper.add(qnaVO);
		log.info("===========Insert After BoardNum: {}", qnaVO.getBoardNum());
		result = qnaMapper.refUpdate(qnaVO);
		
		//파일을 HDD에 저장 후 DB에 정보 추가
		for(MultipartFile file : attaches) {
			
			if(file == null || file.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(upload+name, file);
			log.info("저장된 파일명: {}", fileName);
			
		}
		
		
		return 0;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
}
