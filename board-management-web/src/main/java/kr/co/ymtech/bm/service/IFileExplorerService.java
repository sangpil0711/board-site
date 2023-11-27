package kr.co.ymtech.bm.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;

/**
 *  IFileExplorerService 인터페이스
 */
public interface IFileExplorerService {

	/**
	 * @Method loadAllFiles 서버안에 있는 모든 파일들을 가져오는 함수
	 * 
	 * @param parentPath 파일의 부모 경로
	 * @param directoryName 디렉토리 이름
	 * @return
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public List<FileDTO> loadAllFiles(String parentPath, String directoryName);

	/**
	 * @Method 서버에 있는 파일을 다운로드 하는 함수
	 * 
	 * @param response http응답
	 * @param Name 업로드 된 파일 이름
	 * @param Path 업로드 된 파일 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public void downloadFile(HttpServletResponse response, String Name, String Path);

	/**
	 * @Method uploadFiles  서버에 파일을 업로드 하는 함수
	 * 
	 * @param uploadFile 서버에 업로드 할 파일
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public void uploadFiles(FileExplorerDTO uploadFile);

	/**
	 * @Method deleteFile 서버에서 파일을 삭제하는 함수
	 * 
	 * @param Path 삭제할 파일 경로
	 * @param Name 삭제할 파일 이름 
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public void deleteFile(String Path, String Name);
	
	public void saveFiles(SaveFileDTO uploadFile);
	
	public void deleteFile(String Path, String Name);
	
	public void saveFolder(String Name, SaveFolderDTO saveFolderDTO);
	
	public void updateFile(UpdateFileDTO updateFileDTO);
}
