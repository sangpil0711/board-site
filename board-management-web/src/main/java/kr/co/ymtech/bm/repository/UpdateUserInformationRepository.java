package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.controller.dto.UpdateUserInformationDTO;
import kr.co.ymtech.bm.controller.dto.UserDTO;

@Repository
public class UpdateUserInformationRepository implements IUpdateUserInformationRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserDTO findUserInformation(String id) {
		
		RowMapper<UserDTO> mapper = new RowMapper<UserDTO>() {
			
			@Override
			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserDTO member = new UserDTO(
						rs.getString("id"),
						rs.getString("password"), 
						rs.getString("username"), 
						rs.getString("email"),
						rs.getLong("create_date"),
						rs.getInt("grade_id")
						);

				return member;
			}
		};

		return jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE id = ?", mapper, id);
	}
	
	@Override
	public Integer updateUserInformation(UpdateUserInformationDTO userInformation) {
		
	    String sql = "update \"user\" set password = ?, email = ?, username = ? where id = ?";
	    System.out.println(userInformation);
	    return jdbcTemplate.update(sql, userInformation.getPassword(), userInformation.getEmail(), userInformation.getUsername(), userInformation.getId());
	}

		
	
	
}
