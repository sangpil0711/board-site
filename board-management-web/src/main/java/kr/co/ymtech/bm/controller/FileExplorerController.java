package kr.co.ymtech.bm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.controller.dto.FileDTO;
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
	 * @return 파일 경로, 깊이, 파일이름 을 가지고 반환
	 *
	 * @author 박상현
	 * @since 2023. 11. 17.
	 */
	@GetMapping("")
	public List<FileDTO> loadAllFiles(String parentPath, String directoryName) {

		return fileExplorerService.loadAllFiles(parentPath, directoryName);
	}

	@PostMapping("")
	public void saveFiles(List<MultipartFile> files) {
		System.out.println(files);
		fileExplorerService.saveFiles(files);
		
	}

//	@PostMapping("/fileExplorer")
//	public void createFolder() {
//		fileExplorerService.createFolder();
//		
//	}

}
