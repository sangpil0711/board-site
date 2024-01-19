package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import kr.co.ymtech.bm.repository.vo.UserManageVO;

@Repository
public class UserSystemRepository implements IUserSystemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * @Method getUserInfo 사용자 관리 시 유저 리스트를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IUserSystemRepository#getUserInfo(java.lang.String)
	 *
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 수
	 * 
	 * @return 사용자 정보를 조회하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	@Override
	public List<UserManageVO> getUserInfo(Integer pageNumber, Integer itemSize) {
		
		Integer offset = (pageNumber - 1) * itemSize;
		
	    RowMapper<UserManageVO> mapper = new RowMapper<UserManageVO>() {

	        @Override
	        public UserManageVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	            UserManageVO member = new UserManageVO(
	                    rs.getString("id"),
	                    rs.getString("username"),
	                    rs.getString("email"),
	                    rs.getInt("grade_id")
	            );
	            return member;
	        }
	    };
	    
	    return jdbcTemplate.query("SELECT * FROM \"user\" ORDER BY grade_id ASC OFFSET ? LIMIT ?", mapper, offset, itemSize);
	}
	
	/**
	 * @Method findCount 사용자 수를 저장하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IUserSystemRepository#findCount()
	 * 
	 * @return 사용자 수를 조회하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public Integer findCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM \"user\"", Integer.class);
	}
	/**
	 * @Method updateGrade 사용자 권한 번호를 수정하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IUserSystemRepository#updateGrade()
	 * 
	 * @return 사용자 권한 번호를 수정하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public Integer updateGrade(UserManageVO updateInfo) {
		return jdbcTemplate.update("UPDATE \"user\" SET grade_id = ? WHERE id = ?",
				 updateInfo.getGradeId(),updateInfo.getId());
	}
	
	/**
	 * @Method deleteUser 사용자를 삭제하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IUserSystemRepository#deleteUser()
	 * 
	 * @return id 에 맞는 사용자를 삭제하는 query 함수 실행
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public Integer deleteUser(String id) {
		return jdbcTemplate.update("DELETE FROM \"user\" WHERE id = ?", id);
	}


}
