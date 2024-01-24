package kr.co.ymtech.bm.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.UserVO;

@Repository
public class ChartRepository implements IChartRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	/**
	 * @Method boardVisitor 유저가 게시판에 로그인한 시간을 DB에 추가하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#boardVisitor(java.lang.Long)
	 *
	 * @param loginDate 로그인한 시간
	 * 
	 * @return 로그인한 시간을 DB에 추가하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 11.
	 */
	@Override
	public Integer boardVisitor(Long loginDate, String userId) {
		return jdbcTemplate.update("INSERT INTO visitor (visit_date, id) VALUES (?, ?)", loginDate, userId);
	}

	/**
	 * @Method todayVisitor 오늘 방문자 수를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#todayVisitor()
	 *
	 * @return 오늘 방문자 수를 조회하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@Override
	public Integer todayVisitor() {
		Calendar c1 = Calendar.getInstance();
		String today = DATE_FORMAT.format(c1.getTime());

		return jdbcTemplate.queryForObject(
				"SELECT COUNT(DISTINCT id) FROM visitor WHERE TO_TIMESTAMP(visit_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')",
				Integer.class, today);
	}

	/**
	 * @Method todayPost 오늘 생성된 게시글 수를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#todayPost()
	 *
	 * @return 오늘 생성된 게시글 수를 조회하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@Override
	public Integer todayPost() {
		Calendar c1 = Calendar.getInstance();
		String today = DATE_FORMAT.format(c1.getTime());

		return jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM board WHERE TO_TIMESTAMP(create_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')",
				Integer.class, today);
	}

	/**
	 * @Method deleteVisitData 일주일이 지난 로그인 정보를 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#deleteVisitData()
	 *
	 * @return 일주일이 지난 로그인 정보를 삭제하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@Override
	public Integer deleteVisitData() {
		return jdbcTemplate
				.update("DELETE FROM visitor WHERE TO_TIMESTAMP(visit_date / 1000) < CURRENT_TIMESTAMP + '-7days'");
	}

	/**
	 * @Method visitorDataForWeek 일주일 동안의 방문자 수 데이터를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#visitorDataForWeek()
	 *
	 * @return 일주일 동안의 방문자 수를 리스트에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@Override
	public List<Integer> visitorDataForWeek() {
		String nDayAgo = null;
		List<Integer> visitorData = new ArrayList<Integer>();
		String sql = "SELECT COUNT(DISTINCT id) FROM visitor WHERE TO_TIMESTAMP(visit_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		for (int i = 6; i >= 0; i--) {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DAY_OF_MONTH, -i);
			nDayAgo = DATE_FORMAT.format(c1.getTime());

			visitorData.add(jdbcTemplate.queryForObject(sql, Integer.class, nDayAgo));
		}

		return visitorData;
	}

	/**
	 * @Method postDataForWeek 일주일 동안의 게시글 수 데이터를 조회하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.repository.IChartRepository#postDataForWeek()
	 *
	 * @return 일주일 동안의 게시글 수를 리스트에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@Override
	public List<Integer> postDataForWeek() {
		String nDayAgo = null;
		List<Integer> postData = new ArrayList<Integer>();
		String sql = "SELECT COUNT(*) FROM board WHERE TO_TIMESTAMP(create_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		for (int i = 6; i >= 0; i--) {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DAY_OF_MONTH, -i);
			nDayAgo = DATE_FORMAT.format(c1.getTime());

			postData.add(jdbcTemplate.queryForObject(sql, Integer.class, nDayAgo));
		}

		return postData;
	}

	/**
	 * @Method userData 해당 날짜에 방문한 유저 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#userData(java.lang.String)
	 *
	 * @param visitDate 방문한 날짜
	 * 
	 * @return 해당 날짜에 방문한 유저 정보를 반환하는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	@Override
	public List<UserVO> visitUserData(String visitDate) {

		String sql = "SELECT * FROM visitor JOIN \"user\" ON visitor.id = \"user\".id WHERE TO_TIMESTAMP(visit_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		RowMapper<UserVO> mapper = new RowMapper<UserVO>() {
			@Override
			public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVO member = new UserVO(rs.getString("id"), rs.getString("password"), rs.getString("username"),
						rs.getString("email"), rs.getLong("create_date"), rs.getInt("grade_id"));
				return member;
			}
		};

		return jdbcTemplate.query(sql, mapper, visitDate);
	}
	
	/**
	 * @Method visitCount 해당 날짜에 유저가 방문한 횟수를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#visitCount(java.lang.String, java.lang.String)
	 *
	 * @param userId 유저 아이디
	 * @param visitDate 방문한 날짜
	 * 
	 * @return 해당 날짜에 유저가 방문한 횟수를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	@Override
	public Integer visitCount(String userId, String visitDate) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM visitor WHERE id = ? AND TO_TIMESTAMP(visit_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')", Integer.class, userId, visitDate);
	}
	
	/**
	 * @Method postUserData 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#postUserData(java.lang.String)
	 *
	 * @param postDate 게시글 작성 날짜
	 * 
	 * @return 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	@Override
	public List<UserVO> postUserData(String postDate) {
		
		String sql = "SELECT * FROM board JOIN \"user\" ON board.user_id = \"user\".id WHERE TO_TIMESTAMP(board.create_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		RowMapper<UserVO> mapper = new RowMapper<UserVO>() {
			@Override
			public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVO member = new UserVO(rs.getString("id"), rs.getString("password"), rs.getString("username"),
						rs.getString("email"), rs.getLong("create_date"), rs.getInt("grade_id"));
				return member;
			}
		};

		return jdbcTemplate.query(sql, mapper, postDate);
	}
	
	/**
	 * @Method postCount 해당 날짜에 유저가 작성한 게시글 수를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.repository.IChartRepository#postCount(java.lang.String, java.lang.String)
	 *
	 * @param userId 유저 아이디
	 * @param postDate 게시글 작성 날짜
	 * 
	 * @return 해당 날짜에 유저가 작성한 게시글 수를 가져오는 query 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	@Override
	public Integer postCount(String userId, String postDate) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM board WHERE user_id = ? AND TO_TIMESTAMP(create_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')", Integer.class, userId, postDate);
	}

}