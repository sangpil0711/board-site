package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시판 IPhotoBoardRepository 인터페이스
 * 
 * @author 박상현
 * @since  2023.10.24
 */
public interface IPhotoBoardRepository {

	/**
	 * Method : 사진게시물을 조회하는 메소드
	 * 
	 * @param category : 게시물 카테고리 번호
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public List<PhotoBoardVO> findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category);

	
	public Integer findCount(String searchType, String keyword, Integer category);
	/**
	 * Method : 사진게시물을 저장하는 메소드 
	 * 
	 * @param  photo: 저장할 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void savePhotoBoard(PhotoBoardVO photo,  List<FileVO> file);

	/**
	 * Method : 사진게시물을 수정하는 메소드 
	 * 
	 * @param photo: 수정된 게시물 정보
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void updatePhotoBoard(PhotoBoardVO photo,  List<FileVO> file);

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
	public PhotoBoardVO searchByPhotoIndex(Integer index);
	
	
	public List<FileVO> files(Integer index);
	

	public PhotoBoardVO lastPhotoBoard();

	
	public Integer deleteFiles(Integer index);
	

	public Integer deleteFile(Integer index, String fileId);


}
