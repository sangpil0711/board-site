package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.CommentDTO;
import kr.co.ymtech.bm.controller.dto.CommentGetDTO;
import kr.co.ymtech.bm.controller.dto.CommentSearchDTO;
import kr.co.ymtech.bm.repository.ICommentRepository;
import kr.co.ymtech.bm.repository.vo.CommentVO;

/**
 * 일반게시판 CommentService 클래스
 * 
 * @author 박상현
 * @since  2023.09.20
 */
@Service
public class CommentService implements ICommentService {

	private final ICommentRepository commentRepository;

	@Autowired
	private CommentService(ICommentRepository IcommentRepository) {
		this.commentRepository = IcommentRepository;
	}

	/**
	 * Method : 댓글 정보를 저장하는 메소드
	 * 
	 * @param : comment는 사용자가 저장하려고 하는 댓글 정보를 담고 있다.
	 * 
	 * @return : 댓글을 DB에 저장하고 성공하면 1, 실패하면 0을 savecomment 변수에 담아 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	@Override
	public Integer insertComment(CommentDTO comment) {

		CommentVO vo = new CommentVO(); // dto -> vo 변환

		vo.setBoardIndex(comment.getBoardIndex());
		vo.setText(comment.getText());
		vo.setParentIndex(comment.getParentIndex());
		vo.setCreateDate(new Date().getTime());

		Integer insertcomment = commentRepository.insertComment(vo);

		return insertcomment;
	}

	/**
	 * Method : 게시물 번호를 이용하여 댓글 정보들을 조회하는 메소드
	 * 
	 * @param : boardIndex는 게시물 번호를 담고 있고 해당 번호의 댓글 정보들을 조회
	 * 
	 * @return : 해당 번호의 게시물 정보를 findComment 변수에 담고 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.27
	 */
	@Override
	public List<CommentSearchDTO> findComments(Integer boardIndex) {
		List<CommentVO> commentList = commentRepository.findComments(boardIndex);

		List<CommentSearchDTO> findComments = new ArrayList<>(); // 댓글 리스트

		for (CommentVO vo : commentList) {
			// vo -> dto 변환 : START
			CommentSearchDTO dto = new CommentSearchDTO(); 
			dto.setIndex(vo.getIndex());
			dto.setBoardIndex(vo.getBoardIndex());
			dto.setText(vo.getText());
			dto.setParentIndex(vo.getParentIndex());
			dto.setUserId(vo.getUserId());
			dto.setCreateDate(new Date(vo.getCreateDate()));
			// vo -> dto 변환 : END
			if (vo.getParentIndex() != null) { // 대댓글이라면, 해당 댓글 ID에 대한 객체를 찾은 후, childs 변수에 넣어줌
				for (CommentSearchDTO searchDto : findComments) {
					if (searchDto.getIndex().equals(vo.getParentIndex())) {
						if (searchDto.getChilds() == null) { // NullPointerException 오류 처리
							searchDto.setChilds(new ArrayList<>()); // null 값을 가지고 있는 객체를 호출할 때 발생하니 null일시 처리해줌
						}
						searchDto.getChilds().add(dto);
						break;
					}
				}
			} else { //
				findComments.add(dto); // 댓글인 경우, 바로 findComments 리스트에 추가
			}
		}

		return findComments;
	}

	/**
	 * Method : 댓글 내용(text)을 수정 하는 메소드
	 * 
	 * @param : comment는 사용자가 요청한 댓글 내용을 담고 있다.
	 * 
	 * @return : 업데이트 한 댓글 내용을 updatecomment 변수에 담고 반환한다.
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	@Override
	public Integer updateComment(CommentGetDTO comment) {

		CommentVO vo = new CommentVO(); // dto -> vo 변환
		vo.setIndex(comment.getIndex());
		vo.setText(comment.getText());

		Integer updatecomment = commentRepository.updateComment(vo);

		return updatecomment;
	}

	/**
	 * Method : 댓글을 삭제하는 메소드
	 * 
	 * @param : index는 댓글의 번호를 담고 있고 댓글의 번호를 보고 삭제
	 * 
	 * @return : CommentRepository 에서 deleteComment 함수를 실행시킨다.
	 * 
	 * @author 박상현
	 * @since  2023.09.27
	 */
	@Override
	public Integer deleteComment(Integer index) {
	    // 댓글과 관련된 대댓글을 모두 삭제
	    commentRepository.deleteChildComments(index);
	    
	    // 댓글 삭제
	    Integer deleteComment = commentRepository.deleteComment(index);

	    return deleteComment;
	}

}
