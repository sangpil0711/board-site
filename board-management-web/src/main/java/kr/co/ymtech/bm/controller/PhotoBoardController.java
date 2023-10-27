package kr.co.ymtech.bm.controller;

import java.util.List;

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
import kr.co.ymtech.bm.service.IPhotoBoardService;
import kr.co.ymtech.bm.service.PhotoBoardService;

/**
 * 사진게시판 Controller 클래스: 객체에 데이터를 담아 반환해야 하니 RestController 사용
 * 
 * @author 박상현
 * @since  2023.10.24
 */
@RestController
public class PhotoBoardController {

	/**
	 * Controller-Service 연결
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	@Autowired
	private final IPhotoBoardService photoBoardService;

	public PhotoBoardController(PhotoBoardService photoBoardService) {
		this.photoBoardService = photoBoardService;
	}

	/**
	 * Method : 사진게시물을 조회하는 메소드 
	 * 
	 * @param category : 게시물 카테고리 번호
	 * 
	 * @return : 조회한 게시물 내용을 photoBoardList 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	@GetMapping("/photos")
	public ResponseEntity<List<PhotoBoardGetDTO>> findPhotoBoard(@RequestParam Integer category) {

		List<PhotoBoardGetDTO> photoBoardList = photoBoardService.findPhotoBoard(category);

		return new ResponseEntity<List<PhotoBoardGetDTO>>(photoBoardList, HttpStatus.OK);
	}

	/**
	 * Method : 사진게시물을 저장하는 메소드 
	 * 
	 * @param  photo: 저장할 게시물 정보
	 * 
	 * @return : 사진게시물을 DB에 저장하고 성공하면 1, 실패하면 0을 photoBoardlistSave 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.10.24
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
	 * @since  2023.10.24
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
	 * @since  2023.10.25
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
	 * @return : 조회한 게시물 1개의 내용을 photoBoardlistIndex 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 * 
	 * @author 박상현
	 * @since  2023.10.25
	 */
	@GetMapping("/photos/{index}")
	public ResponseEntity<PhotoBoardGetDTO> searchByPhotoIndex(@PathVariable Integer index) {

		PhotoBoardGetDTO photoBoardlistIndex = photoBoardService.searchByPhotoIndex(index);

		return new ResponseEntity<PhotoBoardGetDTO>(photoBoardlistIndex, HttpStatus.OK);
	}

}
