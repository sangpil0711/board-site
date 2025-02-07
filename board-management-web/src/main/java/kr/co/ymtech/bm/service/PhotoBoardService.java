package kr.co.ymtech.bm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ymtech.bm.config.PathConfig;
import kr.co.ymtech.bm.controller.dto.ImageSetDTO;
import kr.co.ymtech.bm.controller.dto.PageDTO;
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
	private final PathConfig PathConfig;

	@Autowired
	public PhotoBoardService(IPhotoBoardRepository IphotoBoardRepository, ICommentRepository IcommentRepository,
			IBoardRepository IboardRepository, PathConfig PathConfig) {
		this.photoBoardRepository = IphotoBoardRepository;
		this.commentRepository = IcommentRepository;
		this.boardRepository = IboardRepository;
		this.PathConfig = PathConfig;
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
	public PhotoBoardPageDTO findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category) {

		List<PhotoBoardVO> photoBoardList = photoBoardRepository.findPhotoBoard(pageNumber, itemSize, searchType,
				keyword, category);
		Integer boardCount = photoBoardRepository.findCount(searchType, keyword, category);

		PhotoBoardPageDTO photoBoardPage = new PhotoBoardPageDTO();
		photoBoardPage.setTotalCount(boardCount);

		for (PhotoBoardVO vo : photoBoardList) {
			List<FileVO> filesForBoard = photoBoardRepository.photoBoardFile(vo.getIndex(), pageNumber, itemSize,
					searchType, keyword, category);

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
	public Integer savePhotoBoard(PhotoBoardDTO photo) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String originalFileName = null;
		String uniqueID = null;
		String filePath = null;
		FileVO boardFile = null;
		List<FileVO> boardFiles = new ArrayList<FileVO>();
		Integer lastBoardIndex = boardRepository.lastBoardIndex();
		Integer maxImageSize = photoBoardRepository.getMaxImageSize();

		// dto -> vo 변환
		PhotoBoardVO vo = new PhotoBoardVO();
		if (lastBoardIndex == null) {
			vo.setIndex(1);
		} else {
			vo.setIndex(lastBoardIndex + 1);
		}
		vo.setTitle(photo.getTitle());
		vo.setText(photo.getText());
		vo.setUserId(auth.getName());
		vo.setCategory(photo.getCategory());
		vo.setCreateDate(new Date().getTime());

		// 선택한 파일의 합이 허용된 최대 용량을 초과하면 동작
		if (photo.getTotalSize() > maxImageSize * 1024 * 1024) {
			throw new IllegalArgumentException("선택한 파일의 용량이 " + maxImageSize + "MB를 초과합니다. ");
		} else {
			for (MultipartFile file : photo.getFiles()) {
				originalFileName = file.getOriginalFilename();
				uniqueID = UUID.randomUUID().toString();
				filePath = Paths.get(PathConfig.getImagePath()).resolve(uniqueID + "_" + originalFileName).normalize()
						.toString();

				boardFile = new FileVO();
				boardFile.setFileId(uniqueID);
				boardFile.setBoardIndex(lastBoardIndex + 1);
				boardFile.setFilePath(PathConfig.getImagePath());
				boardFile.setFileName(originalFileName);
				boardFile.setFileSize(file.getSize());

				boardFiles.add(boardFile);

				// 업로드 되는 파일을 지정된 경로의 폴더에 저장
				try (InputStream input = file.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
					IOUtils.copy(input, output);
				} catch (IOException e) {
					System.out.println("파일 업로드 실패");
				}

			}
		}

		return photoBoardRepository.savePhotoBoard(vo, boardFiles);
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
	public Integer updatePhotoBoard(PhotoBoardUpdateDTO photo) {

		String originalFileName = null;
		String uniqueID = null;
		String filePath = null;
		FileVO boardFile = null;
		String deleteFilePath = null;
		File deleteFile = null;
		String deleteFileId = null;
		List<String> deleteFiles = photo.getDeleteFiles();
		List<FileVO> photoBoardFiles = new ArrayList<FileVO>();
		PhotoBoardVO vo = new PhotoBoardVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (photo.getUserId().equals(auth.getName())) {

			// dto -> vo 변환
			vo.setIndex(photo.getIndex());
			vo.setTitle(photo.getTitle());
			vo.setText(photo.getText());

			// 추가된 파일의 크기에 따라 반복하여 파일을 저장
			for (MultipartFile file : photo.getAddFiles()) {
				originalFileName = file.getOriginalFilename();
				uniqueID = UUID.randomUUID().toString();
				filePath = Paths.get(PathConfig.getImagePath()).resolve(uniqueID + "_" + originalFileName).normalize()
						.toString();

				boardFile = new FileVO();
				boardFile.setFileId(uniqueID);
				boardFile.setBoardIndex(photo.getIndex());
				boardFile.setFilePath(PathConfig.getImagePath());
				boardFile.setFileName(originalFileName);
				boardFile.setFileSize(file.getSize());

				photoBoardFiles.add(boardFile);

				// 추가된 파일을 지정된 경로의 폴더에 저장
				try (InputStream input = file.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
					IOUtils.copy(input, output);
				} catch (IOException e) {
					System.out.println("파일 저장 실패");
				}
			}

			// 삭제한 파일 리스트를 반복하며 파일 삭제
			for (String deleteFileName : deleteFiles) {
				deleteFilePath = Paths.get(PathConfig.getImagePath()).resolve(deleteFileName).normalize().toString();
				deleteFileId = deleteFileName.substring(0, deleteFileName.indexOf("_"));
				deleteFile = new File(deleteFilePath);
				deleteFile.delete();

				boardRepository.deleteFile(photo.getIndex(), deleteFileId);
			}
		}

		return photoBoardRepository.updatePhotoBoard(vo, photoBoardFiles);
	}

	/**
	 * Method : 사진게시물을 삭제하는 메소드
	 * 
	 * @param index:  사진게시물 번호
	 * @param userId: 작성자 아이디
	 * 
	 * @return : 사진게시물을 DB에서 삭제하고 성공하면 1, 실패하면 0을 photoboardlistDelete 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@Override
	public Integer deletePhotoBoard(Integer index, String userId) {

		List<FileVO> files = photoBoardRepository.files(index);
		String filePath = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (userId.equals(auth.getName()) || auth.getAuthorities().stream()
				.anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {

			// 삭제하려는 게시글에 업로드된 파일을 지정된 경로의 폴더에서 삭제
			for (FileVO file : files) {
				filePath = Paths.get(PathConfig.getImagePath()).resolve(file.getFileId() + "_" + file.getFileName())
						.normalize().toString();

				File deleteFile = new File(filePath);
				deleteFile.delete();
			}

			photoBoardRepository.deleteFiles(index);

			commentRepository.deleteAllComment(index);

			return photoBoardRepository.deletePhotoBoard(index);
		}

		return null;
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
		PhotoBoardGetDTO dto = new PhotoBoardGetDTO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer likeCount = boardRepository.boardLikeCount(index);
		Integer userLike = boardRepository.checkUserBoardLike(index, auth.getName());

		// vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Long(vo.getCreateDate()));
		dto.setLikeCount(likeCount);
		dto.setUserLike(userLike);
		dto.setUsername(vo.getUsername());
		dto.setFile(fv);

		return dto;
	}

	/**
	 * @Method updateBoardLike 게시글 추천 수를 업데이트하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#updateBoardLike(java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @return 게시글 추천 수를 업데이트하는 Repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	@Override
	public Integer updateBoardLike(Integer index) {
		boardRepository.updateBoardLike(index);
		return boardRepository.bestBoardLike(index);
	}

	/**
	 * @Method getImageSet 이미지 설정 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#getImageType()
	 *
	 * @return 이미지 유형과 이미지 최대 용량을 dto에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 25.
	 */
	@Override
	public ImageSetDTO getImageSet() {

		ImageSetDTO dto = new ImageSetDTO();

		dto.setImageType(photoBoardRepository.getImageType());
		dto.setImageMaxSize(photoBoardRepository.getMaxImageSize());

		return dto;
	}

	/**
	 * @Method getPageValue 페이지네이션에 필요한 값을 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#getPostPerPage()
	 * 
	 * @return 페이지당 표시되는 게시글 수와 한 번에 표시되는 최대 페이지 수를 page 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	@Override
	public PageDTO getPageValue() {

		PageDTO page = new PageDTO();
		page.setPostPerPage(photoBoardRepository.getPostPerPage());
		page.setMaxPage(photoBoardRepository.getMaxPage());

		return page;
	}

}
