package kr.co.ymtech.bm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.ChartDTO;
import kr.co.ymtech.bm.repository.ChartRepository;
import kr.co.ymtech.bm.repository.IChartRepository;

/**
 * 회원가입 IUserService 클래스
 * 
 * @author 박상현
 * @since  2024.01.04
 */
@Service
public class ChartService implements IChartService {

	private final IChartRepository chartRepository;

	public ChartService(ChartRepository chartRepository) {
		this.chartRepository = chartRepository;
	}
	
	@Override
	public ChartDTO chartData() {
		
		Integer countVisitor = chartRepository.todayVisitor();
		Integer countPost = chartRepository.todayPost();
		List<Integer> visitorDataForWeek = chartRepository.visitorDataForWeek();
		List<Integer> postDataForWeek = chartRepository.postDataForWeek();
		
		ChartDTO chart = new ChartDTO();
		chart.setCountVisitor(countVisitor);
		chart.setCountPost(countPost);
		chart.setVisitorData(visitorDataForWeek);
		chart.setPostData(postDataForWeek);
		
		return chart;
	}

}
