package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.InfoVO;
import kr.co.ymtech.bm.repository.vo.UserUpdateVO;

@Repository
public class InfoRepository implements IInfoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public InfoVO getUserInfo(String userId) {

		RowMapper<InfoVO> mapper = new RowMapper<InfoVO>() {
			@Override
			public InfoVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				InfoVO member = new InfoVO(rs.getString("id"), rs.getString("username"), rs.getString("email"));
				return member;
			}
		};

		return jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE id = ?", mapper, userId);
	}
	
	@Override
	public Integer updateUserInfo(UserUpdateVO updateInfo) {
		return jdbcTemplate.update("UPDATE \"user\" SET password = ?, username = ?, email = ? WHERE id = ?", updateInfo.getNewPassword(),
				updateInfo.getUsername(), updateInfo.getEmail(), updateInfo.getId());
	}
	
	@Override
	public String getUserPassword(String userId) {
		return jdbcTemplate.queryForObject("SELECT password FROM \"user\" WHERE id = ?", String.class, userId);
	}

}