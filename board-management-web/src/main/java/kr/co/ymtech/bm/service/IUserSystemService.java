package kr.co.ymtech.bm.service;

import java.util.List;

import kr.co.ymtech.bm.controller.dto.UserManageDTO;

public interface IUserSystemService {

	public List<UserManageDTO> getUserInfo();

	public Integer updateGrade(UserManageDTO updateInfo);
}
