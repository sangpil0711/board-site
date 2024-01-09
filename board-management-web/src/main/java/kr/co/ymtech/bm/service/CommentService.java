package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @since 2023. 09. 20.
 */
@Service
public class CommentService implements ICommentService {

	private final ICommentRepository commentRepository;

	@Autowired
	private CommentService(ICommentRepository IcommentRepository) {
		this.commentRepository = IcommentRepository;
	}

	/**
	 * @Method 댓글 정보를 저장하는 메소드
	 * 
	 * @param comment 사용자가 저장하려고 하는 댓글 정보
	 * 
	 * @return 댓글을 DB에 저장하고 성공하면 1, 실패하면 0을 savecomment 변수에 담아 반환
	 * 
	 * @author 박상현
	 * @since 2023. 09. 20.
	 */
	@Override
	public Integer insertComment(CommentDTO comment) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// dto -> vo 변환
		CommentVO vo = new CommentVO();
		vo.setBoardIndex(comment.getBoardIndex());
		vo.setText(comment.getText());
		vo.setParentIndex(comment.getParentIndex());
		vo.setUserId(auth.getName());
		vo.setCreateDate(new Date().getTime());

		return commentRepository.insertComment(vo);
	}

	/**
	 * @Method 게시물 번호를 이용하여 댓글 정보들을 조회하는 메소드
	 * 
	 * @param boardIndex 게시물 번호를 담고 있고 해당 번호의 댓글 정보들을 조회
	 * 
	 * @return 해당 번호의 게시물 정보를 findComment 변수에 담고 반환
	 * 
	 * @author 박상현
	 * @since 2023. 09. 27.
	 */
	@Override
	public List<CommentSearchDTO> findComments(Integer boardIndex) {
		List<CommentVO> commentList = commentRepository.findComments(boardIndex);

		// 댓글 리스트
		List<CommentSearchDTO> findComments = new ArrayList<>();

		for (CommentVO vo : commentList) {
			// dto -> vo 변환
			CommentSearchDTO dto = new CommentSearchDTO();
			dto.setIndex(vo.getIndex());
			dto.setBoardIndex(vo.getBoardIndex());
			dto.setText(vo.getText());
			dto.setParentIndex(vo.getParentIndex());
			dto.setUserId(vo.getUserId());
			dto.setCreateDate(new Date(vo.getCreateDate()));

			// 대댓글이라면, 해당 댓글 ID에 대한 객체를 찾은 후, childs 변수에 넣어줌
			if (vo.getParentIndex() != null) {
				for (CommentSearchDTO searchDto : findComments) {
					if (searchDto.getIndex().equals(vo.getParentIndex())) {
						// NullPointerException 오류 처리
						if (searchDto.getChilds() == null) {
							// null 값을 가지고 있는 객체를 호출할 때 발생하니 null일시 처리해줌
							searchDto.setChilds(new ArrayList<>());
						}
						searchDto.getChilds().add(dto);
					}
				}
			}
			// 댓글인 경우, 바로 findComments 리스트에 추가
			else {
				findComments.add(dto);
			}
		}

		return findComments;
	}

	/**
	 * @Method 댓글 내용(text)을 수정 하는 메소드
	 * 
	 * @param comment 사용자가 요청한 댓글 내용
	 * 
	 * @return 업데이트 한 댓글 내용을 updatecomment 변수에 담고 반환
	 * 
	 * @author 박상현
	 * @since 2023. 09. 20.
	 */
	@Override
	public Integer updateComment(CommentGetDTO comment) {

		CommentVO vo = new CommentVO();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (comment.getUserId().equals(auth.getName())) {
			// dto -> vo 변환
			vo.setIndex(comment.getIndex());
			vo.setText(comment.getText());

		}

		return commentRepository.updateComment(vo);
	}

	/**
	 * @Method 댓글을 삭제하는 메소드
	 * 
	 * @param index  댓글의 번호를 담고 있고 댓글의 번호를 보고 삭제
	 * @param userId 로그인한 사용자 아이디
	 * 
	 * @return CommentRepository 에서 deleteComment 함수 실행
	 * 
	 * @author 박상현
	 * @since 2023. 09. 27.
	 */
	@Override
	public Integer deleteComment(Integer index, String userId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// 로그인한 유저 아이디가 작성자 아이디와 같거나 권한이 ROLE_ADMIN이면 동작
		if (userId.equals(auth.getName()) || auth.getAuthorities().stream()
				.anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()))) {

			// 댓글과 관련된 대댓글을 모두 삭제
			commentRepository.deleteChildComments(index);
		}

		return commentRepository.deleteComment(index);
	}

}
