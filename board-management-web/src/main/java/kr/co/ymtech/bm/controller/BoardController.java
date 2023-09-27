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
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.BoardPageDTO;
import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;
import kr.co.ymtech.bm.service.BoardService;
import kr.co.ymtech.bm.service.IBoardService;

/**
 * 일반게시판 Controller 클래스 객체에 데이터를 담아 반환해야 하니 RestController 사용
 */
@RestController
public class BoardController {

	/**
	 * Controller-Service 연결
	 */
	@Autowired
	private final IBoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * Method : 게시물에 저장되어 있는 정보를 모두 조회하는 메소드
	 * 
	 * @return :저장되어 있는 정보를 모두 변수 boardlist에 담고 ResponseEntity 를 사용하여 응답한다. Http
	 *         상태코드는 HttpStatus.ok 로 성공상태 200을 나타내준다.
	 */
	@GetMapping(value = "/boards")
	public ResponseEntity<BoardPageDTO> findBoardPage(Integer pageNumber, Integer pageSize, Integer totalCount) {

		BoardPageDTO boardlist = boardService.findBoardPage(pageNumber, pageSize, totalCount);

		return new ResponseEntity<BoardPageDTO>(boardlist, HttpStatus.OK);
	}

	/**
	 * Method : 게시물 정보를 저장하는 메소드
	 * 
	 * @param : board는 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 * 
	 * @return : 게시물을 DB에 저장하고 성공하면 1, 실패하면 0을 boardlistSave 변수에 담아 반환한다.
	 */
	@PostMapping(value = "/boards")
	public ResponseEntity<Integer> saveBoard(@RequestBody BoardDTO board) {

		Integer boardlistSave = boardService.saveBoard(board);

		return new ResponseEntity<Integer>(boardlistSave, HttpStatus.OK);
	}

	/**
	 * Method : 게시물 내용(text)을 수정 하는 메소드
	 * 
	 * @param : board는 클라이언트가 요청한 게시물 내용을 담고 있다.
	 * 
	 * @return : 업데이트 한 게시물 내용을 boardlistUpdate 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 */
	@PatchMapping("/boards/{index}")
	public ResponseEntity<Integer> updateBoard(@PathVariable Integer index, @RequestBody BoardGetDTO board) {

		Integer boardlistUpdate = boardService.updateBoard(board);

		return new ResponseEntity<Integer>(boardlistUpdate, HttpStatus.OK);
	}

	/**
	 * Method : 게시물을 삭제하는 메소드
	 * 
	 * @param : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return : 게시물을 삭제 후 boardlistDelete 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 */
	@DeleteMapping("/boards/{index}")
	public ResponseEntity<Integer> deleteBoard(@PathVariable Integer index) {

		Integer boardlistDelete = boardService.deleteBoard(index);

		return new ResponseEntity<Integer>(boardlistDelete, HttpStatus.OK);
	}

	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return : 해당 번호의 게시물 정보를 boardlistIndex 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 */
	@GetMapping("/boards/{index}")
	public ResponseEntity<BoardGetDTO>searchByIndex(@PathVariable Integer index) {

		BoardGetDTO boardlistIndex = boardService.searchByIndex(index);

		return new ResponseEntity<BoardGetDTO>(boardlistIndex, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/comment")
	public ResponseEntity<Integer> saveComment(@RequestBody CommentDTO comment){
		
		Integer boardCommentSave = boardService.saveComment(comment);
		
		return new ResponseEntity<Integer>(boardCommentSave, HttpStatus.OK);
	}
	
	@GetMapping("/comments/{boardIndex}")
	public ResponseEntity<CommentGetDTO> findComment(@PathVariable Integer boardIndex, Integer commentIndex) {

		CommentGetDTO boardCommentIndex = boardService.findComment(boardIndex,commentIndex);

		return new ResponseEntity<CommentGetDTO>(boardCommentIndex, HttpStatus.OK);
	}
	
	@PatchMapping("/comment")
	public ResponseEntity<Integer> updateComment(@RequestBody CommentGetDTO comment) {

		Integer boardCommentUpdate = boardService.updateComment(comment);

		return new ResponseEntity<Integer>(boardCommentUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{index}")
	public ResponseEntity<Integer> deleteComment(@PathVariable Integer index) {

		Integer boardCommentDelete = boardService.deleteComment(index);

		return new ResponseEntity<Integer>(boardCommentDelete, HttpStatus.OK);
	}
	
}
