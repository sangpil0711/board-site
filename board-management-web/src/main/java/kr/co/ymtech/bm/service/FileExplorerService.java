package kr.co.ymtech.bm.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.FileDTO;

@Service
public class FileExplorerService implements IFileExplorerService {

	@Override
	public List<FileDTO> loadAllFiles(String path) {

		File file = new File(path);
		File[] files = file.listFiles();
		List<FileDTO> list = new ArrayList<>();
		FileDTO dto = null;

		if (files != null) {
			for (File F : files) {

				dto = new FileDTO();

				// #1. 각 파일 정보 저장
				dto.setName(F.getName());
				dto.setIsDirectory(F.isDirectory());
				dto.setPath(F.getPath());

				// #2. 디렉토리인지 검사 -> 디렉토리면 재귀
//	            if (F.isDirectory()) {
//	            	dto.setChilds(
//	            			loadAllFiles(F.getAbsolutePath())
//            			);
//	            }

				list.add(dto);
			}
		}

		return list;
	}

	@Override
	public void createFolder() {

		String path = "C:/FileExplorer/새폴더";
		File Folder = new File(path);

		if (!Folder.exists()) {
			try {
				Folder.mkdir(); // 폴더 생성합니다.
				System.out.printf("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.printf("이미 폴더가 생성되어 있습니다.");
		}
	}

}
