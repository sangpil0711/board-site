package kr.co.ymtech.bm.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import kr.co.ymtech.bm.controller.dto.UserManageDTO;
import kr.co.ymtech.bm.controller.dto.UserManagePageDTO;
import kr.co.ymtech.bm.service.IUserSystemService;
import kr.co.ymtech.bm.service.UserSystemService;

@RestController
public class UserSystemController {

	private final IUserSystemService userSystemService;
	
	public UserSystemController(UserSystemService userSystemService){
		this.userSystemService = userSystemService;
	}
	
	@GetMapping(value = "/userManage")
	public ResponseEntity<UserManagePageDTO> getUserInfo(Integer pageNumber, Integer itemSize) {

		UserManagePageDTO userList = userSystemService.getUserInfo(pageNumber, itemSize);

		return new ResponseEntity<UserManagePageDTO>(userList, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/gradeUpdate")
	public ResponseEntity<Integer> updateGrade(@RequestBody UserManageDTO updateInfo) {

		Integer updateGrade = userSystemService.updateGrade(updateInfo);

		return new ResponseEntity<Integer>(updateGrade, HttpStatus.OK);
	}
	
	
}
