package kr.co.ymtech.bm.repository;

import java.util.List;

public interface IChartRepository {

	/**
	 * @Method boardVisitor 유저가 게시판에 로그인한 시간을 DB에 추가하는 메소드
	 *
	 * @param loginDate 로그인한 시간
	 *
	 * @author 황상필
	 * @since 2024. 01. 11.
	 */
	public Integer boardVisitor(Long loginDate);
	
	/**
	 * @Method todayVisitor 오늘 방문자 수를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public Integer todayVisitor();
	
	/**
	 * @Method todayPost 오늘 생성된 게시글 수를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public Integer todayPost();
	
	/**
	 * @Method deleteVisitData 일주일이 지난 로그인 정보를 삭제하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public Integer deleteVisitData();
	
	public List<Integer> visitorDataForWeek();
	
	public List<Integer> postDataForWeek();

}
