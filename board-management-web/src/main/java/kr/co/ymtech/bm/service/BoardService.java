package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.repository.CommentRepository;
import kr.co.ymtech.bm.repository.IBoardRepository;
import kr.co.ymtech.bm.repository.ICommentRepository;
import kr.co.ymtech.bm.repository.vo.BoardVO;

/**
 * 일반게시판 BoardService 클래스
 * 
 *  작성일 : 2023.09.18
 *  작성자 : 박상현
 */
@Service
public class BoardService implements IBoardService {

	/**
	 * BoardService-BoardRepository 연결
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	private final IBoardRepository boardRepository;
	private final ICommentRepository commentRepository;


	@Autowired
	private BoardService(IBoardRepository IboardRepository,ICommentRepository IcommentRepository) {
		this.boardRepository = IboardRepository;
		this.commentRepository = IcommentRepository;
	}

	/**
	 * Method : 게시물에 저장되어 있는 정보를 모두 조회하는 메소드
	 * 
	 * @return : findAll 변수에 vo -> dto 변환된 값들을 담아 반환
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
    public BoardPageDTO findBoardPage(Integer pageNumber, Integer pageSize, String searchType, String keyword) {
		
		List<BoardVO> list = boardRepository.findPage(pageNumber, pageSize, searchType, keyword);

	    List<PageVO> boardCount = boardRepository.findAll(searchType, keyword);
        
        List<BoardGetDTO> findPage = new ArrayList<>(); // vo -> dto 변환
		for (BoardVO vo : list) {
			BoardGetDTO tmp = new BoardGetDTO();
			tmp.setIndex(vo.getIndex());
			tmp.setTitle(vo.getTitle());
			tmp.setText(vo.getText());
			tmp.setUserId(vo.getUserId());
			tmp.setCategory(vo.getCategory());
			tmp.setCreateDate(new Date(vo.getCreateDate()));
			findPage.add(tmp);
		}

        BoardPageDTO boardPageDTO = new BoardPageDTO();
        boardPageDTO.setBoardList(list);
        boardPageDTO.setPageList(boardCount);

        return boardPageDTO;
    }

	/**
	 * Method : 게시물 정보를 저장하는 메소드
	 * 
	 * @param : board는 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 * 
	 * @return :게시물을 DB에 저장하고 성공하면 1, 실패하면 0을 save 변수에 담아 반환한다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public Integer saveBoard(BoardDTO board) {

		BoardVO vo = new BoardVO(); // dto -> vo 변환
//		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
//		vo.setUserId(board.getUserId()); 
//		vo.setCategory(board.getCategory());

		if (board.getCreateDate() == null) {
			vo.setCreateDate(new Date().getTime());
		} else {
			vo.setCreateDate(board.getCreateDate());
		}

		Integer save = boardRepository.saveBoard(vo);

		return save;
	}

	/**
	 * Method : 게시물 내용(text)을 수정 하는 메소드
	 * 
	 * @param : board는 클라이언트가 요청한 게시물 내용을 담고 있다.
	 * 
	 * @return : 업데이트 한 게시물 내용을 update 변수에 담고 반환한다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public Integer updateBoard(BoardGetDTO board) {

		BoardVO vo = new BoardVO(); // dto -> vo 변환
		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());

		Integer update = boardRepository.updateBoard(vo);

		return update;
	}

	/**
	 * Method : 게시물을 삭제하는 메소드
	 * 
	 * @param : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return : Repository 에서 deleteBoard 함수를 실행시킨다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public Integer deleteBoard(Integer index) {
		
		commentRepository.deleteAllComment(index);

		return boardRepository.deleteBoard(index);
	}

	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return : 해당 번호의 게시물 정보를 res 변수에 담고 반환한다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public BoardGetDTO searchByIndex(Integer index) {
		BoardVO vo = boardRepository.searchByIndex(index);

		BoardGetDTO dto = new BoardGetDTO(); // vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Date(vo.getCreateDate()));
		return dto;
	}


}
