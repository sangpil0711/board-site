package kr.co.ymtech.bm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFileDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;
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
	public void saveFiles(SaveFileDTO uploadFile) {

		fileExplorerService.saveFiles(uploadFile);
	}

	@PostMapping("/{Name}")
	public void saveFolder(@PathVariable String Name, @RequestBody SaveFolderDTO saveFolderDTO) {

		fileExplorerService.saveFolder(Name, saveFolderDTO);
	}

	@GetMapping("/{Name}")
	public void downloadFile(HttpServletResponse response, @PathVariable String Name, @RequestParam String Path) {

		fileExplorerService.downloadFile(response, Name, Path);
	}

	@DeleteMapping("/{Name}")
	public void deleteFile(@PathVariable String Name, @RequestParam String Path) {

		fileExplorerService.deleteFile(Path, Name);
	}

	@PatchMapping("")
	public void updateFile(@RequestBody UpdateFileDTO updateFileDTO) {

		fileExplorerService.updateFile(updateFileDTO);
	}

	@PatchMapping("/{Name}")
	public void moveFile(@PathVariable String fileName, @RequestParam String folderName, @RequestParam String oldPath,
			@RequestParam String newPath) {
		fileExplorerService.moveFile(fileName, folderName, oldPath, newPath);
	}

}