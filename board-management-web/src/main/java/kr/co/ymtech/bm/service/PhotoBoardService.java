package kr.co.ymtech.bm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardPageDTO;
import kr.co.ymtech.bm.repository.IBoardRepository;
import kr.co.ymtech.bm.repository.IPhotoBoardRepository;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시판 PhotoBoardService 클래스
 * 
 * @author 박상현
 * @since 2023. 10. 24.
 */
@Service
public class PhotoBoardService implements IPhotoBoardService {
	
	/**
	 * PhotoBoardService-PhotoBoardRepository 연결
	 * 
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	private final IPhotoBoardRepository photoBoardRepository;
	private final IBoardRepository boardRepository;

	@Autowired
	public PhotoBoardService(IPhotoBoardRepository IphotoBoardRepository, IBoardRepository IboardRepository) {
		this.photoBoardRepository = IphotoBoardRepository;
		this.boardRepository = IboardRepository;
	}

	/**
	 * @Method findPhotoBoard : 조건에 따른 게시글 정보를 DB에서 받아오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#findPhotoBoard(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber : 게시판 페이지 번호
	 * @param itemSize : 게시판 페이지 당 게시글 수
	 * @param searchType : 게시판 검색 유형
	 * @param keyword : 게시판 검색어
	 * @param category : 일반게시판,사진게시판 구분 카테고리 (일반게시판 0, 사진게시판 1)
	 * 
	 * @return findPage 메소드와 findAll 메소드를 boardPageDTO 변수에 담아 반환
	 *
	 * @author 박상현
	 * @since 2023. 10. 31.
	 */
	@Override
    public PhotoBoardPageDTO findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category) {
		
		List<PhotoBoardVO> boardList = photoBoardRepository.findPhotoBoard(pageNumber, itemSize, searchType, keyword, category);

	    Integer boardCount = photoBoardRepository.findCount(searchType, keyword, category);

	    PhotoBoardPageDTO photoBoardPage = new PhotoBoardPageDTO();
        photoBoardPage.setPhotoBoardList(boardList);
        photoBoardPage.setTotalCount(boardCount);

        return photoBoardPage;
    }

	/**
	 * @Method savePhotoBoard 사진게시판에 게시물을 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#savePhotoBoard(kr.co.ymtech.bm.controller.dto.PhotoBoardDTO, java.util.List, java.util.List)
	 *
	 * @param photo 클라이언트가 저장하려고 하는 게시물 정보
	 * 
	 * @return 저장하려는 게시물 정보
	 *
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	@Override
	public Integer savePhotoBoard(PhotoBoardDTO photo) {
		PhotoBoardVO vo = new PhotoBoardVO();
		vo.setTitle(photo.getTitle());
		vo.setText(photo.getText());
		vo.setCategory(photo.getCategory());

		if (photo.getCreateDate() == null) {
			vo.setCreateDate(new Date().getTime());
		} else {
			vo.setCreateDate(photo.getCreateDate());
		}

		Integer save = photoBoardRepository.savePhotoBoard(vo);

		return save;
	}

	/**
	 * 
	 * @Method updatePhotoBoard 사진게시판 게시물 내용을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IPhotoBoardService#updatePhotoBoard(kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO)
	 *
	 * @param board 클라이언트가 요청한 게시물 내용을 포함
	 * 
	 * @return 업데이트 한 게시물 내용을 update 변수에 담아 반환
	 *
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	@Override
	public Integer updatePhotoBoard(PhotoBoardGetDTO photo) {

		PhotoBoardVO vo = new PhotoBoardVO(); // dto -> vo 변환
		vo.setIndex(photo.getIndex());
		vo.setTitle(photo.getTitle());
		vo.setText(photo.getText());

		Integer update = photoBoardRepository.updatePhotoBoard(vo);

		return update;
	}

	/**
	 * Method : 사진게시물을 삭제하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @return : 사진게시물을 DB에서 삭제하고 성공하면 1, 실패하면 0을 photoboardlistDelete 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	@Override
	public Integer deletePhotoBoard(Integer index) {

//		commentRepository.deleteAllComment(index);

		return photoBoardRepository.deletePhotoBoard(index);
	}

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드 
	 * 
	 * @param index: 사진게시물 번호 
	 * 
	 * @return : 조회한 게시물 1개의 내용을 photoBoardlistIndex 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	@Override
	public PhotoBoardGetDTO searchByPhotoIndex(Integer index) {
		PhotoBoardVO vo = photoBoardRepository.searchByPhotoIndex(index);

		PhotoBoardGetDTO dto = new PhotoBoardGetDTO(); // vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Date(vo.getCreateDate()));
		dto.setLikeCount(vo.getLikeCount());
		return dto;
	}
	
	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#boardLikeCount(java.lang.Integer, java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 * 
	 * @return boardRepository의 boardLikeCount메소드 실행
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	@Override
	public Integer boardLikeCount(Integer index, Integer likeCount) {
		return boardRepository.boardLikeCount(index, likeCount);
	}

}
