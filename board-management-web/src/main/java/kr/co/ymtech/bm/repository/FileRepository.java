package kr.co.ymtech.bm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository implements IFileRepository {

	/**
	 * jdbc사용 DB 연결
	 * 
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @Method resetFiles 게시물에 업로드된 파일을 초기화 시키는 메소드
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

}
