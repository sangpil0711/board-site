package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserManageVO;

public interface IUserSystemRepository {

	public List<UserManageVO> getUserInfo();

	public Integer updateGrade(UserManageVO vo);

}
