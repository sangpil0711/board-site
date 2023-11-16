package kr.co.ymtech.bm.service;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.config.PathConfig;

/**
 * FileService 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 23.
 */
@Service
public class FileService implements IFileService {
	
	/**
	 * BoardService-BoardRepository 연결
	 * 
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	private final PathConfig pathConfig;

	@Autowired
	private FileService(PathConfig pathConfig) {
		this.pathConfig = pathConfig;
	}

	/**
	 * @Method downloadFile 게시글에 업로드된 파일을 다운로드 하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IFileService#downloadFile(javax.servlet.http.HttpServletResponse, java.lang.String)
	 *
	 * @param response http 응답
	 * @param fileName 업로드된 파일 이름
	 * @param fileId 업로드된 파일 uuid
	 *
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	@Override
	public void downloadFile(HttpServletResponse response, String fileName, String fileId) {
		
		String filePath = pathConfig.getImagePath() + "/" + fileId + "_" + fileName;

		// 지정된 경로의 폴더에서 파일을 찾아서 다운로드
		try (FileInputStream input = new FileInputStream(filePath); OutputStream output = response.getOutputStream()) {
			String fileName1 = URLEncoder.encode(fileName, "UTF-8");
			fileName1 = fileName1.replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName1 + "\"");
			IOUtils.copy(input, output);
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
		}
	}

}
