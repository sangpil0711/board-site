package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class BoardRepository implements IBoardRepository {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	@Override
	public List<BoardVO> findAll() {
		
		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {
			
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO member = new BoardVO(
						rs.getInt("index"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("user_id"),
						rs.getInt("category"),
						rs.getLong("create_date"));
				
				return member;
			}
		};
		return jdbcTemplate.query("select * from board", mapper);
	}
		
		//  -> https://velog.io/@ogu1208/spring-JdbcTemplate%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%BF%BC%EB%A6%AC-%EC%8B%A4%ED%96%89
		
//		return jdbcTemplate.query("select * from board",(rs, rowNum) -> new BoardVO(
//				
//				rs.getInt("index"),
//				rs.getString("title"),
//				rs.getString("content"),
//				rs.getString("user_id"),
//				rs.getInt("category"),
//				rs.getLong("create_date")
//				));
	
	@Override
	public Integer boardSave(BoardVO board) {
	
		return jdbcTemplate.update("insert into board values(?,?,?,?,?,?)",
				board.getIndex(),
				board.getTitle(),
				board.getText(),
				board.getUserId(),
				board.getCategory(),
				board.getCreateDate()
				); 
	}
	
	@Override
	public Integer boardUpdate(BoardVO board) {
		
		return jdbcTemplate.update("update board set cotent = ? where Index = ? ",
				board.getText(),board.getIndex()
				);
	}
	
	@Override
	public Integer boardDelete(Integer index) {
		
		return jdbcTemplate.update("update board set title = 'FALSE' where user_id = ?", index);
	}
	
	@Override
	public List<BoardVO> indexSearch(Integer index) {
		
		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {
			
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO member = new BoardVO(
						rs.getInt("index"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("user_id"),
						rs.getInt("category"),
						rs.getLong("create_date"));
				
				return member;
			}
		};
		return jdbcTemplate.query("select * from board where index =? ",mapper,index);
	}

//		return jdbcTemplate.query("select * from board where index =? or name title ? ", new Object[] {index,"%"},(rs,rowNum) -> 
//		
//		new BoardDTO(	
//				rs.getInt("index"),
//				rs.getString("title"),
//				rs.getString("content"),
//				rs.getString("user_id"),
//				rs.getInt("category"),
//				rs.getLong("create_date")
//				));
//	}
	

	

	
	
	
	
}
