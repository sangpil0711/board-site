package kr.co.ymtech.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardPageDTO;
import kr.co.ymtech.bm.service.BoardService;
import kr.co.ymtech.bm.service.IBoardService;
import kr.co.ymtech.bm.service.IPhotoBoardService;
import kr.co.ymtech.bm.service.PhotoBoardService;

/**
 * 사진게시판 Controller 클래스: 객체에 데이터를 담아 반환해야 하니 RestController 사용
 * 
 * @author 박상현
 * @since 2023.10.24
 */
@RestController
public class PhotoBoardController {

	/**
	 * Controller-Service 연결
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@Autowired
	private final IPhotoBoardService photoBoardService;
	private final IBoardService boardService;

	public PhotoBoardController(PhotoBoardService photoBoardService, BoardService boardService) {
		this.photoBoardService = photoBoardService;
		this.boardService = boardService;
	}

	/**
	 * 
	 * @Method findPhotoBoard 게시물에 저장되어 있는 정보를 조회 및 검색하는 메소드
	 *
	 * @param pageNumber : 사진게시판 페이지 번호
	 * @param itemSize   : 사진게시판 페이지 당 게시글 개수
	 * @param searchType : 사진게시판 검색에 필요한 검색 유형
	 * @param keyword    : 사진게시판 검색에 필요한 검색어
	 * @param category   : 일반게시판,사진게시판 구분 카테고리 (일반게시판 0, 사진게시판 1)
	 * 
	 * @return 저장되어 있는 정보를 모두 변수 photoBoardList에 담고 ResponseEntity 를 사용하여 응답한다. Http
	 *         상태코드는 HttpStatus.ok 로 성공상태 200을 나타내준다.
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@GetMapping("/photos")
	public ResponseEntity<PhotoBoardPageDTO> findPhotoBoard(@RequestParam Integer pageNumber,
			@RequestParam Integer itemSize, @RequestParam String searchType, @RequestParam String keyword,
			@RequestParam Integer category) {

		PhotoBoardPageDTO photoBoardList = photoBoardService.findPhotoBoard(pageNumber, itemSize, searchType, keyword,
				category);

		return new ResponseEntity<PhotoBoardPageDTO>(photoBoardList, HttpStatus.OK);
	}

	/**
	 * Method : 사진게시물을 저장하는 메소드
	 * 
	 * @param photo: 저장할 게시물 정보
	 * 
	 * @return : 사진게시물을 DB에 저장하고 성공하면 1, 실패하면 0을 photoBoardlistSave 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@PostMapping("/photos")
	public ResponseEntity<Integer> savePhotoBoard(@RequestBody PhotoBoardDTO photo) {

		Integer photoBoardlistSave = photoBoardService.savePhotoBoard(photo);

		return new ResponseEntity<Integer>(photoBoardlistSave, HttpStatus.OK);
	}

	/**
	 * Method : 사진게시물을 수정하는 메소드
	 * 
	 * @param photo: 수정된 게시물 정보
	 * @param index: 사진게시물 번호
	 * 
	 * @return : 사진게시물을 DB에 저장하고 성공하면 1, 실패하면 0을 photoBoardlistUpdate 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@PatchMapping("/photos/{index}")
	public ResponseEntity<Integer> updatePhotoBoard(@PathVariable Integer index, @RequestBody PhotoBoardGetDTO photo) {

		Integer photoBoardlistUpdate = photoBoardService.updatePhotoBoard(photo);

		return new ResponseEntity<Integer>(photoBoardlistUpdate, HttpStatus.OK);
	}

	/**
	 * Method : 사진게시물을 삭제하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @return : 사진게시물을 DB에서 삭제하고 성공하면 1, 실패하면 0을 photoboardlistDelete 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@DeleteMapping("/photos/{index}")
	public ResponseEntity<Integer> deletePhotoBoard(@PathVariable Integer index) {

		Integer photoboardlistDelete = photoBoardService.deletePhotoBoard(index);

		return new ResponseEntity<Integer>(photoboardlistDelete, HttpStatus.OK);
	}

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드
	 * 
	 * @param index: 사진게시물 번호
	 * 
	 * @return : 조회한 게시물 1개의 내용을 photoBoardlistIndex 변수에 담고 ResponseEntity 를 사용하여
	 *         응답한다.
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@GetMapping("/photos/{index}")
	public ResponseEntity<PhotoBoardGetDTO> searchByPhotoIndex(@PathVariable Integer index) {

		PhotoBoardGetDTO photoBoardlistIndex = photoBoardService.searchByPhotoIndex(index);

		return new ResponseEntity<PhotoBoardGetDTO>(photoBoardlistIndex, HttpStatus.OK);
	}

	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 * 
	 * @return 게시글의 번호와 추천 수를 boardLikeCount 변수에 담고 ResponseEntity 를 사용하여 응답
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	@PatchMapping("/photos/{index}/{likeCount}")
	public ResponseEntity<Integer> boardLikeCount(@PathVariable Integer index, @PathVariable Integer likeCount) {

		Integer boardLikeCount = boardService.boardLikeCount(index, likeCount);

		return new ResponseEntity<Integer>(boardLikeCount, HttpStatus.OK);
	}

}
