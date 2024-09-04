package com.sol.app.qna;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sol.app.aws.S3Service;
import com.sol.app.util.FileManager;
import com.sol.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private S3Service s3Service;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		return qnaMapper.getList(pager);
	}
	
	public Integer add(QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		Integer result = qnaMapper.add(qnaVO);
		result = qnaMapper.refUpdate(qnaVO);
		
		//파일을 HDD에 저장 후 DB에 정보 추가
		for(MultipartFile file : attaches) {
			
			if(file == null || file.isEmpty()) {
				continue;
			}
			
			String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
			
			log.info(s3Service.upload(file, fileName));
			
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(file.getOriginalFilename());
			qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			
			result = qnaMapper.addFile(qnaFileVO);
		}
		
		
		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	};

}
