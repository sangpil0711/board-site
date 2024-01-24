package kr.co.ymtech.bm.service;


import kr.co.ymtech.bm.controller.dto.PageDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardPageDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardUpdateDTO;

/**
 * 사진게시판 IPhotoBoardService 인터페이스
 * 
 * @author 박상현
 * @since  2023.10.24
 */
public interface IPhotoBoardService {

	/**
	 * Method : 사진게시물을 조회하는 메소드
	 * 
	 * @param category : 게시물 카테고리 번호
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public PhotoBoardPageDTO findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category);

	/**
	 * Method : 사진게시물을 저장하는 메소드 
	 * 
	 * @param  photo: 저장할 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public Integer savePhotoBoard(PhotoBoardDTO photo);

	/**
	 * Method : 사진게시물을 수정하는 메소드 
	 * 
	 * @param photo: 수정된 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public Integer updatePhotoBoard(PhotoBoardUpdateDTO photo);

	/**
	 * Method : 사진게시물을 삭제하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	public Integer deletePhotoBoard(Integer index, String userId);

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	public PhotoBoardGetDTO searchByPhotoIndex(Integer index);
	
	/**
	 * @Method updateBoardLike 게시글 추천 수를 업데이트하는 함수
	 *
	 * @param index 해당 게시글 번호
	 *
	 * @author 황상필
	 * @since 2024. 01. 03.
	 */
	public Integer updateBoardLike(Integer index);
	
	/**
	 * @Method getImageType 업로드 가능한 이미지 유형을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public String getImageType();
	
	/**
	 * @Method getPostPerPage 페이지네이션에 필요한 값을 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public PageDTO getPageValue();

}
