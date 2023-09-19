package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.CommentVO;

@Repository
public class CommentRepository implements ICommentRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public Integer saveComment(CommentVO comment) {

		return jdbcTemplate.update("insert into \"comment\"(board_index,\"content\",create_date) values(?, ?, ?)", 
//				comment.getIndex(), 
				comment.getBoardIndex(),
				comment.getText(),
//				comment.getParentIndex()
//				comment.getUserId(), 
				comment.getCreateDate()
				);
	}
	
	@Override
	public CommentVO findComment(Integer boardIndex) {

		RowMapper<CommentVO> mapper = new RowMapper<CommentVO>() {

			@Override
			public CommentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentVO member = new CommentVO(
						rs.getInt("index"), 
						rs.getInt("board_index"), 
						rs.getString("content"),
						rs.getInt("parent_index"), 
						rs.getString("user_id"), 
						rs.getLong("create_date")
						);

				return member;
			}
		};
		return jdbcTemplate.queryForObject("select * from comment where board_index = ?", mapper, boardIndex);
	}
	
	@Override
	public Integer updateComment(CommentVO comment) {

		return jdbcTemplate.update("update comment set content = ? where index = ? ", comment.getText(), comment.getIndex());
	}
	
	@Override
	public Integer deleteComment(Integer index) {

		return jdbcTemplate.update("delete from comment where index = ?", index);
	}
	
	
}
