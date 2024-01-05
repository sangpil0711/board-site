package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.UserListVO;
import kr.co.ymtech.bm.repository.vo.UserVO;

/**
 * 로그인/회원가입 IUserRepository 인터페이스
 * 
 * @author 박상현
 * @since  2024.01.04
 */
public interface IUserRepository {

	/**
	 * Method : 로그인 한 사용자가 일치하는지 확인하는 메소드
	 * 	 
	 * @param username : 사용자 정보
	 *
	 * @author 박상현
	 * @since 2024. 1. 3.
	 */
	public UserVO findByUsername(String username);
	
	public Integer checkUserId(String userId);

	/**
	 * Method : 사용자 정보를 저장하는 메소드
	 * 
	 * @param user : 사용자 정보
	 *
	 * @author 박상현
	 * @since 2024. 1. 4.
	 */
	public Integer saveUser(UserListVO user);

}
