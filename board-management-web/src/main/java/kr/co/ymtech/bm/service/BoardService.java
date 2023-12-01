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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kr.co.ymtech.bm.config.PathConfig;
import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.BoardUpdateDTO;
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

		String originalFileName = null;
		String uniqueID = null;
		String filePath = null;
		FileVO boardFile = null;
		List<FileVO> boardFiles = new ArrayList<FileVO>();
		Integer lastBoardIndex = boardRepository.lastBoardIndex();
		
		System.out.println(board.getText());

		// dto -> vo 변환
		BoardVO vo = new BoardVO();
		vo.setIndex(lastBoardIndex + 1);
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		vo.setCategory(board.getCategory());
		vo.setCreateDate(new Date().getTime());

		// 게시글 작성 시 선택된 파일을 업로드
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

		// dto -> vo 변환
		BoardVO vo = new BoardVO();
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
	public Integer deleteBoard(Integer index) {

		String filePath = null;
		List<FileVO> files = boardRepository.files(index);

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

		// vo -> dto 변환
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
			dto.setFile(files);

			dtoList.add(dto);
		}
		return dtoList;
	}

}
