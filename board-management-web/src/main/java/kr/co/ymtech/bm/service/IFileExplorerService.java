package kr.co.ymtech.bm.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import kr.co.ymtech.bm.controller.dto.FileDTO;

public interface IFileExplorerService {

//	public void createFolder();

	public List<FileDTO> loadAllFiles(String parentPath, String directoryName);

	
	public void saveFiles(List<MultipartFile> files);
}
