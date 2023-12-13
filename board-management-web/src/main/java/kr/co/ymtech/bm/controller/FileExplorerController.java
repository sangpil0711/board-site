package kr.co.ymtech.bm.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.FileExplorerDTO;
import kr.co.ymtech.bm.controller.dto.MoveFileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;
import kr.co.ymtech.bm.controller.dto.UploadFileDTO;
import kr.co.ymtech.bm.controller.dto.UploadFileResponseDTO;
import kr.co.ymtech.bm.service.FileExplorerService;
import kr.co.ymtech.bm.service.IFileExplorerService;

/**
 * 파일탐색기 FileExplorerController 클래스
 * 
 * @author 박상현
 * @since 2023. 11. 17.
 */
@RestController
@RequestMapping("/fileExplorer")
public class FileExplorerController {

   /**
    * Controller-Service 연결
    * 
    * @author 박상현
    * @since 2023. 11. 17.
    */
   private final IFileExplorerService fileExplorerService;

   public FileExplorerController(FileExplorerService fileExplorerService) {
      this.fileExplorerService = fileExplorerService;
   }

	/**
	 * @Method loadAllFiles 서버안에 있는 모든 파일들을 가져오는 함수
	 * 
	 * @param parentPath 파일의 부모경로
	 * @Param directoryName 디렉토리 이름
	 *
	 * @return 파일 경로, 깊이, 파일이름 을 가지고 반환
	 *
	 * @author 박상현
	 * @since 2023. 11. 17.
	 */
	@GetMapping("")
	public ResponseEntity<FileExplorerDTO> loadAllFiles(String parentPath, String directoryName) {

		FileExplorerDTO loadAllFileList = fileExplorerService.loadAllFiles(parentPath, directoryName);

		return new ResponseEntity<FileExplorerDTO>(loadAllFileList, HttpStatus.OK);
	}

	/**
	 * @Method saveFiles 서버에 파일을 업로드 하는 함수
	 * 
	 * @param saveFiles 서버에 업로드 할 파일
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@PostMapping("")
	public ResponseEntity<UploadFileResponseDTO> uploadFiles(UploadFileDTO uploadFile) {

		UploadFileResponseDTO uploadResult = fileExplorerService.uploadFiles(uploadFile);

		return new ResponseEntity<UploadFileResponseDTO>(uploadResult, HttpStatus.OK);
	}

	/**
	 * @Method downloadFile 서버에 있는 파일을 다운로드 하는 함수
	 * @param response http응답
	 * @param Name     업로드 된 파일 이름
	 * @param Path     업로드 된 파일 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@GetMapping("/{name}")
	public void downloadFile(HttpServletResponse response, @PathVariable String name, @RequestParam String path) {

		fileExplorerService.downloadFile(response, name, path);
	}

	/**
	 * @Method deleteFile 서버에서 파일을 삭제하는 함수
	 * 
	 * @param Name 삭제할 파일 이름
	 * @param Path 삭제할 파일 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteFile(@PathVariable String name, @RequestParam String path) {

		String deleteResult = fileExplorerService.deleteFile(name, path);

		if ("파일 삭제 성공!".equals(deleteResult)) {
			return new ResponseEntity<String>(deleteResult, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(deleteResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Method saveFolder 서버에 디렉토리를 추가하는 함수
	 * 
	 * @param saveFolderDTO 추가할 디렉토리 정보
	 * @param name          디렉토리 이름
	 *
	 * @author 박상현
	 * @since 2023. 11. 28.
	 */
	@PostMapping("/directory")
	public ResponseEntity<String> saveFolder(@RequestBody SaveFolderDTO saveFolderDTO) {

		String saveFolderResult = fileExplorerService.saveFolder(saveFolderDTO);

		if ("디렉토리 생성 성공!".equals(saveFolderResult)) {
			return new ResponseEntity<String>(saveFolderResult, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(saveFolderResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Method updateFile 파일 또는 디렉토리 이름을 수정하는 함수
	 * 
	 * @param updateFileDTO 수정할 파일혹은 디렉토리 정보
	 * 
	 * @author 박상현
	 * @since 2023. 11. 28.
	 */
	@PatchMapping("")
	public ResponseEntity<String> updateFile(@RequestBody UpdateFileDTO updateFileDTO) {

		String updateResult = fileExplorerService.updateFile(updateFileDTO);

		if ("파일 이름 변경 성공!".equals(updateResult)) {
			return new ResponseEntity<String>(updateResult, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(updateResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Method moveFile 파일 또는 디렉토리를 이동하는 함수
	 * 
	 * @param fileName   파일 또는 디렉토리 이름
	 * @param folderName 디렉토라 이름
	 * @param oldPath    원래 경로
	 * @param newPath    새로운 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 30.
	 */
	@PatchMapping("/move")
	public ResponseEntity<String> moveFile(@RequestBody MoveFileDTO moveFileDTO) {
		
		String moveFileResult = fileExplorerService.moveFile(moveFileDTO);

		if ("파일 이동 성공!".equals(moveFileResult)) {
			return new ResponseEntity<String>(moveFileResult, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(moveFileResult, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}