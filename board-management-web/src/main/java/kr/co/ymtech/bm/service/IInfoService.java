package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.InfoDTO;
import kr.co.ymtech.bm.controller.dto.UserUpdateDTO;

public interface IInfoService {

	/**
	 * @Method getUserInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 메소드
	 *
	 * @author 박상현
	 * @since 2024. 01. 15.
	 */
	public InfoDTO getUserInfo();
	
	/**
	 * @Method updateUserInfo 유저의 정보를 업데이트하는 메소드
	 *
	 * @param updateInfo 업데이트할 유저 정보
	 * 
	 * @author 박상현
	 * @since 2024. 01. 15.
	 */
	public Integer updateUserInfo(UserUpdateDTO updateInfo);

}
