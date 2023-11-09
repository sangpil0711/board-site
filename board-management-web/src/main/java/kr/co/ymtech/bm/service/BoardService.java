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
	private final static String SAVE_PATH = "C:/boardFile";

	@Autowired
	private BoardService(IBoardRepository IboardRepository, ICommentRepository IcommentRepository) {
		this.boardRepository = IboardRepository;
		this.commentRepository = IcommentRepository;
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
	public void saveBoard(BoardDTO board) {

		List<FileVO> boardFiles = new ArrayList<FileVO>();

		// dto -> vo 변환
		BoardVO vo = new BoardVO();
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		vo.setCategory(board.getCategory());
		vo.setCreateDate(new Date().getTime());

		try {

			// 게시글 작성 시 업로드 되는 파일이 있으면 동작
			if (board.getFiles() != null) {
				for (int i = 0; i < board.getFiles().size(); i++) {
					MultipartFile files = board.getFiles().get(i);
					String originalFileName = files.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID + "_" + originalFileName;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
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

		boardRepository.saveBoard(vo, boardFiles);

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
	public void updateBoard(BoardUpdateDTO board) {

		List<FileVO> boardFiles = new ArrayList<FileVO>();

		// dto -> vo 변환
		BoardVO vo = new BoardVO();
		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());

		try {
			// 게시글 수정 시 추가된 파일이 있으면 동작
			if (board.getAddFiles() != null) {
				// 추가된 파일의 크기에 따라 반복하여 파일을 저장
				for (int i = 0; i < board.getAddFiles().size(); i++) {
					MultipartFile file = board.getAddFiles().get(i);
					String originalFileName = file.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID + "_" + originalFileName;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
					boardFile.setFilePath(SAVE_PATH);
					boardFile.setFileName(originalFileName);
					boardFile.setFileSize(file.getSize());

					boardFiles.add(boardFile);

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
			if (board.getDeleteFiles() != null) {

				// SAVE_PATH에 있는 파일 리스트를 전부 가져옴
				File dir = new File(SAVE_PATH);
				File files[] = dir.listFiles();

				List<String> deleteFileNames = board.getDeleteFiles();

				// 가져온 파일리스트에서 삭제된 파일의 UUID가 포함되어 있으면 지정된 경로의 폴더에서 삭제
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
					boardRepository.deleteFile(board.getIndex(), deleteFileName);
				}
			}
		} catch (Exception e) {
			System.out.println("파일 수정 실패");
		}

		boardRepository.updateBoard(vo, boardFiles);
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

		List<FileVO> files = boardRepository.files(index);

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

		// vo -> dto 변환
		BoardGetDTO dto = new BoardGetDTO();
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
	    List<BoardVO> voList = boardRepository.bestBoard();
	    List<BoardGetDTO> dtoList = new ArrayList<>();

	    for (BoardVO vo : voList) {
	    	BoardGetDTO dto = new BoardGetDTO();
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
