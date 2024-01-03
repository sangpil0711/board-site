package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.UserListVO;
import kr.co.ymtech.bm.repository.vo.UserVO;

public interface IUserRepository {

	public UserVO findByUsername(String username);

	public Integer saveUser(UserListVO user);

}
