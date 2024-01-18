package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserManageVO;

public interface IUserSystemRepository {

	public List<UserManageVO> getUserInfo(Integer pageNumber, Integer itemSize);

	public Integer updateGrade(UserManageVO vo);

	public Integer findCount();

}
