package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.InfoVO;
import kr.co.ymtech.bm.repository.vo.UserUpdateVO;

public interface IInfoRepository {

	public InfoVO getUserInfo(String userId);
	
	public Integer updateUserInfo(UserUpdateVO updateInfo);
	
	public String getUserPassword(String userId);

}
