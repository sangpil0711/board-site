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
import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.BoardUpdateDTO;
import kr.co.ymtech.bm.controller.dto.FileSetDTO;
import kr.co.ymtech.bm.controller.dto.PageDTO;
import kr.co.ymtech.bm.repository.IBoardRepository;
import kr.co.ymtech.bm.repository.ICommentRepository;
import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 일반게시판 BoardService 클래스
 * 
 * @author 박상현
 * @since 2023. 09. 18.
 */
@Service
public class BoardService implements IBoardService {

	/**
	 * BoardService-BoardRepository 연결
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	private final IBoardRepository boardRepository;
	private final ICommentRepository commentRepository;
	private final PathConfig PathConfig;

	@Autowired
	private BoardService(IBoardRepository IboardRepository, ICommentRepository IcommentRepository,
			PathConfig PathConfig) {
		this.boardRepository = IboardRepository;
		this.commentRepository = IcommentRepository;
		this.PathConfig = PathConfig;
	}

	/**
	 * @Method findBoardPage 조건에 따른 게시글 정보를 DB에서 받아오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#findBoardPage(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize   게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword    게시판 검색어
	 * @param category   게시판 카테고리
	 * 
	 * @return findPage 메소드와 findAll 메소드를 boardPageDTO 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	@Override
	public BoardPageDTO findBoardPage(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category) {

		List<BoardVO> boardList = boardRepository.findPage(pageNumber, itemSize, searchType, keyword, category);

		Integer boardCount = boardRepository.findCount(searchType, keyword, category);

		BoardPageDTO boardPage = new BoardPageDTO();
		boardPage.setBoardList(boardList);
		boardPage.setTotalCount(boardCount);

		return boardPage;
	}

	/**
	 * @Method saveBoard 게시물과 파일 정보를 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#saveBoard(kr.co.ymtech.bm.controller.dto.BoardDTO,
	 *      java.util.List, java.util.List)
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물과 파일 정보
	 * 
	 * @return 저장할 게시물의 정보와 파일 정보를 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	@Override
	public Integer saveBoard(BoardDTO board) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String originalFileName = null;
		String uniqueID = null;
		String filePath = null;
		FileVO boardFile = null;
		List<FileVO> boardFiles = new ArrayList<FileVO>();
		Integer lastBoardIndex = boardRepository.lastBoardIndex();
		Integer maxFileSize = boardRepository.getMaxFileSize();
		
		// dto -> vo 변환
		BoardVO vo = new BoardVO();
		if (lastBoardIndex == null) {
			vo.setIndex(1);
		} else {
			vo.setIndex(lastBoardIndex + 1);
		}
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		vo.setUserId(auth.getName());
		vo.setCategory(board.getCategory());
		vo.setCreateDate(new Date().getTime());

		// 선택한 파일의 합이 허용된 최대 용량을 초과하면 동작
		if (board.getTotalSize() > maxFileSize * 1024 * 1024) {
			throw new IllegalArgumentException("선택한 파일의 용량이 " + maxFileSize + "MB를 초과합니다.");
		} 
		else {
			for (MultipartFile file : board.getFiles()) {
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

		return boardRepository.saveBoard(vo, boardFiles);
	}

	/**
	 * 
	 * @Method updateBoard 게시물 내용을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#updateBoard(kr.co.ymtech.bm.controller.dto.BoardGetDTO)
	 *
	 * @param board 수정한 게시물 내용을 포함
	 * 
	 * @return 업데이트 한 게시물 내용을 update 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2023. 11. 01.
	 */
	@Override
	public Integer updateBoard(BoardUpdateDTO board) {

		String originalFileName = null;
		String uniqueID = null;
		String filePath = null;
		FileVO boardFile = null;
		String deleteFilePath = null;
		File deleteFile = null;
		String deleteFileId = null;
		List<String> deleteFiles = board.getDeleteFiles();
		List<FileVO> boardFiles = new ArrayList<FileVO>();
		BoardVO vo = new BoardVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// 로그인한 유저 아이디가 작성자 아이디와 같으면 동작
		if (board.getUserId().equals(auth.getName())) {

			// dto -> vo 변환
			vo.setIndex(board.getIndex());
			vo.setTitle(board.getTitle());
			vo.setText(board.getText());

			// 추가된 파일의 크기에 따라 반복하여 파일을 저장
			for (MultipartFile file : board.getAddFiles()) {
				originalFileName = file.getOriginalFilename();
				uniqueID = UUID.randomUUID().toString();
				filePath = Paths.get(PathConfig.getImagePath()).resolve(uniqueID + "_" + originalFileName).normalize()
						.toString();

				boardFile = new FileVO();
				boardFile.setFileId(uniqueID);
				boardFile.setFilePath(PathConfig.getImagePath());
				boardFile.setFileName(originalFileName);
				boardFile.setFileSize(file.getSize());

				boardFiles.add(boardFile);

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

				boardRepository.deleteFile(board.getIndex(), deleteFileId);
			}
		}

		return boardRepository.updateBoard(vo, boardFiles);
	}

	/**
	 * @Method deleteBoard 게시물을 삭제하는 메소드
	 * 
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return Repository에서 함수를 실행하여 게시물과 관련된 데이터 삭제
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	@Override
	public Integer deleteBoard(Integer index, String userId) {

		String filePath = null;
		List<FileVO> files = boardRepository.files(index);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// 로그인한 유저 아이디가 작성자 아이디와 같거나 권한이 ROLE_ADMIN이면 동작
		if (userId.equals(auth.getName()) || auth.getAuthorities().stream()
				.anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {

			// 삭제하려는 게시글에 업로드된 파일을 지정된 경로의 폴더에서 삭제
			for (FileVO file : files) {
				filePath = Paths.get(PathConfig.getImagePath()).resolve(file.getFileId() + "_" + file.getFileName())
						.normalize().toString();

				File deleteFile = new File(filePath);
				deleteFile.delete();
			}

			boardRepository.deleteFiles(index);

			commentRepository.deleteAllComment(index);

			return boardRepository.deleteBoard(index);
		}

		return null;
	}

	/**
	 * @Method searchByIndex 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return 해당 번호의 게시물 정보를 res 변수에 담고 반환
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	@Override
	public BoardGetDTO searchByIndex(Integer index) {

		List<FileVO> fv = boardRepository.files(index);
		BoardVO vo = boardRepository.searchByIndex(index);
		BoardGetDTO dto = new BoardGetDTO();
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
	 * @see kr.co.ymtech.bm.service.IBoardService#updateBoardLike(java.lang.Integer)
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
	 * @Method bestBoard 추천 수가 많은 게시글을 반환하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#bestBoard()
	 *
	 * @return 게시글의 정보를 담은 dtoList 변수 반환
	 *
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	@Override
	public List<BoardGetDTO> bestBoard() {
		BoardGetDTO dto = null;
		List<BoardVO> voList = boardRepository.bestBoard();
		List<BoardGetDTO> dtoList = new ArrayList<>();

		// vo -> dto 변환
		for (BoardVO vo : voList) {
			dto = new BoardGetDTO();
			List<FileVO> files = boardRepository.bestBoardFile(vo.getIndex());
			dto.setIndex(vo.getIndex());
			dto.setTitle(vo.getTitle());
			dto.setText(vo.getText());
			dto.setUserId(vo.getUserId());
			dto.setCategory(vo.getCategory());
			dto.setCreateDate(vo.getCreateDate());
			dto.setLikeCount(vo.getLikeCount());
			dto.setUsername(vo.getUsername());
			dto.setFile(files);

			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * @Method getFileSet 파일 설정 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#getImageType()
	 *
	 * @return 파일 유형과 파일 최대 용량을 dto에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 25.
	 */
	@Override
	public FileSetDTO getFileSet() {
		FileSetDTO dto = new FileSetDTO();

		dto.setFileType(boardRepository.getFileType());
		dto.setFileMaxSize(boardRepository.getMaxFileSize());

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
		page.setPostPerPage(boardRepository.getPostPerPage());
		page.setMaxPage(boardRepository.getMaxPage());

		return page;
	}

}
