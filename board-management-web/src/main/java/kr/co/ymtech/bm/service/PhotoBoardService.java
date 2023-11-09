package kr.co.ymtech.bm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardPageDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardUpdateDTO;
import kr.co.ymtech.bm.repository.IBoardRepository;
import kr.co.ymtech.bm.repository.ICommentRepository;
import kr.co.ymtech.bm.repository.IPhotoBoardRepository;
import kr.co.ymtech.bm.repository.vo.FileVO;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시판 PhotoBoardService 클래스
 * 
 * @author 박상현
 * @since 2023. 10. 24.
 */
@Service
public class PhotoBoardService implements IPhotoBoardService {

	/**
	 * PhotoBoardService-PhotoBoardRepository 연결
	 * 
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	private final IPhotoBoardRepository photoBoardRepository;
	private final ICommentRepository commentRepository;
	private final IBoardRepository boardRepository;
	private final static String SAVE_PATH = "C:/boardFile";

	@Autowired
	public PhotoBoardService(IPhotoBoardRepository IphotoBoardRepository,   ICommentRepository IcommentRepository, IBoardRepository IboardRepository) {
		this.photoBoardRepository = IphotoBoardRepository;
		this.commentRepository = IcommentRepository;
		this.boardRepository = IboardRepository;
	}

	/**
	 * @Method findPhotoBoard : 조건에 따른 게시글 정보를 DB에서 받아오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#findPhotoBoard(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber : 게시판 페이지 번호
	 * @param itemSize   : 게시판 페이지 당 게시글 수
	 * @param searchType : 게시판 검색 유형
	 * @param keyword    : 게시판 검색어
	 * @param category   : 일반게시판,사진게시판 구분 카테고리 (일반게시판 0, 사진게시판 1)
	 * 
	 * @return findPage 메소드와 findAll 메소드를 boardPageDTO 변수에 담아 반환
	 *
	 * @author 박상현
	 * @since 2023. 10. 31.
	 */
	@Override
	   public PhotoBoardPageDTO findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType,
	           String keyword, Integer category) {

	       List<PhotoBoardVO> photoBoardList = photoBoardRepository.findPhotoBoard(pageNumber, itemSize, searchType,
	               keyword, category);
	       Integer boardCount = photoBoardRepository.findCount(searchType, keyword, category);

	       PhotoBoardPageDTO photoBoardPage = new PhotoBoardPageDTO();
	       photoBoardPage.setTotalCount(boardCount);

	       for (PhotoBoardVO vo : photoBoardList) {

	           List<FileVO> filesForBoard = photoBoardRepository.photoBoardFile(vo.getIndex(), pageNumber, itemSize, searchType, keyword, category);
	           
	           if (vo.getFile() == null) {
	               vo.setFile(new ArrayList<>());
	           }

	           vo.getFile().addAll(filesForBoard);
	       }

	       photoBoardPage.setPhotoBoardList(photoBoardList);

	       return photoBoardPage;
	   }

	/**
	 * @Method savePhotoBoard 사진게시판에 게시물을 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#savePhotoBoard(kr.co.ymtech.bm.controller.dto.PhotoBoardDTO,
	 *      java.util.List, java.util.List)
	 *
	 * @param photo 클라이언트가 저장하려고 하는 게시물 정보
	 * 
	 * @return 저장하려는 게시물 정보
	 *
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	@Override
	public void savePhotoBoard(PhotoBoardDTO photo) {

		PhotoBoardVO lastPhotoBoard = photoBoardRepository.lastPhotoBoard();
		List<FileVO> boardFiles = new ArrayList<FileVO>();

		// dto -> vo 변환
		PhotoBoardVO vo = new PhotoBoardVO();
		vo.setIndex(lastPhotoBoard.getIndex() + 1);
		vo.setTitle(photo.getTitle());
		if(photo.getText() == null) {
			vo.setText("");
		} else {
			vo.setText(photo.getText());
		}
		vo.setCategory(photo.getCategory());
		vo.setCreateDate(new Date().getTime());

		try {

			// 게시글 작성 시 업로드 되는 파일이 있으면 동작
			if (photo.getFiles() != null) {
				for (int i = 0; i < photo.getFiles().size(); i++) {
					MultipartFile files = photo.getFiles().get(i);
					String originalFileName = files.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID + "_" + originalFileName;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
					boardFile.setBoardIndex(lastPhotoBoard.getIndex() + 1);
					boardFile.setFilePath(SAVE_PATH);
					boardFile.setFileName(originalFileName);
					boardFile.setFileSize(files.getSize());

					boardFiles.add(boardFile);

					// 업로드 되는 파일을 지정된 경로의 폴더에 저장
					try (InputStream input = files.getInputStream();
							OutputStream output = new FileOutputStream(filePath)) {
						IOUtils.copy(input, output);
					} catch (IOException e) {
						System.out.println("파일 업로드 실패");
					}

				}
			}
		} catch (Exception e) {
			System.out.println("파일 저장 실패");
		}

		photoBoardRepository.savePhotoBoard(vo, boardFiles);

	}
	
	/**
	 * 
	 * @Method updatePhotoBoard 사진게시판 게시물 내용을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#updatePhotoBoard(kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO)
	 *
	 * @param board 클라이언트가 요청한 게시물 내용을 포함
	 * 
	 * @return 업데이트 한 게시물 내용을 update 변수에 담아 반환
	 *
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	@Override
	public void updatePhotoBoard(PhotoBoardUpdateDTO photo) {

		List<FileVO> photoBoardFiles = new ArrayList<FileVO>();

		// dto -> vo 변환
		PhotoBoardVO vo = new PhotoBoardVO();
		vo.setIndex(photo.getIndex());
		vo.setTitle(photo.getTitle());
		if (photo.getText() == null) {
			vo.setText("");
		} else {
			vo.setText(photo.getText());
		}

		try {
			// 게시글 수정 시 추가된 파일이 있으면 동작
			if (photo.getAddFiles() != null) {
				// 추가된 파일의 크기에 따라 반복하여 파일을 저장
				for (int i = 0; i < photo.getAddFiles().size(); i++) {
					MultipartFile file = photo.getAddFiles().get(i);
					String originalFileName = file.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID + "_" + originalFileName;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
					boardFile.setBoardIndex(photo.getIndex());
					boardFile.setFilePath(SAVE_PATH);
					boardFile.setFileName(originalFileName);
					boardFile.setFileSize(file.getSize());

					photoBoardFiles.add(boardFile);

					// 추가된 파일을 지정된 경로의 폴더에 저장
					try (InputStream input = file.getInputStream();
							OutputStream output = new FileOutputStream(filePath)) {
						IOUtils.copy(input, output);
					} catch (IOException e) {
						System.out.println("파일 저장 실패");
					}
				}
			}

			// 게시글 수정 시 삭제된 파일이 있으면 동작
			if (photo.getDeleteFiles() != null) {

				// SAVE_PATH에 있는 파일 리스트를 전부 가져옴
				File dir = new File(SAVE_PATH);
				File files[] = dir.listFiles();

				List<String> deleteFileNames = photo.getDeleteFiles();

				// 가져온 파일리스트에서 삭제된 파일의 uuid가 포함되어 있으면 지정된 경로의 폴더에서 삭제
				for (String deleteFileName : deleteFileNames) {
					for (File file : files) {
						if (file.getName().contains(deleteFileName)) {
							if (file.exists()) {
								if (file.delete()) {
								} else {
									System.out.println("파일 삭제 실패");
								}
							} else {
								System.out.println("파일이 존재하지 않음");
							}
						}
					}
					photoBoardRepository.deleteFile(photo.getIndex(), deleteFileName);
				}
			}
		} catch (Exception e) {
			System.out.println("파일 수정 실패");
		}

		photoBoardRepository.updatePhotoBoard(vo, photoBoardFiles);
	}

	/**
	 * Method : 사진게시물을 삭제하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @return : 사진게시물을 DB에서 삭제하고 성공하면 1, 실패하면 0을 photoboardlistDelete 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@Override
	public Integer deletePhotoBoard(Integer index) {

		List<FileVO> files = photoBoardRepository.files(index);

		// 삭제하려는 게시글에 업로드된 파일을 지정된 경로의 폴더에서 삭제
		for (FileVO file : files) {

			String filePath = SAVE_PATH + "/" + file.getFileId() + "_" + file.getFileName();

			File deleteFile = new File(filePath);

			if (deleteFile.exists()) {
				if (deleteFile.delete()) {

				} else {
					System.out.println("파일 삭제에 실패했습니다.");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}

		photoBoardRepository.deleteFiles(index);

		commentRepository.deleteAllComment(index);

		return photoBoardRepository.deletePhotoBoard(index);
	}

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @return : 조회한 게시물 1개의 내용을 photoBoardlistIndex 변수에 담고 ResponseEntity 를 사용하여
	 *         응답한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@Override
	public PhotoBoardGetDTO searchByPhotoIndex(Integer index) {

		List<FileVO> fv = photoBoardRepository.files(index);

		PhotoBoardVO vo = photoBoardRepository.searchByPhotoIndex(index);

		PhotoBoardGetDTO dto = new PhotoBoardGetDTO(); // vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Long(vo.getCreateDate()));
		dto.setLikeCount(vo.getLikeCount());
		dto.setFile(fv);
		return dto;
	}

	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#boardLikeCount(java.lang.Integer,
	 *      java.lang.Integer)
	 *
	 * @param index     해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 * 
	 * @return boardRepository의 boardLikeCount메소드 실행
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	@Override
	public Integer boardLikeCount(Integer index, Integer likeCount) {
		return boardRepository.boardLikeCount(index, likeCount);
	}

}
