package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import kr.co.ymtech.bm.repository.vo.BoardVO;

/**
 * 일반게시판 Repository 클래스
 */
@Repository
public class BoardRepository implements IBoardRepository {

	/**
	 * jdbc사용 DB 연결
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Method : 게시물에 저장되어 있는 정보를 모두 조회하는 메소드
	 * 
	 * @return : DB에 있는 정보를 조회하는 query 함수 실행
	 */
	@Override
	public List<BoardVO> findAll() {

		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException { // ResultSet에 결과값을 담아 BoardVO에 담음
				BoardVO member = new BoardVO(	
						rs.getInt("index"), 
						rs.getString("title"), 
						rs.getString("content"),
						rs.getString("user_id"), 
						rs.getInt("category"), 
						rs.getLong("create_date")
						);

				return member;
			}
		};
		return jdbcTemplate.query("select * from board order by index desc offset 0 limit 5", mapper);
	}

	// ->
	// https://velog.io/@ogu1208/spring-JdbcTemplate%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%BF%BC%EB%A6%AC-%EC%8B%A4%ED%96%89

//		return jdbcTemplate.query("select * from board",(rs, rowNum) -> new BoardVO(
//				
//				rs.getInt("index"),
//				rs.getString("title"),
//				rs.getString("content"),
//				rs.getString("user_id"),
//				rs.getInt("category"),
//				rs.getLong("create_date")
//				));
	

	/**
	 * Method : 게시물 정보를 저장하는 메소드
	 * 
	 * @param : board는 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 * 
	 * @return : 게시물 정보를 DB에 저장하는 update 함수 실행
	 */
	@Override
	public Integer saveBoard(BoardVO board) {

		return jdbcTemplate.update("insert into board(title, content, create_Date) values(?, ?, ?)", 
//				board.getIndex(), 
				board.getTitle(),
				board.getText(), 
//				board.getUserId(), 
//				board.getCategory(), 
				board.getCreateDate()
				);
	}
	
	/**
	 * Method : 게시물 내용(text)을 수정 하는 메소드
	 * 
	 * @param : board는 클라이언트가 요청한 게시물 내용을 담고 있다.
	 * 
	 * @return : DB에 있는 게시물 정보를 수정하는 update 함수 실행
	 */
	@Override
	public Integer updateBoard(BoardVO board) {

		return jdbcTemplate.update("update board set title = ?, content = ? where index = ? ", board.getTitle(), board.getText(), board.getIndex());
	}

	/**
	 * Method : 게시물을 삭제하는 메소드
	 * 
	 * @param : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return : DB에 있는 해당 index 번호의 게시물 정보를 삭제하는 update 함수 실행
	 */
	@Override
	public Integer deleteBoard(Integer index) {

		return jdbcTemplate.update("delete from board where index = ?", index);
	}

	/**
	 * Method : 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 * 
	 * @param : index는 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return : 해당 번호의 게시물 정보를 조회하는 query 함수 실행
	 */
	@Override
	public BoardVO searchByIndex(Integer index) {

		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO member = new BoardVO(
						rs.getInt("index"), 
						rs.getString("title"), 
						rs.getString("content"),
						rs.getString("user_id"), 
						rs.getInt("category"), 
						rs.getLong("create_date")
						);

				return member;
			}
		};
		
		return jdbcTemplate.queryForObject("select * from board where index = ?", mapper, index);
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
