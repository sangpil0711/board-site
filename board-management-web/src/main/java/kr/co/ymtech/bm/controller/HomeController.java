package kr.co.ymtech.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.repository.IUserRepository;
import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;

@Controller
public class HomeController {

	@Autowired
	private final IUserRepository userRepository;

	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// 로그인 여부 확인
		if (auth == null || !UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			model.setViewName("login"); // 로그인 안되어 있으면 로그인 페이지로 이동
		} else {

			if (auth.getAuthorities().isEmpty()) {
				model.setViewName("login");
			} else {
				model.setViewName("main");

				String username = auth.getName();
				UserVO userVO = userRepository.findByUsername(username);

				model.addObject("UserVO", userVO);
			}
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		model.setViewName("signup");
		return model;
	}
}
