package kr.co.ymtech.bm.service;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.repository.IFileRepository;

/**
 * FileService 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 23.
 */
@Service
public class FileService implements IFileService {

	/**
	 * FileService-FileRepository 연결
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	private final IFileRepository fileRepository;
	private final static String SAVE_PATH = "C:/boardFile";

	@Autowired
	private FileService(IFileRepository IfileRepository) {
		this.fileRepository = IfileRepository;
	}

	/**
	 * @Method downloadFile 게시글에 업로드된 파일을 다운로드 하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IFileService#downloadFile(javax.servlet.http.HttpServletResponse, java.lang.String)
	 *
	 * @param response http 응답
	 * @param fileName 업로드된 파일 이름
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	@Override
	public void downloadFile(HttpServletResponse response, String fileName) {
		String filePath = SAVE_PATH + "/" + fileName;

		try (FileInputStream input = new FileInputStream(filePath); OutputStream output = response.getOutputStream()) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			IOUtils.copy(input, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Method resetFiles 게시글에 업로드된 파일을 초기화 시키는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IFileService#resetFiles(java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @return Repository에서 함수를 실행하여 해당 게시글에 업로드된 파일 삭제
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	@Override
	public Integer resetFiles(Integer index) {
		return fileRepository.resetFiles(index);
	}

}
