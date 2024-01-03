package kr.co.ymtech.bm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.service.IUserService;
import kr.co.ymtech.bm.service.UserService;

@RestController
public class UserController {

	private final IUserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login/error")
	public ModelAndView login(HttpServletRequest request, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {

		ModelAndView model = new ModelAndView("login");

		String saveUsername = (String) request.getSession().getAttribute("username");
		model.addObject("username", saveUsername);

		model.addObject("error", error);
		model.addObject("exception", exception);

		return model;
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<Integer> saveUser(@RequestBody UserDTO user) {
		
		Integer userList = userService.saveUser(user);
		
		return new ResponseEntity<Integer>(userList, HttpStatus.OK);
	}
	
	
	

}
