package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.FileVO;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시판 IPhotoBoardRepository 인터페이스
 * 
 * @author 박상현
 * @since 2023.10.24
 */
public interface IPhotoBoardRepository {

	/**
	 * @Method findPhotoBoard 검색 조건에 따른 게시글 표시와 페이지네이션 구현
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 * 
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
	public List<PhotoBoardVO> findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category);

	/**
	 * @Method findCount 화면에 표시되는 게시글 수 조회
	 *
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 *
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
	public Integer findCount(String searchType, String keyword, Integer category);

	/**
	 * Method : 사진게시물을 저장하는 메소드
	 * 
	 * @param photo: 저장할 게시물 정보
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	public Integer savePhotoBoard(PhotoBoardVO photo, List<FileVO> file);

	/**
	 * Method : 사진게시물을 수정하는 메소드
	 * 
	 * @param photo: 수정된 게시물 정보
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	public Integer updatePhotoBoard(PhotoBoardVO photo, List<FileVO> file);

	/**
	 * Method : 사진게시물을 삭제하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	public Integer deletePhotoBoard(Integer index);

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	public PhotoBoardVO searchByPhotoIndex(Integer index);
	
	/**
	 * @Method files 게시물 번호에 해당되는 파일 정보를 조회
	 *
	 * @param index 해당 게시물 번호
	 *
	 * @author 박상현
	 * @since 2023. 11. 01.
	 */
	public List<FileVO> files(Integer index);

	/**
	 * @Method deleteFiles 게시물에 업로드된 파일을 전부 삭제하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
	public Integer deleteFiles(Integer index);
	
	/**
	 * @Method deleteFile 게시물에 업로드된 파일을 개별로 삭제하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param fileId 파일 uuid
	 * 
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
	public Integer deleteFile(Integer index, String fileId);

	/**
	 * @Method photoBoardFile 사진 게시판 화면에 파일 정보를 가져오는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 * 
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
	public List<FileVO> photoBoardFile(Integer index, Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category);
	
	/**
	 * @Method getImageType 업로드 가능한 이미지 유형을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public String getImageType();
	
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
	
	/**
	 * @Method getMaxImageSize 최대 이미지 용량을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 1. 25.
	 */
	public Integer getMaxImageSize();

}
