package kr.co.ymtech.bm.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.repository.BoardVO;
import kr.co.ymtech.bm.repository.IBoardRepository;

/**
 * 일반게시판 Service 클래스
 */
@Service
public class BoardService implements IBoardService {

	/**
	 * Service-Repository 연결
	 */
	private final IBoardRepository boardRepository;

	@Autowired
	private BoardService(IBoardRepository IboardRepository) {
		this.boardRepository = IboardRepository;
	}

	/**
	 * 게시물에 저장되어 있는 정보를 모두 조회하는 메소드
	 */
	@Override
	public List<BoardGetDTO> findAll() {

		List<BoardVO> list = boardRepository.findAll();

		List<BoardGetDTO> findAll = new ArrayList<>(); // vo -> dto 변환
		for (BoardVO vo : list) {
			BoardGetDTO tmp = new BoardGetDTO();
			tmp.setIndex(vo.getIndex());
			tmp.setTitle(vo.getTitle());
			tmp.setText(vo.getText());
			tmp.setUserId(vo.getUserId());
			tmp.setCategory(vo.getCategory());
			tmp.setCreateDate(new Date(vo.getCreateDate()));
			findAll.add(tmp);
		}
		return findAll;
	}

	/**
	 * 게시물 정보를 저장하는 메소드
	 */
	@Override
	public Integer boardSave(BoardGetDTO board) {

		BoardVO vo = new BoardVO(); // dto -> vo 변환
		vo.setIndex(board.getIndex());
		vo.setTitle(board.getTitle());
		vo.setText(board.getText());
		vo.setUserId(board.getUserId());
		vo.setCategory(board.getCategory());
		vo.setCreateDate(new Date().getTime());

		Integer save = boardRepository.boardSave(vo);

		return save;
	}

	/**
	 * 게시물 내용(text)을 수정 하는 메소드
	 */
	@Override
	public Integer boardUpdate(BoardGetDTO board) {

		BoardVO vo = new BoardVO(); // dto -> vo 변환
		vo.setIndex(board.getIndex());
		vo.setText(board.getText());

		Integer update = boardRepository.boardUpdate(vo);

		return update;
	}

	/**
	 * 게시물을 삭제하는 메소드
	 */
	@Override
	public Integer boardDelete(Integer index) {

		return boardRepository.boardDelete(index);
	}

	/**
	 * 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 */
	@Override
	public List<BoardGetDTO> indexSearch(Integer index) {
		List<BoardVO> list = boardRepository.indexSearch(index);

		List<BoardGetDTO> res = new ArrayList<>(); // vo -> dto 변환
		for (BoardVO vo : list) {
			BoardGetDTO tmp = new BoardGetDTO();
			tmp.setIndex(vo.getIndex());
			tmp.setTitle(vo.getTitle());
			tmp.setText(vo.getText());
			tmp.setUserId(vo.getUserId());
			tmp.setCategory(vo.getCategory());
			tmp.setCreateDate(new Date(vo.getCreateDate()));
			res.add(tmp);
		}
		return res;
	}

}
