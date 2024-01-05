package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.UserDTO;

/**
 * 회원가입 IUserService 인터페이스
 * 
 * @author 박상현
 * @since  2024.01.04
 */
public interface IUserService {

	public Integer checkUserId(String userId);
	
	/**
	 * Method : 사용자 정보를 저장하는 메소드
	 * 
	 * @param user : 사용자 정보
	 *
	 * @author 박상현
	 * @since 2024. 1. 4.
	 */
	public Integer saveUser(UserDTO user);


}
