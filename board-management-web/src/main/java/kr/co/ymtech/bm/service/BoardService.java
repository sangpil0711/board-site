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
	 * 
	 * @return findPage 메소드와 findAll 메소드를 boardPageDTO 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
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

		BoardVO lastBoard = boardRepository.lastBoard();
		List<FileVO> boardFiles = new ArrayList<FileVO>();

		BoardVO vo = new BoardVO();
		vo.setIndex(lastBoard.getIndex() + 1);
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		vo.setCategory(board.getCategory());

		if (board.getCreateDate() == null) {
			vo.setCreateDate(new Date().getTime());
		} else {
			vo.setCreateDate(board.getCreateDate());
		}
		try {

			if (board.getFiles() != null) {
				for (int i = 0; i < board.getFiles().size(); i++) {
					MultipartFile files = board.getFiles().get(i);
					String originalFileName = files.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
					boardFile.setBoardIndex(lastBoard.getIndex() + 1);
					boardFile.setFilePath(filePath);
					boardFile.setFileName(originalFileName);
					boardFile.setFileSize(files.getSize());

					boardFiles.add(boardFile);

					try (InputStream input = files.getInputStream();
							OutputStream output = new FileOutputStream(filePath)) {
						IOUtils.copy(input, output);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		boardRepository.saveBoard(vo, boardFiles);

	}

	/**
	 * 
	 * @Method updateBoard 게시물 내용을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#updateBoard(kr.co.ymtech.bm.controller.dto.BoardGetDTO)
	 *
	 * @param board 클라이언트가 요청한 게시물 내용을 포함
	 * 
	 * @return 업데이트 한 게시물 내용을 update 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 24.
	 */
	@Override
	public void updateBoard(BoardUpdateDTO board) {

		List<FileVO> boardFiles = new ArrayList<FileVO>();

		BoardVO vo = new BoardVO(); // dto -> vo 변환
		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());

		try {
			if (board.getAddFiles() != null) {
				for (int i = 0; i < board.getAddFiles().size(); i++) {
					MultipartFile file = board.getAddFiles().get(i);
					String originalFileName = file.getOriginalFilename();
					String uniqueID = UUID.randomUUID().toString();
					String filePath = SAVE_PATH + "/" + uniqueID;

					FileVO boardFile = new FileVO();
					boardFile.setFileId(uniqueID);
					boardFile.setBoardIndex(board.getIndex());
					boardFile.setFilePath(filePath);
					boardFile.setFileName(originalFileName);
					boardFile.setFileSize(file.getSize());

					boardFiles.add(boardFile);

					try (InputStream input = file.getInputStream();
							OutputStream output = new FileOutputStream(filePath)) {
						IOUtils.copy(input, output);
					}
				}
			}
			
			if (board.getDeleteFiles() != null) {
			    File dir = new File(SAVE_PATH);
			    File files[] = dir.listFiles();

			    List<String> deleteFileNames = board.getDeleteFiles();
			    
			    for (String deleteFileName : deleteFileNames) {
			        for (File file : files) {
			            if (file.getName().equals(deleteFileName)) {
			                if (file.exists()) {
			                    if (file.delete()) {
			                        System.out.println("파일 삭제 성공" );
			                    } else {
			                        System.out.println("파일 삭제 실패");
			                    }
			                } else {
			                    System.out.println("파일이 없음");
			                }
			            }
			        }
			        boardRepository.deleteFile(board.getIndex(), deleteFileName);
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
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

		for (FileVO file : files) {
			String fileLocation = file.getFilePath();

			File deleteFile = new File(fileLocation);

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
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Override
	public BoardGetDTO searchByIndex(Integer index) {

		List<FileVO> fv = boardRepository.files(index);

		BoardVO vo = boardRepository.searchByIndex(index);

		BoardGetDTO dto = new BoardGetDTO(); // vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Long(vo.getCreateDate()));
		dto.setFile(fv);
		return dto;
	}

}
