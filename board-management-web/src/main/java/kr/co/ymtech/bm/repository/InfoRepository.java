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

	/**
	 * @Method getUserInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IInfoRepository#getUserInfo(java.lang.String)
	 *
	 * @param userId 유저 아이디
	 * 
	 * @return 유저 데이터를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
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
	
	/**
	 * @Method updateUserInfo 유저의 정보를 업데이트하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IInfoRepository#updateUserInfo(kr.co.ymtech.bm.repository.vo.UserUpdateVO)
	 *
	 * @param updateInfo 업데이트할 유저의 정보
	 * 
	 * @return 유저의 정보를 업데이트하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	@Override
	public Integer updateUserInfo(UserUpdateVO updateInfo) {
		return jdbcTemplate.update("UPDATE \"user\" SET password = ?, username = ?, email = ? WHERE id = ?", updateInfo.getNewPassword(),
				updateInfo.getUsername(), updateInfo.getEmail(), updateInfo.getId());
	}
	
	/**
	 * @Method getUserPassword 유저의 비밀번호를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IInfoRepository#getUserPassword(java.lang.String)
	 *
	 * @param userId 유저 아이디
	 * 
	 * @return 유저의 비밀번호를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	@Override
	public String getUserPassword(String userId) {
		return jdbcTemplate.queryForObject("SELECT password FROM \"user\" WHERE id = ?", String.class, userId);
	}

}