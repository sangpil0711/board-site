package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.service.IUserService;
import kr.co.ymtech.bm.service.UserService;

@RestController
public class UserController {

	private final IUserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(UserDTO user) {

		String login = userService.login(user);

		return new ResponseEntity<String>(login, HttpStatus.OK);

	}

}
