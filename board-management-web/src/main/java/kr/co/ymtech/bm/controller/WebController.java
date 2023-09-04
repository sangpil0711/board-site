package kr.co.ymtech.bm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ymtech.bm.controller.dto.BoardDTO;

@Controller
public class WebController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "main_display"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	@RequestMapping(value = "/main_display", method = RequestMethod.GET)
	public String mainpage() {
		return "main_display"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	@RequestMapping(value = "/general_board", method = RequestMethod.GET)
	public String boardpage() {
		return "general_board"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	@RequestMapping(value = "/general_write", method = RequestMethod.GET)
	public String writepage() {
		return "general_write"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	// "/board" 경로로 GET을 요청하면 BoardDTO 객체의 리스트를 담는 ResponseEntity를 반환하는 getBoard 메서드 정의
//	@RequestMapping(value = "/board", method = RequestMethod.GET)
	@GetMapping(value = "/board")
	public ResponseEntity<List<BoardDTO>> getBoard() {

		List<BoardDTO> list = new ArrayList<>(); // BoardDTO 객체의 리스트를 담기 위한 new ArrayList를 생성

		BoardDTO dto1 = new BoardDTO(); // dto1 객체 생성 및 값 설정 후 리스트 추가
		dto1.setIndex(1);
		dto1.setTitle("Sample Title 1");
		dto1.setText("Text 1");
		dto1.setUserId("user1");
		dto1.setCategory(1);
		dto1.setCreateDate(System.currentTimeMillis());
		list.add(dto1);

		BoardDTO dto2 = new BoardDTO(); // dto2 객체 생성 및 값 설정 후 리스트 추가
		dto2.setIndex(1);
		dto2.setTitle("Sample Title 2");
		dto2.setText("Text 2");
		dto2.setUserId("user2");
		dto2.setCategory(1);
		dto2.setCreateDate(System.currentTimeMillis());
		list.add(dto2);

		BoardDTO dto3 = new BoardDTO(); // dto3 객체 생성 및 값 설정 후 리스트 추가
		dto3.setIndex(1);
		dto3.setTitle("Sample Title 3");
		dto3.setText("Text 3");
		dto3.setUserId("user3");
		dto3.setCategory(1);
		dto3.setCreateDate(System.currentTimeMillis());
		list.add(dto3);

		BoardDTO dto4 = new BoardDTO(); // dto1 객체 생성 및 값 설정 후 리스트 추가
		dto4.setIndex(1);
		dto4.setTitle("Sample Title 4");
		dto4.setText("Text 4");
		dto4.setUserId("user4");
		dto4.setCategory(1);
		dto4.setCreateDate(System.currentTimeMillis());
		list.add(dto4);

		return new ResponseEntity<List<BoardDTO>>(list, HttpStatus.OK); // 리스트를 담은 ResponseEntity를 생성하여 반환
	}

}

