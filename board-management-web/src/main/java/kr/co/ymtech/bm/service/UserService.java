//package kr.co.ymtech.bm.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import kr.co.ymtech.bm.controller.dto.UserDTO;
//import kr.co.ymtech.bm.repository.IUserRepository;
//import kr.co.ymtech.bm.repository.UserRepository;
//import kr.co.ymtech.bm.repository.vo.UserVO;
//
//public class UserService implements IUserService {
//
//	private final IUserRepository userRepository;
//
//	@Autowired
//	private UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	public String login(UserDTO user) {
//
//		UserVO vo = new UserVO();
//		vo.setId(user.getId());
//		vo.setPassword(user.getPassword());
//		vo.setName(user.getName());
//		vo.setEmail(user.getEmail());
//		vo.setCreateDate(user.getCreateDate());
//		vo.setGradeId(user.getGradeId());
//
//		String login = userRepository.login(vo);
//
//		return login;
//
//	}
//
//}
