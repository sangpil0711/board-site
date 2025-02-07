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

	public UserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @Method findByUsername 사용자 아이디를 기반으로 사용자 정보 조회하는 메소드 
	 *
	 * @see kr.co.ymtech.bm.repository.IUserRepository#findByUsername(java.lang.String)
	 *
	 * @param id 사용자 아이디
	 * 
	 * @return 사용자 아이디를 기반으로 사용자 정보 조회하여 사용자 정보들을 vo로 반환
	 *
	 * @author 박상현
	 * @since 2024. 01. 04.
	 */
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

	/**
	 * @Method checkUserId 입력된 아이디가 이미 생성된 아이디인지 확인하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IUserRepository#checkUserId(java.lang.String)
	 *
	 * @param userId 입력된 아이디
	 * 
	 * @return 이미 생성된 아이디면 1을 반환하고 아니면 0을 반환하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	public Integer checkUserId(String userId) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM \"user\" WHERE id = ?", Integer.class, userId);
	}

	/**
	 * Method : 사용자 정보를 저장하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IUserRepository#saveUser(kr.co.ymtech.bm.repository.vo.UserListVO)
	 * 
	 * @return DB에 사용자 정보를 저장
	 *
	 * @author 박상현
	 * @since 2024. 1. 5.
	 */
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
	
}