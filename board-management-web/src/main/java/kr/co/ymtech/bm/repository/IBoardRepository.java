package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;

/**
 * 일반게시판 IBoardRepository 인터페이스
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 박상현
 */
public interface IBoardRepository {

	/**
	 * 게시물에 저장되어 있는 정보를 모두 조회
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	public List<BoardVO> findAll();

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
