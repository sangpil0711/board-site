package kr.co.ymtech.bm.service;

import java.awt.dnd.DropTargetAdapter;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.config.ImagePathConfig;
import kr.co.ymtech.bm.controller.dto.FileDTO;

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
	private final ImagePathConfig imagePathConfig;

	public FileExplorerService(ImagePathConfig imagePathConfig) {
		this.imagePathConfig = imagePathConfig;
	}

	/**
	 * 
	 * @Method loadAllFiles 서버안에 있는 모든 파일들을 가져오는 함수
	 * 
	 * @see kr.co.ymtech.bm.service.IFileExplorerService#loadAllFiles(java.lang.String,
	 *      java.lang.String)
	 *
	 * @author 박상현
	 * @since 2023. 11. 17.
	 */
	@Override
	public List<FileDTO> loadAllFiles(String parentPath, String directoryName) {
		File file = null;

		if (parentPath == null) {
			file = new File(imagePathConfig.getFilePath());
			parentPath = imagePathConfig.getFilePath();
		} else {
			file = new File(Paths.get(parentPath).resolve(directoryName).normalize().toString());
			parentPath = Paths.get(parentPath).resolve(directoryName).normalize().toString();
		}

	
		List<FileDTO> list = new ArrayList<>();

		if (file.isDirectory()) { // parentPath가 디렉토리 인 경우만 처리
			File[] files = file.listFiles();
			FileDTO dto = null;

			if (files != null) {   //files null 일때 예외 처리 
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

		return list;
	}

//	@Override
//	public void createFolder() {
//
//		String path = "C:/FileExplorer/새폴더";
//		File Folder = new File(path);
//
//		if (!Folder.exists()) {
//			try {
//				Folder.mkdir(); // 폴더 생성합니다.
//				System.out.printf("폴더가 생성되었습니다.");
//			} catch (Exception e) {
//				e.getStackTrace();
//			}
//		} else {
//			System.out.printf("이미 폴더가 생성되어 있습니다.");
//		}
//	}

}
