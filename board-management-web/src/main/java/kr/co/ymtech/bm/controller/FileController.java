package kr.co.ymtech.bm.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.service.FileService;
import kr.co.ymtech.bm.service.IFileService;

/**
 * 파일 다운로드와 삭제를 위한 RestController
 * 
 * @author 황상필
 * @since 2023. 10. 23.
 */
@RestController
public class FileController {

	/**
	 * Controller-Service 연결
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	@Autowired
	private final IFileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	/**
	 * @Method downloadFile 게시글에 업로드된 파일을 다운로드 하는 메소드
	 *
	 * @param response http 응답
	 * @param fileName 업로드된 파일 이름
	 * 
	 * @return 파일이름을 받아와서 fileService로 넘겨 메소드 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	@GetMapping("/files/{fileId}")
	public void downloadFile(HttpServletResponse response, @RequestParam String fileName, @PathVariable String fileId) {
		fileService.downloadFile(response, fileName, fileId);
	}

}
