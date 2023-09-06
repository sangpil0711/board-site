package kr.co.ymtech.bm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.co.ymtech.bm.Service.BoardService;
import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.repository.BoardVO;
import kr.co.ymtech.bm.repository.IBoardRepository;

@Controller
public class BoardController {
	
	@Autowired
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
 
//	@Autowired
//	private IBoardRepository boardRepository;
	
	
	@GetMapping(value = "/index")
	public String homepage() {
		return "index"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}
	
	@GetMapping(value = "/board") 
	public ResponseEntity<List<BoardGetDTO>> getBoard() {  // "/board" 경로로 GET을 요청하면 BoardDTO 객체의 리스트를 담는 ResponseEntity를 반환하는 getBoard 메서드 정의

		
		List<BoardVO> list = boardService.findAll();
		
		List<BoardGetDTO> res = new ArrayList<>();
		for (BoardVO vo : list) {
			BoardGetDTO tmp = new BoardGetDTO();
			tmp.setIndex(vo.getIndex());
			tmp.setTitle(vo.getTitle());
			tmp.setText(vo.getText());
			tmp.setUserId(vo.getUserId());
			tmp.setCategory(vo.getCategory());
			tmp.setCreateDate(new Date(vo.getCreateDate()));
			
			res.add(tmp);
		}
		
		return new ResponseEntity<List<BoardGetDTO>>(res, HttpStatus.OK); // 리스트를 담은 ResponseEntity를 생성하여 반환
	}
	
	@PostMapping(value ="/board")
	public ResponseEntity<Integer> boardsave(@RequestBody BoardDTO board) {
		
		// dto -> vo
		BoardVO vo = new BoardVO();
		
		Integer cnt = boardRepository.save(vo);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK); // 리스트를 담은 ResponseEntity를 생성하여 반환
	}
}
	
	
	
	
	
	
