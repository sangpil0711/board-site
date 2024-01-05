package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.UserDTO;

/**
 * 회원가입 IUserService 인터페이스
 * 
 * @author 박상현
 * @since  2024.01.04
 */
public interface IUserService {

	/**
	 * @Method checkUserId 입력된 아이디가 이미 생성된 아이디인지 확인하는 메소드
	 *
	 * @param userId 입력된 아이디
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
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
