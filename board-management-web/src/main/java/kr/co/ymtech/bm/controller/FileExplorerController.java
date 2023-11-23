package kr.co.ymtech.bm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/{Name}")
	public void downloadFile(HttpServletResponse response, @PathVariable String Name, @RequestParam String Path) {

		fileExplorerService.downloadFile(response, Name, Path);
	}

//   @PostMapping("")
//   public ResponseEntity<Integer> saveFiles() {
//      return null;
//   }

//   @PostMapping("/fileExplorer")
//   public void createFolder() {
//      fileExplorerService.createFolder();
//      
//   }

}