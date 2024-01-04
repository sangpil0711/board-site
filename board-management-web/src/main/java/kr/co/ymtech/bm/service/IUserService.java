package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.UserDTO;

public interface IUserService {

	public Integer checkUserId(String userId);
	
	public Integer saveUser(UserDTO user);


}
