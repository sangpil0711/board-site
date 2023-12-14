package kr.co.ymtech.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {

		ModelAndView model = new ModelAndView();

		// #1. 로그인 하지 않으면, 로그인 페이지가 떠야함
		// #2. 로그인이 성공하면, 메인 페이지가 떠야함,,
		
		// 로그인 되어있는지 검사,,,
		if (true) {
			model.setViewName("main");
		}

		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView();

		// #1. 로그인 하지 않으면, 로그인 페이지가 떠야함
		// #2. 로그인이 성공하면, 메인 페이지가 떠야함,,
		
		// 로그인 되어있는지 검사,,,
		model.setViewName("login");

		return model;
	}

}