package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.ChartDTO;
import kr.co.ymtech.bm.controller.dto.ChartUserDTO;
import kr.co.ymtech.bm.repository.ChartRepository;
import kr.co.ymtech.bm.repository.IChartRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;

/**
 * 회원가입 IUserService 클래스
 * 
 * @author 박상현
 * @since 2024.01.04
 */
@Service
public class ChartService implements IChartService {

	private final IChartRepository chartRepository;

	public ChartService(ChartRepository chartRepository) {
		this.chartRepository = chartRepository;
	}

	/**
	 * @Method chartData 차트에 표시할 데이터를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IChartService#chartData()
	 *
	 * @return 오늘 방문자 수, 오늘 게시글 수, 일주일동안의 방문자와 게시글 데이터를 chart에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 12.
	 */
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

	/**
	 * @Method visitUserData 해당 날짜에 방문한 유저 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IChartService#visitUserData(java.lang.String)
	 *
	 * @param visitDate 방문한 날짜
	 * 
	 * @return 해당 날짜에 방문한 유저 리스트와 유저마다 방문횟수를 chartUser에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 22.
	 */
	@Override
	public ChartUserDTO visitUserData(String visitDate) {

	    Integer visitCount = null;
	    List<UserVO> userList = chartRepository.visitUserData(visitDate);
	    ChartUserDTO chartUser = new ChartUserDTO();

	    Set<String> unduplicateUser = new HashSet<>();
	    List<UserVO> unduplicateUsers = new ArrayList<>();

	    // userList를 순회하면서 중복되지 않은 아이디만 visitCount를 설정하고 unduplicateUsers에 추가
	    for (UserVO vo : userList) {
	        if (unduplicateUser.add(vo.getId())) {
	            visitCount = chartRepository.visitCount(vo.getId(), visitDate);
	            vo.setUserCount(visitCount);
	            unduplicateUsers.add(vo);
	        }
	    }
	    
	    // unduplicateUsers에 추가된 리스트를 userCount가 높은 순서대로 정렬
	    Collections.sort(unduplicateUsers, new Comparator<UserVO>() {
	        public int compare(UserVO user1, UserVO user2) {
	            return Integer.compare(user2.getUserCount(), user1.getUserCount());
	        }
	    });
	    
	    chartUser.setUserList(unduplicateUsers);

	    return chartUser;
	}
	
	/**
	 * @Method postUserData 해당 날짜에 게시글을 작성한 유저 정보를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IChartService#postUserData(java.lang.String)
	 *
	 * @param postDate 게시글 작성 날짜
	 * 
	 * @return 해당 날짜에 게시글을 작성한 유저 리스트와 유저마다 작성한 게시글 수를 chartUser에 담아 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 23.
	 */
	@Override
	public ChartUserDTO postUserData(String postDate) {

	    Integer postCount = null;
	    List<UserVO> userList = chartRepository.postUserData(postDate);
	    ChartUserDTO chartUser = new ChartUserDTO();
	    
	    Set<String> unduplicateUser = new HashSet<>();
	    List<UserVO> unduplicateUsers = new ArrayList<>();

	 // userList를 순회하면서 중복되지 않은 아이디만 postCount를 설정하고 unduplicateUsers에 추가
	    for (UserVO vo : userList) {
	        if (unduplicateUser.add(vo.getId())) {
	        	postCount = chartRepository.postCount(vo.getId(), postDate);
	            vo.setUserCount(postCount);
	            unduplicateUsers.add(vo);
	        }
	    }
	    
	 // unduplicateUsers에 추가된 리스트를 userCount가 높은 순서대로 정렬
	    Collections.sort(unduplicateUsers, new Comparator<UserVO>() {
	        public int compare(UserVO user1, UserVO user2) {
	            return Integer.compare(user2.getUserCount(), user1.getUserCount());
	        }
	    });
	    
	    chartUser.setUserList(unduplicateUsers);

	    return chartUser;
	}

}
