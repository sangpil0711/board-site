package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;

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
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	public BoardPageDTO findBoardPage(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category);

	/**
	 * @Method saveBoard 게시물 정보를 저장하는 메소드
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	public void saveBoard(BoardDTO board);

	/**
	 * @Method updateBoard 게시물 내용을 수정하는 메소드
	 * 
	 * @param board 클라이언트가 수정할 부분의 게시물 내용을 담고 있다.
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public Integer updateBoard(BoardGetDTO board);

	/**
	 * @Method deleteBoard 게시물을 삭제하는 메소드
	 * 
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public Integer deleteBoard(Integer index);

	/**
	 * @Method searchByIndex 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public BoardGetDTO searchByIndex(Integer index);

}
