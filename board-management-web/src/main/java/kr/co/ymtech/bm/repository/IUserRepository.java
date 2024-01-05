package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.UserListVO;
import kr.co.ymtech.bm.repository.vo.UserVO;

public interface IUserRepository {

	UserVO findByUsername(String username);
	
	/**
	 * @Method checkUserId 입력된 아이디가 이미 생성된 아이디인지 확인하는 메소드
	 *
	 * @param userId 입력된 아이디
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	public Integer checkUserId(String userId);

	public Integer saveUser(UserListVO user);

}
