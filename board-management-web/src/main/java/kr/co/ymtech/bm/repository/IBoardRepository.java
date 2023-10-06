package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;
import kr.co.ymtech.bm.repository.vo.PageVO;

/**
 * 일반게시판 IBoardRepository 인터페이스
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 박상현
 */
public interface IBoardRepository {
	
	/**
	 * 검색 조건에 따른 게시글 표시와 페이지네이션 구현
	 * 
	 * @param pageNumber : 게시판 페이지 번호
	 * @param itemSize : 게시판 페이지 당 게시글 개수
	 * @param searchType : 게시글 검색에 필요한 검색 유형
	 * @param keyword : 게시글 검색에 필요한 검색어
	 * 
	 * 작성자 : 황상필
	 * 작성일 : 2023.10.05
	 */
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword);
	
	/**
	 * 화면에 표시되는 게시글 수 조회
	 * 
	 * @param searchType : 게시글 검색에 필요한 검색 유형
	 * @param keyword : 게시글 검색에 필요한 검색어
	 * 
	 * 작성자 : 황상필
	 * 작성일 : 2023.10.05
	 */
	public List<PageVO> findAll(String searchType, String keyword);

	/**
	 * 게시물 정보를 저장
	 * 
	 * @param board : 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	public Integer saveBoard(BoardVO board);
	
	/**
	 * 게시물 정보를 저장
	 * 
	 * @param board : 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 */
	public Integer saveFile(FileVO file);

	/**
	 * 게시물 내용(text)을 수정
	 * 
	 * @param board : board는 클라이언트가 수정할 부분의 게시물 내용을 담고 있다.
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	public Integer updateBoard(BoardVO board);

	/**
	 * 게시물 정보를 삭제
	 * 
	 * @param board : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	public Integer deleteBoard(Integer index);

	/**
	 * 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회
	 * 
	 * @param board : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 ** 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	public BoardVO searchByIndex(Integer index);

}
