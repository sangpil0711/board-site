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
	 * @since 2023. 11. 02.
	 */
	@Override
	public List<PhotoBoardVO> findPhotoBoard(Integer pageNumber, Integer itemSize, String searchType, String keyword,
			Integer category) {
		Integer offset = (pageNumber - 1) * itemSize;
		String sql = "SELECT * FROM board INNER JOIN \"user\" ON board.user_id = \"user\".id WHERE board.category = ?"; // 기본 쿼리

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
						rs.getLong("create_date"), rs.getInt("like_count"), rs.getString("username"));
				return member;
			}
		};

		return jdbcTemplate.query(sql, mapper, category, "%" + keyword + "%", offset, itemSize);
	}
	
	/**
	 * @Method findCount DB에 저장되어 있는 게시물 수를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#findCount(java.lang.String,
	 *      java.lang.String)
	 *
	 * @param searchType    게시판 검색 유형
	 * @param keyword       게시판 검색어
	 * @param category      게시물 카테고리
	 * 
	 * @return  카테고리와 검색유형, 검색어에 맞는 것을 찾아 반환
	 *
	 * @author 박상현
	 * @since 2023. 11. 02.
	 */
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
	@Override
	public Integer savePhotoBoard(PhotoBoardVO photo, List<FileVO> file) {

		// 게시글 정보를 DB에 저장
		Integer boardDB = jdbcTemplate.update("INSERT INTO board(index, title, content, user_id, category, create_Date) VALUES(?, ?, ?, ?, ?, ?)",
				photo.getIndex(), photo.getTitle(), photo.getText(), photo.getUserId(), photo.getCategory(), photo.getCreateDate());

		// 게시글에 업로드된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), files.getBoardIndex(), files.getFilePath(), files.getFileName(), files.getFileSize());
		}
		
		return boardDB;
	}

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
	public Integer updatePhotoBoard(PhotoBoardVO photo, List<FileVO> file) {

		// 수정된 게시글 정보를 DB에 저장
		Integer boardDB = jdbcTemplate.update("UPDATE board SET title = ?, content = ? WHERE index = ? ", photo.getTitle(),
				photo.getText(), photo.getIndex());

		// 게시글에 추가된 파일을 DB에 저장
		for (FileVO files : file) {
			jdbcTemplate.update(
					"INSERT INTO file(uuid, board_index, file_location, original_filename, file_size) VALUES(?, ?, ?, ?, ?)",
					files.getFileId(), photo.getIndex(), files.getFilePath(), files.getFileName(), files.getFileSize());
		}
		
		return boardDB;
	}

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
						rs.getLong("create_date"), rs.getInt("like_count"), rs.getString("username"));

				return member;
			}
		};

		return jdbcTemplate.queryForObject("SELECT * FROM board INNER JOIN \"user\" ON board.user_id = \"user\".id where index = ?", mapper, index);
	}
	
	/**
	 * 
	 * @Method files 게시물 번호에 해당되는 파일 정보를 조회
	 *
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#files(java.lang.Integer)
	 *
	 * @param index 해당 게시물 번호
	 * 
	 * @return 해당 번호의 파일 정보를 조회하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 11. 06.
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
	 * @Method deleteFiles 해당 게시물에 업로드된 파일을 전부 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IFileRepository#resetFiles(java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @return DB에 있는 정보를 삭제하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 11. 06.
	 */
	@Override
	public Integer deleteFiles(Integer index) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ?", index);
	}
	
	/**
	 * @Method deleteFile 해당 게시물에 업로드된 파일을 개별 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#deleteFile(java.lang.Integer,
	 *      java.lang.String)
	 *
	 * @param index  해당 게시글 번호
	 * @param fileId 업로드된 파일의 UUID
	 * 
	 * @return DB에 있는 정보를 삭제하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2023. 11. 06.
	 */
	@Override
	public Integer deleteFile(Integer index, String fileId) {
		return jdbcTemplate.update("DELETE FROM file WHERE board_index = ? AND uuid = ?", index, fileId);
	}
	
	/**
	 * @Method photoBoardFile 사진 게시판 화면에 파일 정보를 가져오는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#photoBoardFile(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 *
	 * @param index 해당 게시글 번호
	 * @param pageNumber 게시판 페이지 번호
	 * @param itemSize 게시판 페이지 당 게시글 수
	 * @param searchType 게시판 검색 유형
	 * @param keyword 게시판 검색어
	 * @param category 게시판 카테고리
	 * 
	 * @author 박상현
	 * @since 2023. 11. 6.
	 */
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
	
	/**
	 * @Method getPhotoType 업로드 가능한 이미지 유형을 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IPhotoBoardRepository#getImageType()
	 *
	 * @return 업로드 가능한 이미지 유형을 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	@Override
	public String getImageType() {
		return jdbcTemplate.queryForObject("SELECT value FROM property WHERE key = 'photo.post.image-type'",String.class);
	}
	
	/**
	 * @Method getPostPerPage 페이지당 표시되는 게시글 수를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#getPostPerPage()
	 *
	 * @return 페이지 당 표시되는 게시글 수를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	@Override
	public String getPostPerPage() {
		return jdbcTemplate.queryForObject("SELECT value FROM property WHERE key = 'photo.post.per-page'",String.class);
	}
	
	/**
	 * @Method getMaxPage 한 번에 표시되는 최대 페이지 값을 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IBoardRepository#getMaxPage()
	 *
	 * @return 한 번에 표시되는 최대 페이지 값을 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	@Override
	public Integer getMaxPage() {
		return jdbcTemplate.queryForObject("SELECT value FROM property WHERE key = 'photo.page.max-page'",Integer.class);
	}
	
}
