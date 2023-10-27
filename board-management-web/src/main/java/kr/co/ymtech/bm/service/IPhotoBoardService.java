package kr.co.ymtech.bm.service;

import java.util.List;

import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;

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
	List<PhotoBoardGetDTO> findPhotoBoard(Integer category);

	/**
	 * Method : 사진게시물을 저장하는 메소드 
	 * 
	 * @param  photo: 저장할 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	Integer savePhotoBoard(PhotoBoardDTO photo);

	/**
	 * Method : 사진게시물을 수정하는 메소드 
	 * 
	 * @param photo: 수정된 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	Integer updatePhotoBoard(PhotoBoardGetDTO photo);

	/**
	 * Method : 사진게시물을 삭제하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	Integer deletePhotoBoard(Integer index);

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	PhotoBoardGetDTO searchByPhotoIndex(Integer index);

}
