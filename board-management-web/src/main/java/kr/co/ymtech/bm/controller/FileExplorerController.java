package kr.co.ymtech.bm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.service.FileExplorerService;
import kr.co.ymtech.bm.service.IFileExplorerService;

@RestController
@RequestMapping("/fileExplorer")
public class FileExplorerController {

	private final IFileExplorerService fileExplorerService;

	public FileExplorerController(FileExplorerService fileExplorerService) {
		this.fileExplorerService = fileExplorerService;
	}

	@GetMapping("")
	public List<FileDTO> loadAllFiles(String path, int depth, String name) {
		return fileExplorerService.loadAllFiles(path, depth, name);
	}

//	@PostMapping("/fileExplorer")
//	public void createFolder() {
//		fileExplorerService.createFolder();
//
//	}

}
