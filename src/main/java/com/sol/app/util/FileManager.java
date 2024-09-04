package com.sol.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
		public String fileSave(String path, MultipartFile multipartFile) throws Exception {
			File file = new File(path);
			
			if(!file.exists()) {
				file.mkdir();
			}
			
			String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
			
			file = new File(file, fileName);
			multipartFile.transferTo(file);
			
			return fileName;
		}
}
