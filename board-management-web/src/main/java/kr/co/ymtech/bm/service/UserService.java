package kr.co.ymtech.bm.service;


import java.util.Date;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.repository.IUserRepository;
import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserListVO;

@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Integer checkUserId(String userId) {
		return userRepository.checkUserId(userId);
	}

	@Override
	public Integer saveUser(UserDTO user) {
		
		UserListVO vo = new UserListVO();
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setUsername(user.getUsername());
		vo.setEmail(user.getEmail());
		vo.setCreateDate(new Date().getTime());
		vo.setGradeId(1);
		

		return userRepository.saveUser(vo);
	}
}
