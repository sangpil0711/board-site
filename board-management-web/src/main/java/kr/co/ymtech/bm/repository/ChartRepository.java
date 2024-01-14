package kr.co.ymtech.bm.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	public Integer boardVisitor(Long loginDate) {
		return jdbcTemplate.update("INSERT INTO visitor (date) VALUES (?)", loginDate);
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
				"SELECT COUNT(*) FROM visitor WHERE TO_TIMESTAMP(date / 1000)::date = TO_DATE(?, 'YYYYMMDD')",
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
		return jdbcTemplate.update("DELETE FROM visitor WHERE TO_TIMESTAMP(date / 1000) > CURRENT_STAMP + '-7days'");
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
		List<Integer> visitorData = new ArrayList<Integer>();
		String sql = "SELECT COUNT(*) FROM visitor WHERE TO_TIMESTAMP(date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		for (int i = 6; i >= 0; i--) {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DAY_OF_MONTH, -i);
	        String nDayAgo = DATE_FORMAT.format(c1.getTime());
			
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
		List<Integer> postData = new ArrayList<Integer>();
		String sql = "SELECT COUNT(*) FROM board WHERE TO_TIMESTAMP(create_date / 1000)::date = TO_DATE(?, 'YYYYMMDD')";

		for (int i = 6; i >= 0; i--) {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DAY_OF_MONTH, -i);
	        String nDayAgo = DATE_FORMAT.format(c1.getTime());
			
			postData.add(jdbcTemplate.queryForObject(sql, Integer.class, nDayAgo));
		}

		return postData;
	}

}