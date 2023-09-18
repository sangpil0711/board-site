package kr.co.ymtech.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.service.BoardService;
import kr.co.ymtech.bm.service.IBoardService;

@Controller
public class HomeController {

	@Autowired
	private final IBoardService boardService;

	public HomeController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * Method : "/" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @return : "main_display.html"을 반환
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "main_display"; // "/" 경로로 GET을 요청하면 "index.html" 반환
	}

	/**
	 * Method : "/main" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @return : "main_display.html"을 반환
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainpage() {
		return "main_display";
	}

	/**
	 * Method : "/board" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @return : "general_board.html"을 반환
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String boardpage() {
		return "general_board";
	}

	/**
	 * Method : "/board/write" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @return : "general_write.html"을 반환
	 */
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String writepage() {
		return "general_write";
	}

	/**
	 * Method : "/board/update/{id}" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @param id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/board/update/{index}", method = RequestMethod.GET)
	public ModelAndView updatepage(@PathVariable Integer index) {

		ModelAndView model = new ModelAndView();

		model.addObject("index", index);
		
		model.setViewName("general_update");

		return model;
	}

	/**
	 * Method : "/board/write" 경로로 'POST' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @param boardGetDTO
	 * @param title
	 * @param text
	 * 
	 * @return
	 */
	@PostMapping(value = "/board/write")
	public String writeBoard(BoardDTO board) {

		boardService.saveBoard(board);

		return "redirect:/board";
	}

	@DeleteMapping("/board/delete/{index}")
	public ModelAndView removeBoard(@PathVariable Integer index) {
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("index", index);
		
		boardService.deleteBoard(index);

		return model;
	}

	/**
	 * Method : "/board/update/{id}" 경로로 'PATCH' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @param id
	 * @param newText
	 * 
	 * @return
	 */
	@PatchMapping(value = "/board/update/{index}")
	public String updateBoard(@PathVariable Integer index, @RequestParam("newText") String newText, @RequestBody BoardGetDTO board) {

		BoardDTO newBoard = getBoardDTOById(index);
		
		newBoard.setText(newText);

		System.out.println(newText);
		
		boardService.updateBoard(board);

		return "redirect:/board/{id}";
	}

	/**
	 * Method : "/board/{index}" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @param index : 게시물의 번호를 나타내는 index
	 * 
	 * @return : 화면에는 "general_read.html"을 반환하고 "index"에 index 값을 할당하여 반환
	 */
	@GetMapping(value = "/board/{index}")
	public ModelAndView readpage(@PathVariable Integer index) {

		ModelAndView model = new ModelAndView();

		model.setViewName("general_read");

		model.addObject("index", index);

		return model;
	}

	private BoardDTO getBoardDTOById(Integer index) {
		if (index == 1) {
			BoardDTO dto1 = new BoardDTO();
			dto1.setIndex(1);
			dto1.setTitle("Sample Title 1");
			dto1.setText("Text 1");
			dto1.setUserId("user1");
			dto1.setCategory(1);
			dto1.setCreateDate(System.currentTimeMillis());
			return dto1;
		}

		else if (index == 2) {
			BoardDTO dto2 = new BoardDTO();
			dto2.setIndex(2);
			dto2.setTitle("Sample Title 2");
			dto2.setText("Text 2");
			dto2.setUserId("user2");
			dto2.setCategory(1);
			dto2.setCreateDate(System.currentTimeMillis());
			return dto2;
		}

		else if (index == 3) {
			BoardDTO dto3 = new BoardDTO();
			dto3.setIndex(3);
			dto3.setTitle("Sample Title 3");
			dto3.setText("Text 3");
			dto3.setUserId("user3");
			dto3.setCategory(1);
			dto3.setCreateDate(System.currentTimeMillis());
			return dto3;
		}

		else if (index == 4) {
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
