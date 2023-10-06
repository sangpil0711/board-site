package kr.co.ymtech.bm.service;

import java.util.List;
import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;
import kr.co.ymtech.bm.controller.dto.CommentSearchDTO;

public interface ICommentService {

	/**
	 * Method : 댓글 정보를 저장
	 * 
	 * @param board : 사용자가 저장하려고 하는 댓글 정보를 담고 있다.
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer insertComment(CommentDTO comment);
	
	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 댓글정보들을 조회
	 * 
	 * @param boardIndex : boardIndex는 게시물 번호를 담고 있고 해당 번호의 댓글 정보들을 조회
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public List<CommentSearchDTO> findComment(Integer boardIndex);
	
	/**
	 * Method : 게시물 내용(text)을 수정
	 * 
	 * @param comment : comment는 사용자가 수정할 부분의 댓글 내용을 담고 있다.
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer updateComment(CommentGetDTO comment);

	/**
	 * Method : 게시물 1개 정보를 삭제
	 * 
	 * @param index : index는 댓글 번호를 담고 있고 댓글 번호를 보고 삭제
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer deleteComment(Integer index);
	
	/**
	 * Method : 해당 번호 게시글에 관련된 댓글 전체 정보를 삭제하는 메소드
	 * 
	 * @param boardIndex : boardIndex는 게시글 번호를 담고 있고 게시글 번호를 보고 댓글 전체 삭제
	 * 
	 * 작성일 : 2023.09.27
	 * 작성자 : 박상현
	 */
	public Integer deleteAllComment(Integer boardIndex);


	

}
