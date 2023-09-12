package kr.co.ymtech.bm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@GetMapping(value = "/")
	public ModelAndView homepage(ModelAndView modelAndView) {
		modelAndView.addObject("name","이순신");
		modelAndView.addObject("age",29);
		modelAndView.setViewName("model");
		return modelAndView; 
	
	
}
}
