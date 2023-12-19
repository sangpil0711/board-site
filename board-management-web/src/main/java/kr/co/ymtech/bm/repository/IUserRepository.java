package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.repository.vo.UserVO;

public interface IUserRepository {


	UserVO findByUsername(Object principal);


}
