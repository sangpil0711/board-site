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

	/**
	 * @Method createSystem 시스템을 생성하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.ISystemRepository#createSystem(kr.co.ymtech.bm.repository.vo.SystemVO)
	 *
	 * @param system 생성할 시스템 정보
	 * 
	 * @return 시스템을 생성하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer createSystem(SystemVO system) {
		return jdbcTemplate.update("INSERT INTO property(key, value, description, create_date) VALUES(?, ?, ?, ?)",
				system.getKey(), system.getValue(), system.getDescription(), system.getCreateDate());
	}

	/**
	 * @Method findPage 시스템 정보를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.ISystemRepository#findPage()
	 * 
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 수
	 *
	 * @return 시스템 정보를 조회하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public List<SystemVO> findPage(Integer pageNumber, Integer itemSize) {
		
		Integer offset = (pageNumber - 1) * itemSize;

		RowMapper<SystemVO> mapper = new RowMapper<SystemVO>() {
			@Override
			public SystemVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SystemVO member = new SystemVO(rs.getString("key"), rs.getString("value"), rs.getString("description"));
				return member;
			}
		};

		return jdbcTemplate.query("SELECT * FROM property ORDER BY create_date DESC OFFSET ? LIMIT ?", mapper, offset, itemSize);
	}

	/**
	 * @Method deleteSystem 시스템을 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.ISystemRepository#deleteSystem(java.lang.String)
	 *
	 * @param key 삭제할 시스템 키
	 * 
	 * @return 시스템을 삭제하는 query 함수 실행 
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer deleteSystem(String key) {
		return jdbcTemplate.update("DELETE FROM property WHERE key = ?", key);
	}

	/**
	 * @Method updateSystem 시스템을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.ISystemRepository#updateSystem(kr.co.ymtech.bm.repository.vo.SystemVO)
	 *
	 * @param system 수정할 시스템
	 * 
	 * @return 시스템을 수정하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer updateSystem(SystemVO system) {
		
		return jdbcTemplate.update("UPDATE property SET value = ?, description = ?, update_date = ? WHERE key = ?", system.getValue(),
				system.getDescription(), system.getUpdateDate(), system.getKey());
	}
	
	@Override
	public Integer findCount() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM property", Integer.class);
	}

}