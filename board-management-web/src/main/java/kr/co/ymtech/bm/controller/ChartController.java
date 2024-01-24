package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.ChartDTO;
import kr.co.ymtech.bm.controller.dto.ChartUserDTO;
import kr.co.ymtech.bm.service.ChartService;
import kr.co.ymtech.bm.service.IChartService;

@RestController
public class ChartController {

	private final IChartService chartService;

	public ChartController(ChartService chartService) {
		this.chartService = chartService;
	}

	/**
	 * @Method chartData 차트에 표시할 데이터를 가져오는 메소드
	 *
	 * @return ResponseEntity를 사용하여 Repository 함수 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
	@GetMapping(value = "/chart")
	public ResponseEntity<ChartDTO> chartData() {

		ChartDTO chart = chartService.chartData();

		return new ResponseEntity<ChartDTO>(chart, HttpStatus.OK);
	}

	/**
	 * @Method visitUserData 해당 날짜에 방문한 유저 정보를 가져오는 메소드
	 *
	 * @param visitDate 방문한 날짜
	 * 
	 * @return 유저 리스트를 ResponseEntity를 사용하여 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	@GetMapping(value = "/visitUser")
	public ResponseEntity<ChartUserDTO> visitUserData(String visitDate) {

		ChartUserDTO user = chartService.visitUserData(visitDate);

		return new ResponseEntity<ChartUserDTO>(user, HttpStatus.OK);
	}
	
	/**
	 * @Method visitUserData 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 메소드
	 *
	 * @param postDate 게시글 작성 날짜
	 * 
	 * @return 유저 리스트를 ResponseEntity를 사용하여 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	@GetMapping(value = "/postUser")
	public ResponseEntity<ChartUserDTO> postUserData(String postDate) {

		ChartUserDTO user = chartService.postUserData(postDate);

		return new ResponseEntity<ChartUserDTO>(user, HttpStatus.OK);
	}

}
