package kr.co.ymtech.bm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.InfoDTO;
import kr.co.ymtech.bm.controller.dto.UserUpdateDTO;
import kr.co.ymtech.bm.repository.IInfoRepository;
import kr.co.ymtech.bm.repository.InfoRepository;
import kr.co.ymtech.bm.repository.vo.InfoVO;
import kr.co.ymtech.bm.repository.vo.UserUpdateVO;

@Service
public class InfoService implements IInfoService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private final IInfoRepository infoRepository;

	public InfoService(InfoRepository infoRepository) {
		this.infoRepository = infoRepository;
	}

	/**
	 * @Method getUserInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IInfoService#getUserInfo()
	 *
	 * @return 유저의 정보를 담은 dto를 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 15.
	 */
	@Override
	public InfoDTO getUserInfo() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		InfoVO vo = infoRepository.getUserInfo(auth.getName());
		InfoDTO dto = new InfoDTO();

		// vo -> dto 변환
		dto.setId(vo.getId());
		dto.setUsername(vo.getUsername());
		dto.setEmail(vo.getEmail());

		return dto;
	}

	/**
	 * @Method updateUserInfo 유저의 정보를 업데이트하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.IInfoService#updateUserInfo(kr.co.ymtech.bm.controller.dto.UserUpdateDTO)
	 *
	 * @param updateInfo 업데이트할 유저 정보
	 * 
	 * @return 업데이트할 정보를 담아서 repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 16.
	 */
	@Override
	public Integer updateUserInfo(UserUpdateDTO updateInfo) {

		String encodedPassword = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPassword = infoRepository.getUserPassword(auth.getName());
		UserUpdateVO vo = new UserUpdateVO();

		if (updateInfo.getNewPassword() != null && !checkPassword(updateInfo.getNewPassword())) {
			throw new IllegalArgumentException("비밀번호는 영문, 숫자를 포함하여 8~20자여야 합니다.");
		} else if (updateInfo.getUsername() == null) {
			throw new IllegalArgumentException("이름을 입력해주세요.");
		} else if (updateInfo.getNewPassword() != null && updateInfo.getCurrentPassword() != null
				&& updateInfo.getCurrentPassword().equals(updateInfo.getNewPassword())) {
			throw new IllegalArgumentException("현재비밀번호와 새 비밀번호가 일치합니다.");
		} else if (updateInfo.getNewPassword() != null
				&& !updateInfo.getNewPassword().equals(updateInfo.getNewPasswordCheck())) {
			throw new IllegalArgumentException("비밀번호와 비밀번호확인이 일치하지 않습니다.");
		} else if (updateInfo.getCurrentPassword() != null
				&& !passwordEncoder.matches(updateInfo.getCurrentPassword(), currentPassword)) {
			throw new IllegalArgumentException("현재비밀번호가 일치하지 않습니다.");
		} else if (updateInfo.getCurrentPassword() != null && updateInfo.getNewPassword() == null) {
			throw new IllegalArgumentException("새 비밀번호를 입력해주세요.");
		} else if (updateInfo.getCurrentPassword() == null && updateInfo.getNewPassword() != null) {
			throw new IllegalArgumentException("현재비밀번호를 입력해주세요.");
		} else {
			vo.setId(auth.getName());
			if (updateInfo.getCurrentPassword() == null && updateInfo.getNewPassword() == null) {
				vo.setNewPassword(currentPassword);
			} else {
				encodedPassword = passwordEncoder.encode(updateInfo.getNewPassword());
				vo.setNewPassword(encodedPassword);
			}
			vo.setUsername(updateInfo.getUsername());
			vo.setEmail(updateInfo.getEmail());
		}

		return infoRepository.updateUserInfo(vo);
	}

	private boolean checkPassword(String password) {
		String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
		return password.matches(passwordPattern);
	}

}
