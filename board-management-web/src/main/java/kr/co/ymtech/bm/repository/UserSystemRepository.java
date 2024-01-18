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
	
	@Override
	public Integer findCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM \"user\"", Integer.class);
	}
	
	@Override
	public Integer updateGrade(UserManageVO updateInfo) {
		return jdbcTemplate.update("UPDATE \"user\" SET grade_id = ? WHERE id = ?",
				 updateInfo.getGradeId(),updateInfo.getId());
	}
	
	@Override
	public Integer deleteUser(String id) {
		return jdbcTemplate.update("DELETE FROM \"user\" WHERE id = ?", id);
	}


}
