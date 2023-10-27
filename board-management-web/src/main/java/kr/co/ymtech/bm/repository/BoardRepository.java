package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 일반게시판 BoardRepository 클래스
 * 
 * @author 박상현
 * @since  2023. 09. 18.
 */
@Repository
public class BoardRepository implements IBoardRepository {

	/**
	 * jdbc사용 DB 연결
	 * 
	 * @author 박상현
	 * @since  2023. 09. 18.
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
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword, Integer category) {
	    Integer offset = (pageNumber - 1) * itemSize;
	    String sql = "SELECT * FROM board WHERE category = ?"; // 기본 쿼리

	    // 검색 조건을 추가
	    if ("title".equals(searchType)) {
	        sql += " AND title LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
	    } else if ("content".equals(searchType)) {
	        sql += " AND content LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
	    } else if ("user_id".equals(searchType)) {
	        sql += " AND user_id LIKE ? ORDER BY index DESC OFFSET ? LIMIT ?";
	    }

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

	    return jdbcTemplate.query(sql, mapper, category, "%" + keyword + "%", offset, itemSize);
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
	public Integer findCount(String searchType, String keyword, Integer category) {
		
	    String sql = "SELECT COUNT(*) FROM board";
	    
	    if ("title".equals(searchType)) {
	        sql += " WHERE title LIKE ?";
	    } else if ("content".equals(searchType)) {
	        sql += " WHERE content LIKE ?";
	    } else if ("user_id".equals(searchType)) {
	        sql += " WHERE user_id LIKE ?";
	    }
	    
	    return jdbcTemplate.queryForObject(sql, Integer.class, "%" + keyword + "%");
	}

	/**
	 * 
	 * @Method saveBoard 게시물 정보를 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#saveBoard(kr.co.ymtech.bm.repository.vo.BoardVO)
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 * 
	 * @return 게시물 정보를 DB에 저장하는 update 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Override
	public Integer saveBoard(BoardVO board) {
		  return jdbcTemplate.update("insert into board(title, content, category, create_Date) values(?, ?, ?, ?)", board.getTitle(),
				board.getText(), board.getCategory(), board.getCreateDate());
	}

	/**
	 * 
	 * @Method updateBoard 게시물을 수정 하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#updateBoard(kr.co.ymtech.bm.repository.vo.BoardVO)
	 *
	 * @param board 클라이언트가 요청한 게시물 내용
	 * 
	 * @return DB에 있는 게시물 정보를 수정하는 update 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Override
	public Integer updateBoard(BoardVO board) {
		return jdbcTemplate.update("update board set title = ?, content = ? where index = ? ", board.getTitle(),
				board.getText(), board.getIndex());
	}

	/**
	 * 
	 * @Method deleteBoard 게시물을 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#deleteBoard(java.lang.Integer)
	 *
	 * @param index 게시물의 번호를 담고 있고 게시물 번호를 보고 삭제
	 * 
	 * @return DB에 있는 해당 index 번호의 게시물 정보를 삭제하는 update 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Override
	public Integer deleteBoard(Integer index) {
		return jdbcTemplate.update("delete from board where index = ?", index);
	}

	/**
	 * 
	 * @Method searchByIndex 특정 게시물 번호를 이용하여 해당 번호의 정보를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#searchByIndex(java.lang.Integer)
	 *
	 * @param index index 게시물 번호를 담고 있고 해당 번호의 게시물 정보를 조회
	 * 
	 * @return 해당 번호의 게시물 정보를 조회하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 09. 18.
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
	
	/**
	 * 
	 * @Method files 게시물 번호에 해당되는 파일 정보를 조회
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#files(java.lang.Integer)
	 *
	 * @param index 해당 게시물 번호
	 * 
	 * @return 해당 번호의 파일 정보를 조회하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	@Override
	public List<FileVO> files(Integer index) {
		
		RowMapper<FileVO> mapper = new RowMapper<FileVO>() {

			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				FileVO member = new FileVO(
						rs.getString("uuid"), 
						rs.getInt("board_index"), 
						rs.getString("file_location"),
						rs.getString("original_filename"));

				return member;
			}
		};
		
		return jdbcTemplate.query("SELECT * FROM file WHERE board_index = ?", mapper, index);
				
	}
	
	/**
	 * @Method saveFile 파일 정보를 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IFileRepository#saveFile(kr.co.ymtech.bm.repository.vo.FileVO)
	 *
	 * @param file DB에 보낼 파일 데이터
	 * 
	 * @return file DB 테이블에 파일 데이터를 저장
	 *
	 * @author 황상필
	 * @since 2023. 10. 11.
	 */
	@Override
	public Integer saveFile(FileVO file) {
	    return jdbcTemplate.update("insert into file(uuid, board_index, file_location, original_filename) values(?, ?, ?, ?)",
	            file.getFileId(), file.getBoardIndex(), file.getFilePath(), file.getFileName());
	}
	
	/**
	 * @Method lastBoard 마지막에 저장된 게시물의 번호를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#lastBoard()
	 *
	 * @return 마지막에 저장된 게시물 정보에서 index 값을 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 12.
	 */
	@Override
	public List<BoardVO> lastBoard() {

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
		return jdbcTemplate.query("SELECT * FROM board order by index desc offset 0 limit 1", mapper);
	}

}
