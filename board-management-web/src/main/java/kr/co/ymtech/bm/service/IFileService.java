package kr.co.ymtech.bm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	
	public String uploadFile(List<MultipartFile> files, List<String> fileNames);
	
//	public Integer downloadFile();
}
