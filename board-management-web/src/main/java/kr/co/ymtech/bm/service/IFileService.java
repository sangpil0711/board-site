package kr.co.ymtech.bm.service;

import javax.servlet.http.HttpServletResponse;

public interface IFileService {
	
	/**
	 * @Method downloadFile 게시글에 업로드된 파일을 다운로드 하는 메소드
	 *
	 * @param response http 응답
	 * @param fileName 업로드된 파일 이름
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	public void downloadFile(HttpServletResponse response, String fileName, String fileId);
	
}
