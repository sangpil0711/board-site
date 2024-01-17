package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.SystemVO;

@Repository
public class SystemRepository implements ISystemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer createSystem(SystemVO system) {
		return jdbcTemplate.update("INSERT INTO property(key, value, description, create_date) VALUES(?, ?, ?, ?)",
				system.getKey(), system.getValue(), system.getDescription(), system.getCreateDate());
	}
	
	@Override
	public List<SystemVO> findPage() {
		
		RowMapper<SystemVO> mapper = new RowMapper<SystemVO>() {
			@Override
			public SystemVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SystemVO member = new SystemVO(rs.getString("key"), rs.getString("value"), rs.getString("description"));
				return member;
			}
		};
		
		return jdbcTemplate.query("SELECT * FROM property ORDER BY create_date DESC", mapper);
	}

}