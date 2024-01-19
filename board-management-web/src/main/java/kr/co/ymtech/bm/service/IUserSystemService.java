package kr.co.ymtech.bm.service;


import kr.co.ymtech.bm.controller.dto.UserManageDTO;
import kr.co.ymtech.bm.controller.dto.UserManagePageDTO;

public interface IUserSystemService {

	/**
	 * @Method getUserInfo 사용자 관리 시 유저 리스트를 가져오는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public UserManagePageDTO getUserInfo(Integer pageNumber, Integer itemSize);
	
	/**
	 * @Method updateGrade 사용자 권한 번호를 수정하는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public Integer updateGrade(UserManageDTO updateInfo);

	/**
	 * @Method deleteUser 사용자를 삭제하는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public Integer deleteUser(String id);
}
