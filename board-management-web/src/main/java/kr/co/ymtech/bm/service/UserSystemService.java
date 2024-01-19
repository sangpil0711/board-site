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

	/**
	 * @Method getUserInfo 사용자 관리 시 유저 리스트를 가져오는 메소드
	 * 
	 * @see kr.co.ymtech.bm.service.IUserSystemService#getUserInfo(java.lang.Integer, java.lang.Integer)
	 * 
	 * @return 유저 리스트와 유저 수 를 반환
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public UserManagePageDTO getUserInfo(Integer pageNumber, Integer itemSize) {

		List<UserManageVO> userInfoList = userSystemRepository.getUserInfo(pageNumber, itemSize);
		Integer totalCount = userSystemRepository.findCount();
		
		
		UserManagePageDTO userPage = new UserManagePageDTO();
		
		userPage.setUserList(userInfoList);
		userPage.setTotalCount(totalCount);
			
		
		return userPage;

	}
	
	/**
	 * @Method updateGrade 사용자 권한 번호를 수정하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.service.IUserSystemService#updateGrade(kr.co.ymtech.bm.controller.dto.UserManageDTO)
	 * 
	 * @return id 에 맞는 사용자를 찾아 권한번호를 수정하여 반환
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public Integer updateGrade(UserManageDTO updateInfo) {
		
		UserManageVO vo  = new UserManageVO();
		vo.setId(updateInfo.getId());
		vo.setGradeId(updateInfo.getGradeId());
		
		return userSystemRepository.updateGrade(vo);
	}
	
	/**
	 * @Method deleteUser 사용자를 삭제하는 메소드
	 * 
	 * @see kr.co.ymtech.bm.service.IUserSystemService#deleteUser(java.lang.String)
	 * 
	 * @return 맞는 아이디를 찾아 사용자 데이터를 삭제하고 반환
	 *
	 * @author 박상현
	 * @since 2024. 1. 19.
	 */
	@Override
	public Integer deleteUser(String id) {
		return userSystemRepository.deleteUser(id);
	}

}
