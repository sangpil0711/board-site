package kr.co.ymtech.bm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.config.PathConfig;
import kr.co.ymtech.bm.controller.dto.FileDTO;
import kr.co.ymtech.bm.controller.dto.FileExplorerDTO;
import kr.co.ymtech.bm.controller.dto.SaveFolderDTO;
import kr.co.ymtech.bm.controller.dto.UpdateFileDTO;
import kr.co.ymtech.bm.controller.dto.UploadFileDTO;

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
	public FileExplorerDTO loadAllFiles(String parentPath, String directoryName) {
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
						FileExplorerDTO childExplorer = loadAllFiles(parentPath, f.getName());
						List<FileDTO> childList = childExplorer.getFileList();
						dto.setChild(childList);
					}

					list.add(dto);
				}
			}
		}

		list.sort((file1, file2) -> {
			if (file1.getIsDirectory() && !file2.getIsDirectory()) {
				return -1; // file1은 디렉토리이고, file2는 파일입니다.
			} else if (!file1.getIsDirectory() && file2.getIsDirectory()) {
				return 1; // file1은 파일이고, file2는 디렉토리입니다.
			} else {
				return 0;
			}
		});

		FileExplorerDTO result = new FileExplorerDTO();
		result.setFileList(list);
		result.setFirstPath(PathConfig.getFilePath());

		return result;
	}

	/**
	 * @Method saveFiles 서버에 파일을 업로드 하는 함수
	 * 
	 * @param uploadFile 서버에 업로드 할 파일
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#saveFiles(kr.co.ymtech.bm.controller.dto.UploadFileDTO)
	 *
	 * @author 박상현
	 * @since 2023. 11. 23.
	 */
	@Override
	public String uploadFiles(UploadFileDTO uploadFile) {

		String directoryPath;
		String filePath;

		if (uploadFile.getPath() == null && uploadFile.getName() == null) {
			filePath = PathConfig.getFilePath();
		} else {
			filePath = Paths.get(uploadFile.getPath()).resolve(uploadFile.getName()).normalize().toString();
		}

		List<String> failedFiles = new ArrayList<>();

		for (MultipartFile file : uploadFile.getFiles()) {
			directoryPath = Paths.get(filePath).resolve(file.getOriginalFilename()).normalize().toString();

			if (Files.exists(Paths.get(directoryPath))) {
				// 중복된 파일이면 실패 목록에 추가
				failedFiles.add(file.getOriginalFilename());
			} else {
				try (InputStream input = file.getInputStream();
						OutputStream output = new FileOutputStream(directoryPath)) {
					IOUtils.copy(input, output);
				} catch (IOException e) {
					// 파일 업로드 실패 시 실패 목록에 추가
					failedFiles.add(file.getOriginalFilename());
				}
			}
		}

		if (!failedFiles.isEmpty()) {
			return "파일 업로드 실패!";
		}

		return "파일 업로드 성공!";
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
	public void downloadFile(HttpServletResponse response, String name, String path) {

		String filePath = Paths.get(path).resolve(name).normalize().toString();

		// 지정된 경로의 폴더에서 파일을 찾아서 다운로드
		try (FileInputStream input = new FileInputStream(filePath); OutputStream output = response.getOutputStream()) {
			String fileName = URLEncoder.encode(name, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			IOUtils.copy(input, output);
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
		}
	}

	/**
	 * @Method deleteFile 서버에 업로드 된 디렉토리 또는 파일을 삭제하는 함수
	 * 
	 * @Param Path 삭제할 파일 또는 디렉토리 경로
	 * @param Name 삭제할 파일 또는 디렉토리 이름
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#deleteFile(java.lang.String,
	 *      java.lang.String)
	 *
	 * @author 박상현
	 * @since 2023. 11. 28.
	 */
	@Override
	public String deleteFile(String name, String path) {

		File deletedFile = new File(Paths.get(path).resolve(name).normalize().toString());

		if (deletedFile.isDirectory()) {
			try {
				FileUtils.deleteDirectory(deletedFile);
			} catch (IOException e) {
				return "파일 삭제 실패";
			}
		} else {
			deletedFile.delete();
		}
		return "파일 삭제 성공!";
	}

	/**
	 * @Method saveFolder 서버에 디렉토리를 추가하는 함수
	 * 
	 * @param Name          디렉토리 이름
	 * @param saveFolderDTO 추가할 디렉토리 정보
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#saveFolder(java.lang.String,
	 *      kr.co.ymtech.bm.controller.dto.SaveFolderDTO)
	 *
	 * @author 박상현
	 * @since 2023. 11. 28.
	 */
	@Override
	public String saveFolder(String name, SaveFolderDTO saveFolderDTO) {

		String filePath;

		if (saveFolderDTO.getPath() == null && name == null) {
			filePath = Paths.get(PathConfig.getFilePath()).resolve(saveFolderDTO.getNewFolderName()).normalize()
					.toString();
		} else {
			filePath = Paths.get(saveFolderDTO.getPath()).resolve(name).resolve(saveFolderDTO.getNewFolderName())
					.normalize().toString();
		}

		File existingFile = new File(filePath);
		if (existingFile.exists()) {
			return "디렉토리 이름중복이므로 생성 실패!";
		}

		File folder = new File(filePath);

		if (folder.mkdir()) {
			return "디렉토리 생성 성공!";
		} else {
			return "디렉토리 생성 실패!";
		}
	}

	/**
	 * @Method updateFile 파일 또는 디렉토리 이름을 수정하는 함수
	 * 
	 * @param updateFileDTO 수정할 파일혹은 디렉토리 정보
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#updateFile(kr.co.ymtech.bm.controller.dto.UpdateFileDTO)
	 *
	 * @author 박상현
	 * @since 2023. 11. 28.
	 */
	@Override
	public String updateFile(UpdateFileDTO updateFileDTO) {
		Path oldFileName = Paths.get(updateFileDTO.getPath()).resolve(updateFileDTO.getName()).normalize();
		Path newFileName = Paths.get(updateFileDTO.getPath()).resolve(updateFileDTO.getNewFileName()).normalize();
		if (Files.exists(newFileName)) {
			return "파일 이름 중복이므로 변경 실패!";
		} else {
			try {
				Files.move(oldFileName, newFileName);
			} catch (IOException e) {
				return "파일 이름 변경 실패!";
			}
		}
		return "파일 이름 변경 성공!";
	}

	/**
	 * @Method moveFile 파일 또는 디렉토리를 이동하는 함수
	 * 
	 * @param fileName   파일 또는 디렉토리 이름
	 * @param folderName 디렉토라 이름
	 * @param oldPath    원래 경로
	 * @param newPath    새로운 경로
	 *
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#moveFile(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 *
	 * @author 박상현
	 * @since 2023. 11. 30.
	 */
	@Override
	public String moveFile(String fileName, String folderName, String oldPath, String newPath) {
		Path oldFilePath = Paths.get(oldPath).resolve(fileName).normalize();
		Path newFilePath;
		try {
			if (folderName == null && newPath == null) {
				newFilePath = Paths.get(PathConfig.getFilePath()).resolve(fileName).normalize();
				if (Files.exists(newFilePath)) {
					return "파일 이름 중복이므로 이동 실패!";
				}
			} else {
				newFilePath = Paths.get(newPath).resolve(folderName).resolve(fileName).normalize();
				if (Files.exists(newFilePath)) {
					return "파일 이름 중복이므로 이동 실패!";
				}
			}
			Files.move(oldFilePath, newFilePath);
		} catch (IOException e) {
			return "파일 이동 실패!";
		}
		return "파일 이동 성공!";
	}

}