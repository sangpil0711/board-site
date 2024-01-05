package kr.co.ymtech.bm.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.repository.IUserRepository;
import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserListVO;

/**
 * 회원가입 IUserService 클래스
 * 
 * @author 박상현
 * @since  2024.01.04
 */
@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * @Method checkUserId 입력된 아이디가 이미 생성된 아이디인지 확인하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IUserService#checkUserId(java.lang.String)
	 *
	 * @param userId 입력된 아이디
	 * 
	 * @return 입력된 아이디가 존재하는지 확인하는 Repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	public Integer checkUserId(String userId) {
		return userRepository.checkUserId(userId);
	}


	/**
	 * Method : 사용자 정보를 저장하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IUserService#saveUser(kr.co.ymtech.bm.controller.dto.UserDTO)
	 *
	 * @author 박상현
	 * @since 2024. 1. 5.
	 */
	@Override
	public Integer saveUser(UserDTO user) {
		if (!checkPassword(user.getPassword())) {
			throw new IllegalArgumentException("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
			
		}
		//dto -> vo 변환
		UserListVO vo = new UserListVO();
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setUsername(user.getUsername());
		vo.setEmail(user.getEmail());
		vo.setCreateDate(new Date().getTime());
		vo.setGradeId(1);
		

		return userRepository.saveUser(vo);
	}

	private boolean checkPassword(String password) {
		// 비밀번호 유효성 검사를 위한 정규표현식
		String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
		return password.matches(passwordPattern);
	}
}
