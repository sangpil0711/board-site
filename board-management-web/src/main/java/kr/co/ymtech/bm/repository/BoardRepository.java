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
 * 일반게시판 BoardRepository 클래스
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 박상현
 */
@Repository
public class BoardRepository implements IBoardRepository {

	/**
	 * jdbc사용 DB 연결
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @Method findPage 게시물에 저장되어 있는 정보를 조회 및 검색하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#findPage(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * 
	 * @return DB에 있는 정보를 조회 및 검색하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@Override
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword) {

		Integer offset = (pageNumber - 1) * itemSize;

		String sql = "SELECT * FROM board ";

		if ("title".equals(searchType)) {
		    sql += "WHERE title LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
		} else if ("content".equals(searchType)) {
		    sql += "WHERE content LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
		} else if ("user_id".equals(searchType)) {
		    sql += "WHERE user_id LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
		}

		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {

			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException { // ResultSet에 결과값을 담아 BoardVO에 담음
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
		
		return jdbcTemplate.query(sql, mapper, "%" + keyword + "%", offset, itemSize);
	}

	/**
	 * @Method findAll DB에 저장되어 있는 게시물 수를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#findCount(java.lang.String, java.lang.String)
	 *
	 * @param stkdearchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * 
	 * @return PageVO 클래스에 있는 객체를 하나만 포함하는 리스트를 생성 후 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@Override
	public Integer findCount(String searchType, String keyword) {
		
	    String sql = "SELECT COUNT(*) FROM board";
	    
	    if ("title".equals(searchType)) {
	        sql += " WHERE title LIKE ?";
	    } else if ("content".equals(searchType)) {
	        sql += " WHERE content LIKE ?";
	    } else if ("user_id".equals(searchType)) {
	        sql += " WHERE user_id LIKE ?";
	    }
	    
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, "%" + keyword + "%");
	    
	    return count;
	}

	/**
	 * Method : 게시물 정보를 저장하는 메소드
	 * 
	 * @param : board는 클라이언트가 저장하려고 하는 게시물 정보를 담고 있다.
	 * 
	 * @return : 게시물 정보를 DB에 저장하는 update 함수 실행
	 * 
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public Integer saveBoard(BoardVO board) {
		  return jdbcTemplate.update("insert into board(title, content, create_Date) values(?, ?, ?)", board.getTitle(),
				board.getText(), board.getCreateDate());
	}

	/**
	 * Method : 게시물 내용(text)을 수정 하는 메소드
	 * 
	 * @param : board는 클라이언트가 요청한 게시물 내용을 담고 있다.
	 * 
	 * @return : DB에 있는 게시물 정보를 수정하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
	 */
	@Override
	public Integer updateBoard(BoardVO board) {
		return jdbcTemplate.update("update board set title = ?, content = ? where index = ? ", board.getTitle(),
				board.getText(), board.getIndex());
	}

	/**
	 * Method : 게시물을 삭제하는 메소드
	 * 
	 * @param : index는 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return : DB에 있는 해당 index 번호의 게시물 정보를 삭제하는 update 함수 실행
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
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
	 * 
	 * 작성일 : 2023.09.18
	 * 작성자 : 박상현
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
						rs.getLong("create_date"));

				return member;
			}
		};

		return jdbcTemplate.queryForObject("select * from board where index = ?", mapper, index);
	}

}
