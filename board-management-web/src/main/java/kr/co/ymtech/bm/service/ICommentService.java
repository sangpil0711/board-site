package kr.co.ymtech.bm.service;

import java.util.List;
import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;
import kr.co.ymtech.bm.controller.dto.CommentSearchDTO;

/**
 * 일반게시판 ICommentService 인터페이스
 * 
 * @author 박상현
 * @since  2023.09.20
 */
public interface ICommentService {

	/**
	 * Method : 댓글 정보를 저장
	 * 
	 * @param board : 사용자가 저장하려고 하는 댓글 정보를 담고 있다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Integer insertComment(CommentDTO comment);
	
	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 댓글정보들을 조회
	 * 
	 * @param boardIndex : boardIndex는 게시물 번호를 담고 있고 해당 번호의 댓글 정보들을 조회
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public List<CommentSearchDTO> findComments(Integer boardIndex);
	
	/**
	 * Method : 게시물 내용(text)을 수정
	 * 
	 * @param comment : comment는 사용자가 수정할 부분의 댓글 내용을 담고 있다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Integer updateComment(CommentGetDTO comment);

	/**
	 * Method : 게시물 1개 정보를 삭제
	 * 
	 * @param index : index는 댓글 번호를 담고 있고 댓글 번호를 보고 삭제
	 * 
	 * @author 박상현
	 * @since  2023.09.27
	 */
	public Integer deleteComment(Integer index);
	



}
