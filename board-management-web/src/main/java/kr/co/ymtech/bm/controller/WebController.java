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
	
	// Restful API 생성 - 게시판 목록 조회
	@RequestMapping( value="/board",method = RequestMethod.GET)
	public ResponseEntity<List<BoardDTO>> getBoard() {
		List<BoardDTO> list = new ArrayList<>();
		
		// #1. board DTO 객체 클래스 파일 생성, 정의
		// 완료
		// #2. list에 board DTO 객체 데이터 삽입
		BoardDTO dto1 = new BoardDTO(); // 객체 생성
		dto1.setIndex(1); // 값 세팅
		dto1.setTitle("Sample Title 1");
	    dto1.setText("Text 1");
	    dto1.setUserId("user1");
	    dto1.setCategory(1);
	    dto1.setCreateDate(System.currentTimeMillis());
	    list.add(dto1);
	    
		BoardDTO dto2 = new BoardDTO(); // 객체 생성
		dto2.setIndex(1); // 값 세팅
		dto2.setTitle("Sample Title 2");
		dto2.setText("Text 2");
		dto2.setUserId("user2");
		dto2.setCategory(1);
		dto2.setCreateDate(System.currentTimeMillis());
	    list.add(dto2);
	    
		BoardDTO dto3 = new BoardDTO(); // 객체 생성
		dto3.setIndex(1); // 값 세팅
		dto3.setTitle("Sample Title 3");
		dto3.setText("Text 3");
		dto3.setUserId("user3");
		dto3.setCategory(1);
		dto3.setCreateDate(System.currentTimeMillis());
	    list.add(dto3);
	    
	    
		BoardDTO dto4 = new BoardDTO(); // 객체 생성
		dto4.setIndex(1); // 값 세팅
		dto4.setTitle("Sample Title 4");
		dto4.setText("Text 4");
		dto4.setUserId("user4");
		dto4.setCategory(1);
		dto4.setCreateDate(System.currentTimeMillis());
	    list.add(dto4);
	    
	
		
		// #3. 2번에서 만든 데이터 반환
		
		return new ResponseEntity<List<BoardDTO>>(list, HttpStatus.OK);   
	}
	
}
