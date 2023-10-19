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
import org.springframework.web.bind.annotation.RestController;
import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;
import kr.co.ymtech.bm.controller.dto.CommentSearchDTO;
import kr.co.ymtech.bm.service.CommentService;
import kr.co.ymtech.bm.service.ICommentService;

@RestController
public class CommentController {
	
	@Autowired
	private final ICommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/**
	 * Method : 댓글 정보를 저장하는 메소드
	 * 
	 * @param comment : 사용자가 저장한 댓글 정보를 담고 있다.
	 *
	 * @return 댓글 정보를 DB에 저장하고 성공하면 1, 실패하면 0을 boardCommentSave 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	@PostMapping("/boards/{boardIndex}/comments")
	public ResponseEntity<Integer> insertComment(@PathVariable Integer boardIndex, @RequestBody CommentDTO comment) {

		Integer boardCommentInsert = commentService.insertComment(comment);
		 
		return new ResponseEntity<Integer>(boardCommentInsert, HttpStatus.OK);
	}
	
	/**
	 * Method : 댓글 정보를 조회하는 메소드
	 * 
	 * @param boardIndex : 게시물 번호를 담고 있어 댓글을 조회할 시 해당 게시글 번호의 댓글을 조회 할 수 있다. 
	 * 
	 * @return  해당 게시물 번호로 댓글 정보를 조회하여 boardCommentIndex 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
//	@GetMapping("/comments/{boardIndex}")
	@GetMapping("/boards/{boardIndex}/comments")
	public ResponseEntity<List<CommentSearchDTO>> findComments(@PathVariable Integer boardIndex) {
		
		List<CommentSearchDTO> boardCommentIndex = commentService.findComments(boardIndex);
		
		return new ResponseEntity<List<CommentSearchDTO>>(boardCommentIndex, HttpStatus.OK);
	}
	
	/**
	 * Method : 댓글 정보를 수정하는 메소드
	 * 
	 * @param comment : 댓글 내용 수정을 위해 댓글 번호를 담고 있다.
	 * 
	 * @return 업데이트 한 댓글 내용을 boardCommentUpdate 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	@PatchMapping("/boards/{boardIndex}/comments/{index}")
	public ResponseEntity<Integer> updateComment(@PathVariable Integer index, @RequestBody CommentGetDTO comment) {

		Integer boardCommentUpdate = commentService.updateComment(comment);

		return new ResponseEntity<Integer>(boardCommentUpdate, HttpStatus.OK);
	}
	
	/**
	 * Method : 댓글 1개 정보를 삭제하는 메소드
	 * 
	 * @param index : index는 댓글 번호를 담고 있고 댓글 번호를 보고 삭제
	 * 
	 * @return 댓글을 삭제 후 boardCommentDelete 변수에 담고 ResponseEntity 를 사용하여 응답한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	@DeleteMapping("/boards/{boardIndex}/comments/{index}")
	public ResponseEntity<Integer> deleteComment(@PathVariable Integer index) {

		Integer boardCommentDelete = commentService.deleteComment(index);

		return new ResponseEntity<Integer>(boardCommentDelete, HttpStatus.OK);
	}
	
	
}
