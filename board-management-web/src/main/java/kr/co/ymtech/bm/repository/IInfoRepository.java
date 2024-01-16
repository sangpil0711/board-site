package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.InfoVO;
import kr.co.ymtech.bm.repository.vo.UserUpdateVO;

public interface IInfoRepository {

	/**
	 * @Method getUserInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 메소드
	 *
	 * @param userId 유저 아이디
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	public InfoVO getUserInfo(String userId);
	
	/**
	 * @Method updateUserInfo 유저의 정보를 업데이트하는 메소드
	 *
	 * @param updateInfo 업데이트할 유저의 정보
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	public Integer updateUserInfo(UserUpdateVO updateInfo);
	
	/**
	 * @Method getUserPassword 유저의 비밀번호를 가져오는 메소드
	 *
	 * @param userId 유저 아이디
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	public String getUserPassword(String userId);

}
