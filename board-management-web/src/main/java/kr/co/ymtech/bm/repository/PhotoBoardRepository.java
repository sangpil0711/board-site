package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ymtech.bm.repository.vo.FileVO;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시판 PhotoBoardRepository 클래스
 * 
 * @author 박상현
 * @since 2023.10.24
 */
@Repository
public class PhotoBoardRepository implements IPhotoBoardRepository {

	/**
	 * jdbc사용 DB 연결
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @Method findPhotoBoard 게시물에 저장되어 있는 정보를 조회 및 검색하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#findPhotoBoard(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String)
	 *
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize   게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword    게시판 검색어
	 * @param category   : 일반게시판,사진게시판 구분 카테고리 (일반게시판 0, 사진게시판 1)
	 * 
	 * @return DB에 있는 정보를 조회 및 검색하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 10. 31.
	 */
	@Override
	public List<PhotoBoardVO> findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category) {
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

		RowMapper<PhotoBoardVO> mapper = new RowMapper<PhotoBoardVO>() {
			@Override
			public PhotoBoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PhotoBoardVO member = new PhotoBoardVO(rs.getInt("index"), rs.getString("title"),
						rs.getString("content"), rs.getString("user_id"), rs.getInt("category"),
						rs.getLong("create_date"), rs.getInt("like_count"));
				return member;
			}
		};

		return jdbcTemplate.query(sql, mapper, category, "%" + keyword + "%", offset, itemSize);
	}

	@Override
	public Integer findCount(String searchType, String keyword, Integer category) {

		String sql = "SELECT COUNT(*) FROM board WHERE category = ?";

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
	 * Method : 게시물 정보를 저장하는 메소드
	 * 
	 * @param photo: 저장할 게시물 정보
	 * 
	 * @return : 게시물 정보를 DB에 저장하는 update 함수 실행
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@Transactional
	@Override
	public void savePhotoBoard(PhotoBoardVO photo, List<FileVO> file) {

		// 게시글 정보를 DB에 저장
		jdbcTemplate.update("INSERT INTO board(title, content, category, create_Date) VALUES(?, ?, ?, ?)",
				photo.getTitle(), photo.getText(), photo.getCategory(), photo.getCreateDate());

		// 저장한 게시글 번호
		Integer boardIndex = jdbcTemplate.queryForObject("SELECT index FROM board ORDER BY index DESC OFFSET 0 LIMIT 1",
				Integer.class);

		// 게시글에 업로드된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), boardIndex, files.getFilePath(), files.getFileName(), files.getFileSize());
		}
	}

//	@Override
//	public Integer savePhotoBoard(PhotoBoardVO photo) {
//		return jdbcTemplate.update("insert into board(title, content, category, create_Date) values(?, ?, ?, ?)",
//				photo.getTitle(), photo.getText(), photo.getCategory(), photo.getCreateDate());
//	}

	/**
	 * Method : 게시물 정보를 수정하는 메소드
	 * 
	 * @param : photo: 수정된 게시물 정보
	 * 
	 * @return : 게시물 정보를 DB에 수정하는 update 함수 실행
	 * 
	 * @author 박상현
	 * @since 2023.10.24
	 */
	@Transactional
	@Override
	public void updatePhotoBoard(PhotoBoardVO photo, List<FileVO> file) {

		// 수정된 게시글 정보를 DB에 저장
		jdbcTemplate.update("UPDATE board SET title = ?, content = ? WHERE index = ? ", photo.getTitle(),
				photo.getText(), photo.getIndex());

		// 게시글에 추가된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), photo.getIndex(), files.getFilePath(), files.getFileName(), files.getFileSize());
		}
	}

//	@Override
//	public Integer updatePhotoBoard(PhotoBoardVO photo) {
//		return jdbcTemplate.update("update board set title = ?, content = ? where index = ? ", photo.getTitle(),
//				photo.getText(), photo.getIndex());
//	}

	/**
	 * Method : 게시물 정보를 삭제하는 메소드
	 * 
	 * @param : index: 사진게시물 번호
	 * 
	 * @return : 게시물 정보를 DB에서 삭제하는 update 함수 실행
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@Override
	public Integer deletePhotoBoard(Integer index) {
		return jdbcTemplate.update("delete from board where index = ?", index);
	}

	/**
	 * Method : 사진게시물을 1개를 조회하는 메소드
	 * 
	 * @param : index: 사진게시물 번호
	 * 
	 * @return : 해당 번호의 게시물 정보를 조회하는 query 함수 실행
	 * 
	 * @author 박상현
	 * @since 2023.10.25
	 */
	@Override
	public PhotoBoardVO searchByPhotoIndex(Integer index) {

		RowMapper<PhotoBoardVO> mapper = new RowMapper<PhotoBoardVO>() {

			@Override
			public PhotoBoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PhotoBoardVO member = new PhotoBoardVO(rs.getInt("index"), rs.getString("title"),
						rs.getString("content"), rs.getString("user_id"), rs.getInt("category"),
						rs.getLong("create_date"), rs.getInt("like_count"));

				return member;
			}
		};

		return jdbcTemplate.queryForObject("select * from board where index = ?", mapper, index);
	}

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

	@Override
	public PhotoBoardVO lastPhotoBoard() {

		RowMapper<PhotoBoardVO> mapper = new RowMapper<PhotoBoardVO>() {

			// ResultSet에 결과값을 담아 BoardVO에 담음
			@Override
			public PhotoBoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PhotoBoardVO member = new PhotoBoardVO(rs.getInt("index"), rs.getString("title"),
						rs.getString("content"), rs.getString("user_id"), rs.getInt("category"),
						rs.getLong("create_date"), rs.getInt("like_count"));

				return member;
			}
		};
		return jdbcTemplate.queryForObject("SELECT * FROM board ORDER BY index DESC OFFSET 0 LIMIT 1", mapper);
	}

	@Override
	public Integer deleteFiles(Integer index) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ?", index);
	}

	@Override
	public Integer deleteFile(Integer index, String fileId) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ? AND uuid = ?", index, fileId);
	}

	@Override
	public List<FileVO> photoBoardFile(Integer index, Integer pageNumber, Integer itemSize, String searchType,
			String keyword, Integer category) {
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
						+ ") AS photo_board ON file.board_index = photo_board.index WHERE board_index = ?",
				mapper, category, "%" + keyword + "%", offset, itemSize, index);
	}

}
