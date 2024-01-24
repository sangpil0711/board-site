package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserVO;

public interface IChartRepository {

	/**
	 * @Method boardVisitor 유저가 게시판에 로그인한 시간을 DB에 추가하는 메소드
	 *
	 * @param loginDate 로그인한 시간
	 *
	 * @author 황상필
	 * @since 2024. 01. 11.
	 */
	public Integer boardVisitor(Long loginDate, String userId);
	
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
	
	/**
	 * @Method visitorDataForWeek 일주일 동안의 방문자 수 데이터를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public List<Integer> visitorDataForWeek();
	
	/**
	 * @Method postDataForWeek 일주일 동안의 게시글 수 데이터를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public List<Integer> postDataForWeek();
	
	/**
	 * @Method visitUserData 해당 날짜에 방문한 유저 정보를 가져오는 메소드
	 *
	 * @param visitDate 방문한 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	public List<UserVO> visitUserData(String visitDate);
	
	/**
	 * @Method visitCount 해당 날짜에 유저가 방문한 횟수를 가져오는 메소드
	 *
	 * @param userId 유저 아이디
	 * @param visitDate 방문한 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	public Integer visitCount(String userId, String visitDate);
	
	/**
	 * @Method postUserData 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 메소드
	 *
	 * @param postDate 게시글 작성 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	public List<UserVO> postUserData(String postDate);
	
	/**
	 * @Method postCount 해당 날짜에 유저가 작성한 게시글 수를 가져오는 메소드
	 *
	 * @param userId 유저 아이디
	 * @param postDate 게시글 작성 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	public Integer postCount(String userId, String postDate);

}
