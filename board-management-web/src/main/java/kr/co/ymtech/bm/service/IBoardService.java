package kr.co.ymtech.bm.service;

import java.util.List;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.BoardUpdateDTO;

/**
 * 일반게시판 IboardService 인터페이스
 * 
 * @author 박상현
 * @since  2023.09.18
 */
public interface IBoardService {
	
	/**
	 * @Method findBoardPage 조건에 따른 게시글 정보를 조회하는 메소드
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 *
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	public BoardPageDTO findBoardPage(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category);

	/**
	 * @Method saveBoard 게시물 정보를 저장하는 메소드
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 *
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	public void saveBoard(BoardDTO board);

	/**
	 * @Method updateBoard 게시물 내용을 수정하는 메소드
	 * 
	 * @param board 클라이언트가 수정할 부분의 게시물 내용을 담고 있다.
	 * 
	 * @author 황상필
	 * @since 2023. 11. 01.
	 */
	public void updateBoard(BoardUpdateDTO board);

	/**
	 * @Method deleteBoard 게시물을 삭제하는 메소드
	 * 
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	public Integer deleteBoard(Integer index);

	/**
	 * @Method searchByIndex 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	public BoardGetDTO searchByIndex(Integer index);
	
	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	public Integer boardLikeCount(Integer index, Integer likeCount);
	
	/**
	 * @Method bestBoard 추천 수가 많은 게시글을 반환하는 메소드
	 *
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	public List<BoardGetDTO> bestBoard();

}
