package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.controller.dto.UserManageDTO;
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
	public List<UserManageDTO> getUserInfo() {

		List<UserManageVO> userInfoList = userSystemRepository.getUserInfo();

		List<UserManageDTO> findUserInfo = new ArrayList<>();

		for (UserManageVO vo : userInfoList) {

			UserManageDTO dto = new UserManageDTO();
			dto.setId(vo.getId());
			dto.setUsername(vo.getUsername());
			dto.setEmail(vo.getEmail());
			dto.setGradeId(vo.getGradeId());
			
			findUserInfo.add(dto);

		}
		return findUserInfo;

	}
	
	@Override
	public Integer updateGrade(UserManageDTO updateInfo) {
		
		UserManageVO vo  = new UserManageVO();
		vo.setId(updateInfo.getId());
		vo.setGradeId(updateInfo.getGradeId());
		
		return userSystemRepository.updateGrade(vo);
	}

}
