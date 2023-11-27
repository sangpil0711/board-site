package kr.co.ymtech.bm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;

public interface IFileExplorerService {

	public List<FileDTO> loadAllFiles(String parentPath, String directoryName);
	
	public void downloadFile(HttpServletResponse response, String Name, String Path);
	
	public void saveFiles(SaveFileDTO uploadFile);
	
	public void deleteFile(String Path, String Name);
	
	public void saveFolder(String Name, SaveFolderDTO saveFolderDTO);
	
	public void updateFile(UpdateFileDTO updateFileDTO);
}
