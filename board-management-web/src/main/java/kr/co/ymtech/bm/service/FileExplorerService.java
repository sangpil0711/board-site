package kr.co.ymtech.bm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public void saveFiles(List<MultipartFile> files) {
	    for (MultipartFile file : files) {
	        String originalFilename = file.getOriginalFilename();
	        String directoryPath = Paths.get(PathConfig.getFilePath(), originalFilename).normalize().toString();
	        System.out.println(files);

	        try (InputStream input = file.getInputStream(); OutputStream output = new FileOutputStream(directoryPath)) {
	            IOUtils.copy(input, output);
	        } catch (IOException e) {
	            System.out.println("파일 업로드 실패");
	        }
	    }
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

//   @Override
//   public void createFolder() {
//
//      String path = "C:/FileExplorer/새폴더";
//      File Folder = new File(path);
//
//      if (!Folder.exists()) {
//         try {
//            Folder.mkdir(); // 폴더 생성합니다.
//            System.out.printf("폴더가 생성되었습니다.");
//         } catch (Exception e) {
//            e.getStackTrace();
//         }
//      } else {
//         System.out.printf("이미 폴더가 생성되어 있습니다.");
//      }
//   }

}
