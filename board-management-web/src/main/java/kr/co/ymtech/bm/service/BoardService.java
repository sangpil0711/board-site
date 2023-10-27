package kr.co.ymtech.bm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.repository.IBoardRepository;
import kr.co.ymtech.bm.repository.ICommentRepository;
import kr.co.ymtech.bm.repository.IFileRepository;
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
	private final IFileRepository fileRepository;
//	private final static String SAVE_PATH = "C:/boardFile";


	@Autowired
	private BoardService(IBoardRepository IboardRepository, ICommentRepository IcommentRepository, IFileRepository IfileRepository) {
		this.boardRepository = IboardRepository;
		this.commentRepository = IcommentRepository;
		this.fileRepository = IfileRepository;
	}

	/**
	 * @Method findBoardPage 조건에 따른 게시글 정보를 DB에서 받아오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IBoardService#findBoardPage(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * 
	 * @return findPage 메소드와 findAll 메소드를 boardPageDTO 변수에 담아 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@Override
    public BoardPageDTO findBoardPage(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category) {
		
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
	 * @see kr.co.ymtech.bm.service.IBoardService#saveBoard(kr.co.ymtech.bm.controller.dto.BoardDTO, java.util.List, java.util.List)
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
	    BoardVO vo = new BoardVO(); 
	    vo.setTitle(board.getTitle());
	    vo.setText(board.getText());
	    vo.setCategory(board.getCategory());

	    if (board.getCreateDate() == null) {
	        vo.setCreateDate(new Date().getTime());
	    } else {
	        vo.setCreateDate(board.getCreateDate());
	    }

	    return boardRepository.saveBoard(vo);
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
	public Integer updateBoard(BoardGetDTO board) {

		BoardVO vo = new BoardVO(); // dto -> vo 변환
		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		
		return boardRepository.updateBoard(vo);
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
		
		commentRepository.deleteAllComment(index);
		
		fileRepository.resetFiles(index);

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
		dto.setCreateDate(new Date(vo.getCreateDate()));
		dto.setFile(fv);
		return dto;
	}
	
}
