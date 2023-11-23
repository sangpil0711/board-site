package kr.co.ymtech.bm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import kr.co.ymtech.bm.controller.dto.FileDTO;

public interface IFileExplorerService {

//	public void createFolder();

	public List<FileDTO> loadAllFiles(String parentPath, String directoryName);
	
	public void downloadFile(HttpServletResponse response, String Name, String Path);

	
	public void saveFiles(List<MultipartFile> files);
}
