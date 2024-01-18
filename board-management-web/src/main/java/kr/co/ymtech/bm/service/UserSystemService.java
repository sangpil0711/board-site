package kr.co.ymtech.bm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.controller.dto.UserManageDTO;
import kr.co.ymtech.bm.controller.dto.UserManagePageDTO;
import kr.co.ymtech.bm.repository.IUserSystemRepository;
import kr.co.ymtech.bm.repository.vo.UserManageVO;

@Service
public class UserSystemService implements IUserSystemService {

	private final IUserSystemRepository userSystemRepository;

	@Autowired
	private UserSystemService(IUserSystemRepository IuserSystemRepository) {
		this.userSystemRepository = IuserSystemRepository;
	}

	@Override
	public UserManagePageDTO getUserInfo(Integer pageNumber, Integer itemSize) {

		List<UserManageVO> userInfoList = userSystemRepository.getUserInfo(pageNumber, itemSize);
		Integer totalCount = userSystemRepository.findCount();
		
		
		UserManagePageDTO userPage = new UserManagePageDTO();
		
		userPage.setUserList(userInfoList);
		userPage.setTotalCount(totalCount);
			
		
		return userPage;

	}
	
	@Override
	public Integer updateGrade(UserManageDTO updateInfo) {
		
		UserManageVO vo  = new UserManageVO();
		vo.setId(updateInfo.getId());
		vo.setGradeId(updateInfo.getGradeId());
		
		return userSystemRepository.updateGrade(vo);
	}

}
