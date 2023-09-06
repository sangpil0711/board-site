package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class Boardrepository implements IBoardRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BoardVO> findAll() {
		
		
//		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {
//			@Override
//			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardVO member = new BoardVO(
//						rs.getInt("index"),
//						rs.getString("title"),
//						rs.getString("content"),
//						rs.getString("user_id"),
//						rs.getInt("category"),
//						rs.getLong("create_date"));
//				
//				return member;
//			}
//		};
//		return jdbcTemplate.query("select * from board", mapper);
		
		return jdbcTemplate.query("select * from board",(rs, rowNum) -> new BoardVO(
				
				rs.getInt("index"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getString("user_id"),
				rs.getInt("category"),
				rs.getLong("create_date")
				));
	}
	
	@Override
	public Integer save(BoardVO board) {
	
		return jdbcTemplate.update("insert into board values(?,?,?,?,?,?)",
				board.getIndex(),
				board.getTitle(),
				board.getText(),
				board.getUserId(),
				board.getCategory(),
				board.getCreateDate()
				); 
	}

	
	
	
	
	
	
}
