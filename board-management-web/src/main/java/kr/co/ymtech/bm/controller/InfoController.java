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

	@GetMapping(value = "/userInfo")
	public ResponseEntity<InfoDTO> getUserInfo() {

		InfoDTO userInfo = infoService.getUserInfo();

		return new ResponseEntity<InfoDTO>(userInfo, HttpStatus.OK);
	}

	@PatchMapping(value = "/userUpdate")
	public ResponseEntity<Integer> updateUserInfo(@RequestBody UserUpdateDTO updateInfo) {

		Integer userInfo = infoService.updateUserInfo(updateInfo);

		return new ResponseEntity<Integer>(userInfo, HttpStatus.OK);
	}

}
