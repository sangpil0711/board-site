package kr.co.ymtech.bm.service;


import kr.co.ymtech.bm.controller.dto.UserManageDTO;
import kr.co.ymtech.bm.controller.dto.UserManagePageDTO;

public interface IUserSystemService {

	public UserManagePageDTO getUserInfo(Integer pageNumber, Integer itemSize);

	public Integer updateGrade(UserManageDTO updateInfo);

	public Integer deleteUser(String id);
}
