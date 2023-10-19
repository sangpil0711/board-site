package kr.co.ymtech.bm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.service.FileService;
import kr.co.ymtech.bm.service.IFileService;

@RestController
public class FileController {

	@Autowired
	private final IFileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@PostMapping("/files")
	public ResponseEntity<String> saveFile(@RequestBody List<MultipartFile> files, @RequestParam List<String> fileNames) {
		
		String filelistSave = fileService.uploadFile(files, fileNames);
		
		return new ResponseEntity<String>(filelistSave, HttpStatus.OK);
	}
	
}
