package kr.co.ymtech.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.ymtech.bm.service.FileExplorerService;
import kr.co.ymtech.bm.service.IFileExplorerService;

@RestController
public class FileExplorerController {

	@Autowired
	private final IFileExplorerService fileExplorerService;
	
	public FileExplorerController(FileExplorerService fileExplorerService) {
		this.fileExplorerService = fileExplorerService;
	}
	
	
	@GetMapping("/load")
	public void createFolder() {
		fileExplorerService.createFolder();
		
	}
	
	
	

}
