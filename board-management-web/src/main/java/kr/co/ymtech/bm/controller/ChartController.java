package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.ChartDTO;
import kr.co.ymtech.bm.service.ChartService;
import kr.co.ymtech.bm.service.IChartService;

@RestController
public class ChartController {

	private final IChartService chartService;

	public ChartController(ChartService chartService) {
		this.chartService = chartService;
	}

	@GetMapping(value = "/visitor")
	public ResponseEntity<ChartDTO> todayVisitor() {

		ChartDTO countVisitor = chartService.chartData();

		return new ResponseEntity<ChartDTO>(countVisitor, HttpStatus.OK);
	}

}
