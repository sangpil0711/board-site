package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.ChartDTO;
import kr.co.ymtech.bm.controller.dto.ChartUserDTO;

public interface IChartService {

	/**
	 * @Method chartData 차트에 표시할 데이터를 가져오는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	public ChartDTO chartData();
	
	/**
	 * @Method visitUserData 해당 날짜에 방문한 유저 정보를 가져오는 메소드
	 *
	 * @param visitDate 방문한 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	public ChartUserDTO visitUserData(String visitDate);
	
	/**
	 * @Method postUserData 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 메소드
	 *
	 * @param postDate 게시글 작성 날짜
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	public ChartUserDTO postUserData(String postDate);
	
}
