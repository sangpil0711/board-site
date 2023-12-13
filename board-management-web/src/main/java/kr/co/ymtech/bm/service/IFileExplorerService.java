package kr.co.ymtech.bm.service;

import javax.servlet.http.HttpServletResponse;
import kr.co.ymtech.bm.controller.dto.FileExplorerDTO;
import kr.co.ymtech.bm.controller.dto.MoveFileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;
import kr.co.ymtech.bm.controller.dto.UploadFileDTO;
import kr.co.ymtech.bm.controller.dto.UploadFileResponseDTO;

/**
 * IFileExplorerService 인터페이스
 */
public interface IFileExplorerService {

	/**
	 * @Method loadAllFiles 서버안에 있는 모든 파일들을 가져오는 함수
	 * 
	 * @param parentPath    파일의 부모 경로
	 * @param directoryName 디렉토리 이름
	 * @return
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public FileExplorerDTO loadAllFiles(String parentPath, String directoryName);

	/**
	 * @Method 서버에 있는 파일을 다운로드 하는 함수
	 * 
	 * @param response http응답
	 * @param Name     업로드 된 파일 이름
	 * @param Path     업로드 된 파일 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 24.
	 */
	public void downloadFile(HttpServletResponse response, String name, String path);

	/**
	 * @Method saveFiles 서버에 파일을 업로드 하는 함수
	 * 
	 * @param uploadFile 서버에 업로드 할 파일
	 *
	 * @author 박상현
	 * @return 
	 * @since 2023. 11. 24.
	 */
	public UploadFileResponseDTO uploadFiles(UploadFileDTO uploadFile);

	/**
	 * @Method deleteFile 서버에서 파일을 삭제하는 함수
	 * 
	 * @param Path 삭제할 파일 경로
	 * @param Name 삭제할 파일 이름
	 *
	 * @author 박상현
	 * @return 
	 * @since 2023. 11. 24.
	 */
	public String deleteFile(String name, String path);

	/**
	 * @Method saveFolder 서버에 디렉토리를 추가하는 함수
	 * 
	 * @param saveFolderDTO 추가할 디렉토리 정보
	 * @param name 디렉토리 이름 
	 *
	 * @author 박상현
	 * @return 
	 * @since 2023. 11. 28.
	 */
	public String saveFolder(SaveFolderDTO saveFolderDTO);

	/**
	 * @Method updateFile 파일 또는 디렉토리 이름을 수정하는 함수
	 * 
	 * @param updateFileDTO 수정할 파일혹은 디렉토리 정보
	 * 
	 * @author 박상현
	 * @return 
	 * @since 2023. 11. 28.
	 */
	public String updateFile(UpdateFileDTO updateFileDTO);

	/**
	 * @Method moveFile 파일 또는 디렉토리를 이동하는 함수
	 * 
	 * @param moveFileDTO 이동과 관련된 파일 정보
	 *
	 * @author 박상현
	 * @return 
	 * @since 2023. 11. 30.
	 */
	public String moveFile(MoveFileDTO moveFileDTO);
}
