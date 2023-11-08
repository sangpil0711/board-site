package kr.co.ymtech.bm.service;


import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardPageDTO;

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
	public Integer updatePhotoBoard(PhotoBoardGetDTO photo);

	/**
	 * Method : 사진게시물을 삭제하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	public Integer deletePhotoBoard(Integer index);

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
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	public Integer boardLikeCount(Integer index, Integer likeCount);

}
