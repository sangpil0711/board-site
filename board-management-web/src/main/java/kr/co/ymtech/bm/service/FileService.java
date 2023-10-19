package kr.co.ymtech.bm.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.repository.IFileRepository;
import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

@Service
public class FileService implements IFileService {

	private final IFileRepository fileRepository;

	@Autowired
	private FileService(IFileRepository IfileRepository) {
		this.fileRepository = IfileRepository;
	}

	@Override
	public String uploadFile(List<MultipartFile> files, List<String> fileNames) {
		try {
			String savePath = "C:/boardFile";
			
			System.out.println(files);

			for (int i = 0; i < fileNames.size(); i++) {
				MultipartFile file = files.get(i);
				String fileName = fileNames.get(i);
				String filePath = savePath + fileNames.get(i);

				try (InputStream input = file.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
					IOUtils.copy(input, output);
				}

				List<BoardVO> lastBoard = fileRepository.lastBoard();
				BoardVO savedBoard = lastBoard.get(0);

				FileVO boardFile = new FileVO();
				boardFile.setFileId(UUID.randomUUID().toString());
				boardFile.setBoardIndex(savedBoard.getIndex());
				boardFile.setFilePath(filePath);
				boardFile.setFileName(fileName);

				fileRepository.saveFile(boardFile);	
			}

			return "파일 업로드 완료";
		} 
		catch (IOException e) {
			e.printStackTrace();
			return "파일 업로드 중 오류가 발생했습니다.";
		}
	}

//	@Override
//	public Integer downloadFile() {
//		
//	}
}
