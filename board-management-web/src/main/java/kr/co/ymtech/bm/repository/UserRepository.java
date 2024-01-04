package kr.co.ymtech.bm.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.co.ymtech.bm.repository.vo.UserListVO;
import kr.co.ymtech.bm.repository.vo.UserVO;

@Repository
public class UserRepository implements IUserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final DataSource dataSource;

	// 생성자를 통해 DataSource나 Connection을 주입받음
	public UserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 사용자 아이디를 기반으로 사용자 정보 조회
	public UserVO findByUsername(String id) {
		String query = "SELECT * FROM \"user\" INNER JOIN (SELECT * FROM grade) AS user_grade ON \"user\".grade_id = user_grade.id WHERE \"user\".id = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					UserVO vo = new UserVO();
					vo.setId(resultSet.getString("id"));
					vo.setPassword(resultSet.getString("password"));
					vo.setUsername(resultSet.getString("username"));
					vo.setEmail(resultSet.getString("email"));
					vo.setCreateDate(resultSet.getLong("create_date"));
					vo.setGradeId(resultSet.getInt("grade_id"));
					vo.setName(resultSet.getString("name"));
					vo.setDescription(resultSet.getString("description"));

					return vo;
				}
			}
		} catch (SQLException e) {
			// SQLException 처리
			e.printStackTrace();
		}

		return null; // 사용자가 존재하지 않는 경우
	}

	@Override
	public Integer saveUser(UserListVO user) {
		
		return jdbcTemplate.update(
	            "INSERT INTO \"user\" (id, password, username, email, create_date) VALUES (?, ?, ?, ?, ?)",
	            user.getId(),
	            user.getPassword(),
	            user.getUsername(),
	            user.getEmail(),
	            user.getCreateDate()
	    );
	}
	
	public Integer checkUserId(String userId) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM \"user\" WHERE id = ?", Integer.class, userId);
	}
}