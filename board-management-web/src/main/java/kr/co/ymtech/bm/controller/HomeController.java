package kr.co.ymtech.bm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ymtech.bm.controller.dto.BoardDTO;

@Controller
public class HomeController {
	
	private List<BoardDTO> boardList = new ArrayList<>();

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
	
	@DeleteMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Integer id) {
		
		deleteBoardById(id);
        
        return "redirect:/general_board";
    }
	
	private void deleteBoardById(Integer id) {
		
        boardList.removeIf(boardDTO -> boardDTO.getIndex().equals(id));
        
    }

	@RequestMapping(value = "/board/update/{id}", method = RequestMethod.GET)
	public ModelAndView updatepage(@PathVariable Integer id) {

		ModelAndView model = new ModelAndView();
		BoardDTO boardDTO = getBoardDTOById(id);

		model.addObject("dto", boardDTO);
		model.setViewName("general_update");

		return model;
	}
	
	@PatchMapping(value = "/board/update/{id}")
	public String updateBoard(@PathVariable Integer id, @RequestParam("newText") String newText) {

		BoardDTO newBoard = getBoardDTOById(id);
		newBoard.setText(newText);
		
		System.out.println(newText);

		return "redirect:/board/{id}";
	}

	@PostMapping(value = "/general_board")
	public String writeBoard(@RequestParam("title") String title, @RequestParam("text") String text) {

		BoardDTO newBoard = new BoardDTO();
		newBoard.setTitle(title);
		newBoard.setText(text);
		newBoard.setUserId("admin");
		newBoard.setCreateDate(System.currentTimeMillis());

		return "redirect:/general_board";
	}

	@GetMapping(value = "/board/{id}")
	public ModelAndView readpage(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView();
		BoardDTO boardDTO = getBoardDTOById(id);

		model.addObject("dto", boardDTO);
		model.setViewName("general_read");

		return model;
	}

	private BoardDTO getBoardDTOById(Integer id) {
		if (id == 1) {
			BoardDTO dto1 = new BoardDTO();
			dto1.setIndex(1);
			dto1.setTitle("Sample Title 1");
			dto1.setText("Text 1");
			dto1.setUserId("user1");
			dto1.setCategory(1);
			dto1.setCreateDate(System.currentTimeMillis());
			return dto1;
		}

		else if (id == 2) {
			BoardDTO dto2 = new BoardDTO();
			dto2.setIndex(2);
			dto2.setTitle("Sample Title 2");
			dto2.setText("Text 2");
			dto2.setUserId("user2");
			dto2.setCategory(1);
			dto2.setCreateDate(System.currentTimeMillis());
			return dto2;
		}

		else if (id == 3) {
			BoardDTO dto3 = new BoardDTO();
			dto3.setIndex(3);
			dto3.setTitle("Sample Title 3");
			dto3.setText("Text 3");
			dto3.setUserId("user3");
			dto3.setCategory(1);
			dto3.setCreateDate(System.currentTimeMillis());
			return dto3;
		}

		else if (id == 4) {
			BoardDTO dto4 = new BoardDTO();
			dto4.setIndex(4);
			dto4.setTitle("Sample Title 4");
			dto4.setText("Text 4");
			dto4.setUserId("user4");
			dto4.setCategory(1);
			dto4.setCreateDate(System.currentTimeMillis());
			return dto4;
		}

		return null;
	}
}
