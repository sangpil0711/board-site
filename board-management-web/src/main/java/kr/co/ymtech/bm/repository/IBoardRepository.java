package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 일반게시판 IBoardRepository 인터페이스
 * 
 * @author 박상현
 * @since  2023.09.18
 */
public interface IBoardRepository {
	
	/**
	 * @Method findPage 검색 조건에 따른 게시글 표시와 페이지네이션 구현
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword);
	
	/**
	 * @Method findAll 화면에 표시되는 게시글 수 조회
	 *
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	public Integer findCount(String searchType, String keyword);

	/**
	 * @Method saveBoard 게시물 정보를 저장
	 * 
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public Integer saveBoard(BoardVO board);

	/**
	 * @Method updateBoard 게시물 내용(text)을 수정
	 * 
	 * @param board 클라이언트가 수정할 부분의 게시물 내용
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public Integer updateBoard(BoardVO board);

	/**
	 * @Method deleteBoard 게시물 정보를 삭제
	 * 
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public Integer deleteBoard(Integer index);

	/**
	 * @Method searchByIndex 게시물 번호를 이용하여 해당 번호의 정보를 조회
	 * 
	 * @param index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 ** 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	public BoardVO searchByIndex(Integer index);
	
	/**
	 * 
	 * @Method files 게시물 번호에 해당되는 파일 정보를 조회
	 *
	 * @param index 해당 게시물 번호
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	public List<FileVO> files(Integer index);
	
	/**
	 * @Method saveFile 파일 정보를 저장하는 메소드
	 *
	 * @param file DB에 보낼 파일 데이터
	 *
	 * @author 황상필
	 * @since 2023. 10. 11.
	 */
	public Integer saveFile(FileVO file);
	
	/**
	 * @Method lastBoard 마지막에 저장된 게시물의 번호를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2023. 10. 12.
	 */
	public List<BoardVO> lastBoard();

}
