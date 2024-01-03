package kr.co.ymtech.bm.service;


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

	@Override
	public Integer saveUser(UserDTO user) {
		
		UserListVO vo = new UserListVO();
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setUsername(user.getUsername());
		vo.setEmail(user.getEmail());
		vo.setCreateDate(user.getCreateDate());
		vo.setGradeId(user.getGradeId());
		

		return userRepository.saveUser(vo);
	}
}
