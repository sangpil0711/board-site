package kr.co.ymtech.bm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.config.ImagePathConfig;
import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.service.FileExplorerService;
import kr.co.ymtech.bm.service.IFileExplorerService;

@RestController
public class FileExplorerController {

	private final ImagePathConfig imagePathConfig;
	
	private final IFileExplorerService fileExplorerService;
	
	public FileExplorerController(FileExplorerService fileExplorerService, ImagePathConfig imagePathConfig) {
		this.fileExplorerService = fileExplorerService;
		this.imagePathConfig = imagePathConfig;
	}
	
	
	
	@GetMapping("/fileExplorer")
	public List<FileDTO> loadAllFiles() {
		
		return fileExplorerService.loadAllFiles(imagePathConfig.getFilePath());
	}
	
	
	@PostMapping("/fileExplorer")
	public void createFolder() {
		fileExplorerService.createFolder();
		
	}
	
	
	

}
