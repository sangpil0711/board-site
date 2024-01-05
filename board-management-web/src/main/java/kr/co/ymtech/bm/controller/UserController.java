package kr.co.ymtech.bm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.service.IUserService;
import kr.co.ymtech.bm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final IUserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @Method loginException 로그인 실패 시 예외를 표시하는 메소드
	 *
	 * @param request http 요청
	 * @param error 예외 발생 여부
	 * @param exception 예외 설명
	 * 
	 * @return 입력한 아이디와 예외를 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	@GetMapping("/login/error")
	public ModelAndView loginException(HttpServletRequest request, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {

		ModelAndView model = new ModelAndView("login");

		String saveUsername = (String) request.getSession().getAttribute("username");
		model.addObject("username", saveUsername);

		model.addObject("error", error);
		model.addObject("exception", exception);

		return model;
	}

	/**
	 * 
	 * @Method getUserId 현재 로그인한 유저 아이디를 가져오는 메소드
	 *
	 * @param request http 요청
	 * 
	 * @return 현재 로그인한 유저 아이디를 JSON 형식으로 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 04.
	 */
	@GetMapping(value = "/loginId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUserId(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<String>("\"" + auth.getName() + "\"", HttpStatus.OK);
	}

	/**
	 * @Method checkUserId 입력된 아이디가 이미 생성된 아이디인지 확인하는 메소드
	 *
	 * @param userId 입력된 아이디
	 * 
	 * @return checkId 변수가 1이면 http 상태코드 502를 반환하고 0이면 http 상태코드 200을 반환
	 *
	 * @author 황상필
	 * @since 2024. 1. 4.
	 */
	@PostMapping(value = "/checkId")
	public ResponseEntity<Integer> checkUserId(@RequestBody String userId) {
		
		Integer checkId = userService.checkUserId(userId);
		
		if (checkId == 1) {
			return new ResponseEntity<Integer>(checkId, HttpStatus.BAD_GATEWAY);
		} else {
			return new ResponseEntity<Integer>(checkId, HttpStatus.OK);
		}
	}

	/**
	 * Method : 사용자 정보를 저장하는 메소드
	 * 
	 * @param user : 사용자 정보
	 * @return 사용자 정보를 DB에 저장 하고 성공하면 1 실패하면 0 을 userList에 담아 반환
	 *
	 * @author 박상현
	 * @since 2024. 1. 4.
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<Integer> saveUser(@RequestBody UserDTO user) {

		Integer userList = userService.saveUser(user);

		return new ResponseEntity<Integer>(userList, HttpStatus.OK);
	}

}
