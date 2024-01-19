package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserManageVO;

public interface IUserSystemRepository {

	/**
	 * @Method getUserInfo 사용자 관리 시 유저 리스트를 가져오는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public List<UserManageVO> getUserInfo(Integer pageNumber, Integer itemSize);

	/**
	 * @Method updateGrade 사용자 권한 번호를 수정하는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public Integer updateGrade(UserManageVO vo);

	/**
	 * @Method findCount 사용자 수를 저장하는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 18.
	 */
	public Integer findCount();

	/**
	 * @Method getUserPassword 유저의 비밀번호를 가져오는 메소드
	 *
	 * @param userId 유저 아이디
	 *
	 * @author 박상현
	 * @since 2024. 01. 15.
	 */
	public Integer deleteUser(String id);

}
