package kr.co.ymtech.bm.repository;

import java.util.List;
import kr.co.ymtech.bm.repository.vo.CommentVO;

/**
 * 일반게시판 ICommentRepository 인터페이스
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 박상현
 */
public interface ICommentRepository {

	/**
	 * Method : DB에 저장되어 있는 댓글 정보들을 모두 조회
	 * 
	 * @param comment : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer insertComment(CommentVO comment);

	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 댓글 정보들을 조회
	 * 
	 * @param boardIndex : indboardIndexex는 게시물 번호를 담고 있고 해당 번호의 댓글 정보들을 조회
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public List<CommentVO> findComment(Integer boardIndex);

	/**
	 * Method : 댓글 내용(text)을 수정
	 * 
	 * @param comment : comment는 사용자 수정할 부분의 댓글 내용을 담고 있다.
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer updateComment(CommentVO comment);

	/**
	 * Method : 댓글 1개 정보를 삭제
	 * 
	 * @param index : index는 댓글 번호를 담고 있고 댓글 번호를 보고 삭제
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	public Integer deleteComment(Integer index);

	/**
	 * Method : 게시글번호에 해당하는 댓글 전체를 삭제하는 메소드
	 * 
	 * @param boardIndex : boardIndex는 게시글 번호를 담고 있고 게시글 번호를 보고 댓글 전체 삭제
	 * 
	 * @return : DB에 있는 해당 boardIndex 번호의 댓글 전체를 삭제하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.27
	 * 작성자 : 박상현
	 */
	public Integer deleteAllComment(Integer boardIndex);

}
