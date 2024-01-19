package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.InfoDTO;
import kr.co.ymtech.bm.controller.dto.UserUpdateDTO;
import kr.co.ymtech.bm.service.IInfoService;
import kr.co.ymtech.bm.service.InfoService;

@RestController
public class InfoController {

	private final IInfoService infoService;

	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}

	/**
	 * @Method getUserInfo 개인정보수정 시 필요한 유저 데이터를 가져오는 메소드
	 *
	 * @return 유저 데이터를 ResponseEntity를 통해 반환
	 *
	 * @author 박상현
	 * @since 2024. 01. 15.
	 */
	@GetMapping(value = "/userInfo")
	public ResponseEntity<InfoDTO> getUserInfo() {

		InfoDTO userInfo = infoService.getUserInfo();

		return new ResponseEntity<InfoDTO>(userInfo, HttpStatus.OK);
	}

	/**
	 * @Method updateUserInfo 유저의 정보를 업데이트하는 메소드
	 *
	 * @param updateInfo 업데이트할 유저 정보
	 * 
	 * @return 업대이트할 유저 정보를 ResponseEntity를 통해 반환
	 *
	 * @author 박상현
	 * @since 2024. 01. 15.
	 */
	@PatchMapping(value = "/userUpdate")
	public ResponseEntity<Integer> updateUserInfo(@RequestBody UserUpdateDTO updateInfo) {

		Integer userInfo = infoService.updateUserInfo(updateInfo);

		return new ResponseEntity<Integer>(userInfo, HttpStatus.OK);
	}

}
