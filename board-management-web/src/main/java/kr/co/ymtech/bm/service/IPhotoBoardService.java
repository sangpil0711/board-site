package kr.co.ymtech.bm.service;


import kr.co.ymtech.bm.controller.dto.BoardUpdateDTO;
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
	public void savePhotoBoard(PhotoBoardDTO photo);

	/**
	 * Method : 사진게시물을 수정하는 메소드 
	 * 
	 * @param photo: 수정된 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void updatePhotoBoard(PhotoBoardUpdateDTO photo);

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

}
