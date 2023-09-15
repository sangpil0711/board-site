package kr.co.ymtech.bm.service;

import java.util.List;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;

public interface IBoardService {

	// 모든 게시물 조회
	public List<BoardGetDTO> findAll();

	/**
	 * 
	 * @param board
	 * @return
	 * 
	 * 
	 */
	Integer saveBoard(BoardGetDTO board);

	// 게시물 수정
	Integer updateBoard(BoardGetDTO board);

	// 게시물 삭제
	Integer deleteBoard(Integer index);

	// 특정 인덱스의 게시물 조회
	public List<BoardGetDTO> searchByIndex(Integer index);

}
