package com.javasampleapproach.angularjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "index"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
}