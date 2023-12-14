package kr.co.ymtech.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/**
	 * @Method homepage "/" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @return "main.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView homepage() {

		ModelAndView model = new ModelAndView();

		model.setViewName("main");
		
		return model;


		
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView();

		model.setViewName("login");

		return model;
	}

}
