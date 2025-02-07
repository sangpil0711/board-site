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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.BoardUpdateDTO;
import kr.co.ymtech.bm.controller.dto.FileSetDTO;
import kr.co.ymtech.bm.controller.dto.PageDTO;
import kr.co.ymtech.bm.service.BoardService;
import kr.co.ymtech.bm.service.IBoardService;

/**
 * 일반게시판 Controller 클래스 객체에 데이터를 담아 반환해야 하니 RestController 사용
 * 
 * @author 박상현
 * @since 2023. 09. 18.
 */
@RestController
@RequestMapping("/boards")
public class BoardController {

	/**
	 * Controller-Service 연결
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Autowired
	private final IBoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 
	 * @Method findBoardPage 게시물에 저장되어 있는 정보를 조회 및 검색하는 메소드
	 *
	 * @param pageNumber : 게시판 페이지 번호
	 * @param itemSize   : 게시판 페이지 당 게시글 개수
	 * @param searchType : 게시글 검색에 필요한 검색 유형
	 * @param keyword    : 게시글 검색에 필요한 검색어
	 * 
	 * @return 저장되어 있는 정보를 모두 변수 boardlist에 담고 ResponseEntity 를 사용하여 응답한다. Http
	 *         상태코드는 HttpStatus.ok 로 성공상태 200을 나타내준다.
	 * 
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@GetMapping(value = "")
	public ResponseEntity<BoardPageDTO> findBoardPage(@RequestParam Integer pageNumber, @RequestParam Integer itemSize,
			@RequestParam String searchType, @RequestParam String keyword, @RequestParam Integer category) {

		BoardPageDTO boardlist = boardService.findBoardPage(pageNumber, itemSize, searchType, keyword, category);

		return new ResponseEntity<BoardPageDTO>(boardlist, HttpStatus.OK);
	}

	/**
	 * @Method saveBoard 게시물 정보를 저장하는 메소드
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 *
	 * @author 황상필
	 * @since 2023. 10. 26.
	 */
	@PostMapping(value = "")
	public ResponseEntity<Integer> saveBoard(BoardDTO board) {

		Integer saveBoard = boardService.saveBoard(board);
		
		return new ResponseEntity<Integer>(saveBoard, HttpStatus.OK);
	}

	/**
	 * @Method updateBoard 게시물 내용을 수정하는 메소드
	 * 
	 * @param index 수정할 게시물 번호
	 * @param board 수정된 게시물 정보
	 * 
	 * @author 황상필
	 * @since 2023. 11. 01.
	 */
	@PatchMapping(value = "{index}")
	public ResponseEntity<Integer> updateBoard(@PathVariable Integer index, BoardUpdateDTO board) {
		
		Integer updateBoard = boardService.updateBoard(board);
		
		return new ResponseEntity<Integer>(updateBoard, HttpStatus.OK);
	}

	/**
	 * @Method deleteBoard 게시물을 삭제하는 메소드
	 * 
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return 게시물을 삭제 후 boardlistDelete 변수에 담고 ResponseEntity 를 사용하여 응답
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@DeleteMapping(value = "{index}")
	public ResponseEntity<Integer> deleteBoard(@PathVariable Integer index, @RequestParam String userId) {

		Integer boardlistDelete = boardService.deleteBoard(index, userId);

		return new ResponseEntity<Integer>(boardlistDelete, HttpStatus.OK);
	}

	/**
	 * @Method searchByIndex 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return 해당 번호의 게시물 정보를 boardlistIndex 변수에 담고 ResponseEntity 를 사용하여 응답
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@GetMapping(value = "{index}")
	public ResponseEntity<BoardGetDTO> searchByIndex(@PathVariable Integer index) {

		BoardGetDTO boardlistIndex = boardService.searchByIndex(index);

		return new ResponseEntity<BoardGetDTO>(boardlistIndex, HttpStatus.OK);
	}

	/**
	 * @Method bestBoard 추천 수가 많은 게시글을 반환하는 메소드
	 *
	 * @return 게시글의 정보를 bestBoard 변수에 담고 ResponseEntity 를 사용하여 응답
	 *
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	@GetMapping(value = "best")
	public ResponseEntity<List<BoardGetDTO>> bestBoard() {

		List<BoardGetDTO> bestBoard = boardService.bestBoard();

		return new ResponseEntity<List<BoardGetDTO>>(bestBoard, HttpStatus.OK);
	}
	
	/**
	 * @Method updateBoardLike 게시글 추천 수를 업데이트하는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @return 게시글의 추천 수를 updateBoardLike 변수에 담고 ResponseEntity 를 사용하여 응답
	 *
	 * @author 황상필
	 * @since 2024. 01. 02.
	 */
	@PatchMapping(value = "like")
	public ResponseEntity<Integer> updateBoardLike(@RequestParam Integer index) {
		
		Integer updateBoardLike = boardService.updateBoardLike(index);
		
		return new ResponseEntity<Integer>(updateBoardLike, HttpStatus.OK);
	}
	
	/**
	 * @Method getFileSet 파일 설정 정보를 가져오는 메소드
	 *
	 * @return ResponseEntity를 사용하여 fileSet 변수를 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 25.
	 */
	@GetMapping(value = "fileSet")
	public ResponseEntity<FileSetDTO> getFileSet() {
		
		FileSetDTO fileSet = boardService.getFileSet();
		
		return new ResponseEntity<FileSetDTO>(fileSet, HttpStatus.OK);
	}
	
	/**
	 * @Method getPageValue 페이지네이션에 필요한 값을 가져오는 메소드
	 *
	 * @return ResponseEntity를 사용하여 postPerPage 변수를 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	@GetMapping(value = "pageValue")
	public ResponseEntity<PageDTO> getPageValue() {
		
		PageDTO postPerPage = boardService.getPageValue();
		
		return new ResponseEntity<PageDTO>(postPerPage, HttpStatus.OK);
	}

}
