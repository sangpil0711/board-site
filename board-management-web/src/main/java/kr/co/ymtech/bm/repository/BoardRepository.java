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
 * @since 2023. 09. 18.
 */
@Repository
public class BoardRepository implements IBoardRepository {

	/**
	 * jdbc사용 DB 연결
	 * 
	 * @author 박상현
	 * @since 2023. 09. 18.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @Method findPage 게시물에 저장되어 있는 정보를 조회 및 검색하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#findPage(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize   게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword    게시판 검색어
	 * 
	 * @return DB에 있는 정보를 조회 및 검색하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@Override
	public List<BoardVO> findPage(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category) {
		Integer offset = (pageNumber - 1) * itemSize;

		// 기본 쿼리
		String sql = "SELECT * FROM board WHERE category = ?";

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
				BoardVO member = new BoardVO(rs.getInt("index"), rs.getString("title"), rs.getString("content"),
						rs.getString("user_id"), rs.getInt("category"), rs.getLong("create_date"),
						rs.getInt("like_count"));
				return member;
			}
		};

		return jdbcTemplate.query(sql, mapper, category, "%" + keyword + "%", offset, itemSize);
	}

	/**
	 * @Method findCount DB에 저장되어 있는 게시물 수를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#findCount(java.lang.String,
	 *      java.lang.String)
	 *
	 * @param searchType 게시판 검색 유형
	 * @param keyword    게시판 검색어
	 * 
	 * @return PageVO 클래스에 있는 객체를 하나만 포함하는 리스트를 생성 후 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 05.
	 */
	@Override
	public Integer findCount(String searchType, String keyword, Integer category) {

		// 기본 쿼리
		String sql = "SELECT COUNT(*) FROM board WHERE category = ?";

		// 검색 조건을 추가
		if ("title".equals(searchType)) {
			sql += " AND title LIKE ?";
		} else if ("content".equals(searchType)) {
			sql += " AND content LIKE ?";
		} else if ("user_id".equals(searchType)) {
			sql += " AND user_id LIKE ?";
		}

		return jdbcTemplate.queryForObject(sql, Integer.class, category, "%" + keyword + "%");
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
	 * @author 황상필
	 * @since 2023. 10. 30.
	 */
	@Override
	public Integer saveBoard(BoardVO board, List<FileVO> file) {

		// 게시글 정보를 DB에 저장
		Integer boardDB = jdbcTemplate.update(
				"INSERT INTO board(index, title, content, category, create_Date) VALUES(?, ?, ?, ?, ?)", board.getIndex(), board.getTitle(),
				board.getText(), board.getCategory(), board.getCreateDate());

		// 게시글에 업로드된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), files.getBoardIndex(), files.getFilePath(), files.getFileName(),
					files.getFileSize());
		}

		return boardDB;
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
	 * @author 황상필
	 * @since 2023. 10. 31.
	 */
	@Override
	public Integer updateBoard(BoardVO board, List<FileVO> file) {

		// 수정된 게시글 정보를 DB에 저장
		Integer boardDB = jdbcTemplate.update("UPDATE board SET title = ?, content = ? WHERE index = ? ", board.getTitle(),
				board.getText(), board.getIndex());

		// 게시글에 추가된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), board.getIndex(), files.getFilePath(), files.getFileName(), files.getFileSize());
		}
		
		return boardDB;
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
		return jdbcTemplate.update("DELETE FROM board WHERE index = ?", index);
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

			// ResultSet에 결과값을 담아 BoardVO에 담음
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO member = new BoardVO(rs.getInt("index"), rs.getString("title"), rs.getString("content"),
						rs.getString("user_id"), rs.getInt("category"), rs.getLong("create_date"),
						rs.getInt("like_count"));

				return member;
			}
		};

		return jdbcTemplate.queryForObject("SELECT * FROM board WHERE index = ?", mapper, index);
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

			// ResultSet에 결과값을 담아 FileVO에 담음
			@Override
			public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {

				FileVO member = new FileVO(rs.getString("uuid"), rs.getInt("board_index"),
						rs.getString("file_location"), rs.getString("original_filename"), rs.getLong("file_size"));

				return member;
			}
		};

		return jdbcTemplate.query("SELECT * FROM file WHERE board_index = ?", mapper, index);

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
	public Integer lastBoardIndex() {
		return jdbcTemplate.queryForObject("SELECT index FROM board ORDER BY index DESC OFFSET 0 LIMIT 1",
				Integer.class);
	}

	/**
	 * @Method deleteFiles 해당 게시물에 업로드된 파일을 전부 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IFileRepository#resetFiles(java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @return DB에 있는 정보를 삭제하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	@Override
	public Integer deleteFiles(Integer index) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ?", index);
	}

	/**
	 * @Method deleteFile 해당 게시물에 업로드된 파일을 개별 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#deleteFile(java.lang.Integer,
	 *      java.lang.String)
	 *
	 * @param index  해당 게시글 번호
	 * @param fileId 업로드된 파일의 UUID
	 * 
	 * @return DB에 있는 정보를 삭제하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 10. 31.
	 */
	@Override
	public Integer deleteFile(Integer index, String fileId) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ? AND uuid = ?", index, fileId);
	}

	/**
	 * @Method boardLikeCount 해당 게시글의 추천 수를 반환하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#boardLikeCount(java.lang.Integer,
	 *      java.lang.Integer)
	 *
	 * @param index     해당 게시글 번호
	 * @param likeCount 해당 게시글 추천 수
	 * 
	 * @return 해당 게시글의 추천 수를 수정하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 11. 03.
	 */
	@Override
	public Integer boardLikeCount(Integer index, Integer likeCount) {
		return jdbcTemplate.update("UPDATE board SET like_count = ? WHERE index = ? ", likeCount, index);
	}

	/**
	 * @Method bestBoard 추천 수가 많은 게시글을 반환하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#bestBoard()
	 *
	 * @return 추천 수가 많은 게시글 5개를 반환하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 11. 06.
	 */
	@Override
	public List<BoardVO> bestBoard() {

		RowMapper<BoardVO> mapper = new RowMapper<BoardVO>() {

			// ResultSet에 결과값을 담아 BoardVO에 담음
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO member = new BoardVO(rs.getInt("index"), rs.getString("title"), rs.getString("content"),
						rs.getString("user_id"), rs.getInt("category"), rs.getLong("create_date"),
						rs.getInt("like_count"));

				return member;
			}
		};
		return jdbcTemplate.query(
				"SELECT * FROM board WHERE to_timestamp(create_date / 1000) >= CURRENT_TIMESTAMP + '-7 days' ORDER BY like_count DESC, to_timestamp(create_date / 1000) DESC OFFSET 0 LIMIT 8",
				mapper);
	}

	/**
	 * @Method bestBoardFile 추천 수가 많은 게시글에 업로드된 파일 정보를 받아오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#bestBoardFile(java.lang.Integer)
	 *
	 * @param index 추천 수가 많은 게시글 번호
	 * 
	 * @return 추천 수가 많은 게시글 5개의 파일 정보를 반환하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2023. 11. 08.
	 */
	@Override
	   public List<FileVO> bestBoardFile(Integer index) {
	      
	      String sql = "SELECT * FROM board WHERE to_timestamp(create_date / 1000) >= CURRENT_TIMESTAMP + '-7 days' ORDER BY like_count DESC, to_timestamp(create_date / 1000) DESC OFFSET 0 LIMIT 8";

		RowMapper<FileVO> mapper = new RowMapper<FileVO>() {

	         // ResultSet에 결과값을 담아 FileVO에 담음
	         @Override
	         public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {

	            FileVO member = new FileVO(rs.getString("uuid"), rs.getInt("board_index"),
	                  rs.getString("file_location"), rs.getString("original_filename"), rs.getLong("file_size"));

				return member;
			}
		};
		return jdbcTemplate.query(
				"SELECT * FROM file INNER JOIN (" + sql
						+ ") AS best_board ON file.board_index = best_board.index WHERE board_index = ?",
				mapper, index);
	}

}
