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

@Repository
public class FileRepository implements IFileRepository {
	
	/**
	 * jdbc사용 DB 연결
	 * 
	 * 작성일 : 2023.09.20
	 * 작성자 : 박상현
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

		String sql = "SELECT * FROM board order by index desc offset 0 limit 1";

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
		
		return jdbcTemplate.query(sql, mapper);
	}
	
}
