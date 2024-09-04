package com.sol.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.sol.app.qna.QnaFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownView extends AbstractView{
	
	@Value("${app.upload}")
	private String path;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				System.out.println("FileDownView");
				QnaFileVO qnaFileVO = (QnaFileVO)model.get("qnaFileVO");
												
				File file = new File(path, qnaFileVO.getFileName());
				
				//응답시 한글 Encoding 처리
				response.setCharacterEncoding("UTF-8");
				
				//파일의 크기
				response.setContentLength((int)file.length());
				
				//다운시 파일이름을 지정하고 인코딩 설정
				String downName = qnaFileVO.getOriName();
				downName = URLEncoder.encode(downName, "UTF-8");
				
				//Header 정보 설정
				response.setHeader("Content-Disposition", "attachment;fileName=\""+downName+"\"");
				response.setHeader("Content-Transfer-Encoding", "binary");
				
				//전송
				FileInputStream fi = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				
				FileCopyUtils.copy(fi, os);
				
				//자원 해제
				os.close();
				fi.close();
		
	} 
}
