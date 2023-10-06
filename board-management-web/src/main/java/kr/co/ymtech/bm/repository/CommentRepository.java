package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.CommentVO;

/**
 * 일반게시판 CommentRepository 클래스
 * 
 * 작성일 : 2023.09.20
 * 작성자 : 박상현
 */
@Repository
public class CommentRepository implements ICommentRepository {
	
	/**
	 * jdbc사용 DB 연결
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Method : 댓글 정보를 저장하는 메소드
	 * 
	 * @param : comment는 사용자가 저장하려고 하는 댓글 정보를 담고 있다.
	 * 
	 * @return : 댓글 정보를 DB에 저장하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Override
	public Integer insertComment(CommentVO comment) {

		return jdbcTemplate.update("insert into \"comment\"(board_index,\"content\", parent_index, create_date) values(?, ?, ?, ?)", 
//				comment.getIndex(), 
				comment.getBoardIndex(),
				comment.getText(),
				comment.getParentIndex(),
//				comment.getUserId(), 
				comment.getCreateDate()
				);
	}
	
	/**
	 * Method : 게시물 번호를 이용하여 댓글 정보들을 조회하는 메소드
	 * 
	 * @param : boardIndex는 사용자가 저장하려고 하는 댓글 정보를 담고 있다.
	 * 
	 * @return : DB에 있는 댓글정보를 조회하는 query 함수 실행
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Override
	public List<CommentVO> findComment(Integer boardIndex) {

		RowMapper<CommentVO> mapper = new RowMapper<CommentVO>() {

			@Override
			public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentVO member = new CommentVO(
						rs.getInt("index"), 
						rs.getInt("board_index"), 
						rs.getString("content"),
						rs.getObject("parent_index", Integer.class), 
						rs.getString("user_id"), 
						rs.getLong("create_date")
						);
				return member;
			}
		};
		return jdbcTemplate.query("select * from comment where board_index = ?", mapper, boardIndex);
	}
	
	/**
	 * Method : 댓글 내용(text)을 수정 하는 메소드
	 * 
	 * @param : comment는 사용자가 요청한 게시물 내용을 담고 있다.
	 * 
	 * @return : DB에 있는 댓글 정보를 수정하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Override
	public Integer updateComment(CommentVO comment) {

		return jdbcTemplate.update("update comment set content = ? where index = ? ", comment.getText(), comment.getIndex());
	}
	
	/**
	 * Method : 댓글 1개를 삭제하는 메소드
	 * 
	 * @param : index는 댓글의 번호를 담고 있고 댓글의 번호를 보고 삭제
	 * 
	 * @return : DB에 있는 해당 index 번호의 댓글 정보를 삭제하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Override
	public Integer deleteComment(Integer index) {

		return jdbcTemplate.update("delete from comment where index = ?", index);
	}
	
	/**
	 * Method : 해당 게시글 번호의 댓글 전체를 삭제하는 메소드
	 * 
	 * @param boardIndex : boardIndex는 게시글 번호를 담고 있고 게시글 번호를 보고 댓글 전체 삭제
	 * 
	 * @return : DB에 있는 해당 boardIndex 번호의 댓글 전체를 삭제하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.27
	 * 작성자 : 박상현
	 */
	@Override
	public Integer deleteAllComment(Integer boardIndex) {

		return jdbcTemplate.update("delete from comment where board_index = ?", boardIndex);
	}
	
	
}
