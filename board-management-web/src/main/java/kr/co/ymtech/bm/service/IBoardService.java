package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;

/**
 * 일반게시판 Service 인터페이스
 */
public interface IBoardService {
	
	public BoardPageDTO findBoardPage(Integer pageNumber, Integer pageSize, String searchType, String keyword);

	/**
	 * 게시물 정보를 저장
	 * 
	 * @param board : 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 */
	public Integer saveBoard(BoardDTO board);

	/**
	 * 게시물 내용(text)을 수정
	 * 
	 * @param board : board는 클라이언트가 수정할 부분의 게시물 내용을 담고 있다.
	 */
	public Integer updateBoard(BoardGetDTO board);

	/**
	 * 게시물 정보를 삭제
	 * 
	 * @param board : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 */
	public Integer deleteBoard(Integer index);

	/**
	 * 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회
	 * 
	 * @param board : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 */
	public BoardGetDTO searchByIndex(Integer index);
	
	
	public Integer saveComment(CommentDTO comment);
	
	
	public CommentGetDTO findComment(Integer boardIndex, Integer commentIndex);
	
	
	public Integer updateComment(CommentGetDTO comment);

	
	public Integer deleteComment(Integer index);

	


}
