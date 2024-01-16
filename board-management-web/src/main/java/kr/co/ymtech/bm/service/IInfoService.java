package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.InfoDTO;
import kr.co.ymtech.bm.controller.dto.UserUpdateDTO;

public interface IInfoService {

	public InfoDTO getUserInfo();
	
	public Integer updateUserInfo(UserUpdateDTO updateInfo);

}
