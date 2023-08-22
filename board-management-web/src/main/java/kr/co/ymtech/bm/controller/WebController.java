package kr.co.ymtech.bm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ymtech.bm.controller.dto.BoardDTO;

@Controller
public class WebController {
	
	@RequestMapping( value="/",method = RequestMethod.GET)
	public String homepage() {
		return "index";   // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	@RequestMapping( value="/board",method = RequestMethod.GET)
	public ResponseEntity<List<BoardDTO>> getBoard() {
		List<BoardDTO> list = new ArrayList<>();
		
		// #1. board DTO 객체 클래스 파일 생성, 정의
		// #2. list에 board DTO 객체 데이터 삽입
		// #3. 반환
		
		return new ResponseEntity<List<BoardDTO>>(list, HttpStatus.OK);   // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
}
