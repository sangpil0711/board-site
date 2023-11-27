package kr.co.ymtech.bm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.config.PathConfig;
import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.controller.dto.FileExplorerDTO;
import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 파일 탐색기 FileExplorerService 클래스
 * 
 * @author 박상현
 * @since 2023. 11. 17.
 */
@Service
public class FileExplorerService implements IFileExplorerService {

	/**
	 * 파일 경로 연결
	 * 
	 * @author 박상현
	 * @since 2023. 11. 17.
	 */
	private final PathConfig PathConfig;

	public FileExplorerService(PathConfig PathConfig) {
		this.PathConfig = PathConfig;
	}

	/**
	 * 
	 * @Method loadAllFiles 서버안에 있는 모든 파일들을 가져오는 함수
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#loadAllFiles(java.lang.String,
	 *      java.lang.String)
	 * 
	 * @param parentPath    파일의 부모 경로
	 * @param directoryName 디렉토리 이름
	 *
	 * @author 박상현
	 * @since 2023. 11. 17.
	 */
	@Override
	public List<FileDTO> loadAllFiles(String parentPath, String directoryName) {
		File file = null;

		if (parentPath == null) {
			file = new File(PathConfig.getFilePath());
			parentPath = PathConfig.getFilePath();
		} else {
			file = new File(Paths.get(parentPath).resolve(directoryName).normalize().toString());
			parentPath = Paths.get(parentPath).resolve(directoryName).normalize().toString();
		}

		List<FileDTO> list = new ArrayList<>();

		if (file.isDirectory()) { // parentPath가 디렉토리 인 경우만 처리
			File[] files = file.listFiles();
			FileDTO dto = null;

			if (files != null) { // files null 일때 예외 처리
				for (File f : files) {
					dto = new FileDTO();
					dto.setName(f.getName());
					dto.setIsDirectory(f.isDirectory());
					dto.setPath(parentPath);

					if (f.isDirectory()) { // 디렉토리인 경우에만 재귀 호출 수행
						dto.setChild(loadAllFiles(parentPath, f.getName()));
					}

					list.add(dto);
				}
			}
		}
		list.sort((file1, file2) -> {
			if (file1.getIsDirectory() && !file2.getIsDirectory()) {
				return -1; // f1은 디렉토리이고, f2는 파일입니다.
			} else if (!file1.getIsDirectory() && file2.getIsDirectory()) {
				return 1; // f1은 파일이고, f2는 디렉토리입니다.
			} else {
				return 0;
			}
		});

		return list;
	}

	/**
	 * @Method uploadFiles 서버에 파일을 업로드 하는 함수
	 * 
	 * @param uploadFile 서버에 업로드 할 파일
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#uploadFiles(kr.co.ymtech.bm.controller.dto.FileExplorerDTO)
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	public void uploadFiles(FileExplorerDTO uploadFile) {

		String directoryPath = null;
		String filePath = Paths.get(uploadFile.getPath()).resolve(uploadFile.getName()).normalize().toString();

		for (MultipartFile file : uploadFile.getFiles()) {
			directoryPath = Paths.get(filePath).resolve(file.getOriginalFilename()).normalize().toString();

			try (InputStream input = file.getInputStream(); OutputStream output = new FileOutputStream(directoryPath)) {
				IOUtils.copy(input, output);
			} catch (IOException e) {
				System.out.println("파일 업로드 실패");
			}
		}
	}

	/**
	 * @Method 서버에 있는 파일을 다운로드 하는 함수
	 * 
	 * @param response http응답
	 * @param Name     업로드 된 파일 이름
	 * @param Path     업로드 된 파일 경로
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#downloadFile(javax.servlet.http.HttpServletResponse,
	 *      java.lang.String, java.lang.String)
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@Override
	public void downloadFile(HttpServletResponse response, String Name, String Path) {

		String filePath = Paths.get(Path).resolve(Name).normalize().toString();

		// 지정된 경로의 폴더에서 파일을 찾아서 다운로드
		try (FileInputStream input = new FileInputStream(filePath); OutputStream output = response.getOutputStream()) {
			String fileName = URLEncoder.encode(Name, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			IOUtils.copy(input, output);
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
		}
	}

	/**
	 * @Method deleteFile 서버에서 파일을 삭제하는 함수
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#deleteFile(java.lang.String,
	 *      java.lang.String)
	 * 
	 * @param Name 삭제할 파일 이름
	 * @param Path 삭제할 파일 경로
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@Override
	public void deleteFile(String Path, String Name) {

		File deletedFile = new File(Paths.get(Path).resolve(Name).normalize().toString());
		deletedFile.delete();
	}

	@Override
	public void createDirectory() {
		String path = "C:\\FileExplorer";
		File Folder = new File(path);

		if (!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
	}

}
