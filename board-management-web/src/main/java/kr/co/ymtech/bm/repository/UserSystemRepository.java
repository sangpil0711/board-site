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
	public List<UserManageVO> getUserInfo() {
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
	    
	    List<UserManageVO> result = jdbcTemplate.query("SELECT * FROM \"user\"", mapper);
	    
	    if (result == null) {
	        System.out.println("Query result is null.");
	    }
	    
	    return result;
	}

	
	@Override
	public Integer updateGrade(UserManageVO updateInfo) {
		return jdbcTemplate.update("UPDATE \"user\" SET grade_id = ? WHERE id = ?",
				 updateInfo.getGradeId(),updateInfo.getId());
	}


}
