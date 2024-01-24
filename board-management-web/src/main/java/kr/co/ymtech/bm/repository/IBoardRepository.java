package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 일반게시판 IBoardRepository 인터페이스
 * 
 * @author 박상현
 * @since 2023. 09. 18.
 */
public interface IBoardRepository {
	
	/**
	 * @Method findPage 검색 조건에 따른 게시글 표시와 페이지네이션 구현
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
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category);
	
	/**
	 * @Method findCount 화면에 표시되는 게시글 수 조회
	 *
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 *
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	public Integer findCount(String searchType, String keyword, Integer category);

	/**
	 * @Method saveBoard 게시물 정보를 저장
	 * 
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 * @param file 업로드된 첨부파일
	 * 
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	public Integer saveBoard(BoardVO board, List<FileVO> file);

	/**
	 * @Method updateBoard 게시물 내용(text)을 수정
	 * 
	 * @param board 클라이언트가 수정할 부분의 게시물 내용
	 * @param file 변경관 첨부파일
	 * 
	 * @author 황상필
	 * @since 2023. 11. 01.
	 */
	public Integer updateBoard(BoardVO board, List<FileVO> file);

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
	 * @Method files 게시물 번호에 해당되는 파일 정보를 조회
	 *
	 * @param index 해당 게시물 번호
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	public List<FileVO> files(Integer index);
	
	/**
	 * @Method lastBoard 마지막에 저장된 게시물의 번호를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2023. 10. 12.
	 */
	public Integer lastBoardIndex();
	
	/**
	 * @Method deleteFiles 게시물에 업로드된 파일을 전부 삭제하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	public Integer deleteFiles(Integer index);
	
	/**
	 * @Method deleteFile 게시물에 업로드된 파일을 개별로 삭제하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param fileId 파일 uuid
	 * 
	 * @author 황상필
	 * @since 2023. 10. 31.
	 */
	public Integer deleteFile(Integer index, String fileId);
	
	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	public Integer boardLikeCount(Integer index);
	
	/**
	 * @Method updateBoardLike 게시글 추천 수를 업데이트하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 *
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	public Integer updateBoardLike(Integer index);
	
	/**
	 * @Method checkUserBoardLike 로그인한 유저가 해당 게시글에 추천을 눌렀는지 확인하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param userId 로그인한 아이디
	 *
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	public Integer checkUserBoardLike(Integer index, String userId);
	
	/**
	 * @Method bestBoardLike 베스트 게시글 추천 수를 업데이트하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 *
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	public Integer bestBoardLike(Integer index);
	
	/**
	 * @Method bestBoard 추천 수가 많은 게시글을 반환하는 메소드
	 *
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	public List<BoardVO> bestBoard();
	
	/**
	 * @Method bestBoardFile 추천 수가 많은 게시글에 업로드된 파일 정보를 받아오는 메소드
	 *
	 * @param index 추천 수가 많은 게시글 번호
	 *
	 * @author 황상필
	 * @since 2023. 11. 08.
	 */
	public List<FileVO> bestBoardFile(Integer index);
	
	/**
	 * @Method getFileType 업로드 가능한 파일 유형을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public String getFileType();
	
	/**
	 * @Method getPostPerPage 페이지당 표시되는 게시글 수를 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public String getPostPerPage();
	
	/**
	 * @Method getMaxPage 한 번에 표시되는 최대 페이지 값을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public Integer getMaxPage();

}
