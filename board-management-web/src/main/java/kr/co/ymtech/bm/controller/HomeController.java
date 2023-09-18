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
	public ModelAndView updateBoard(@PathVariable Integer index, @RequestBody BoardGetDTO board) {

		ModelAndView model = new ModelAndView();
		
		model.addObject("index", index);
		
		boardService.updateBoard(board);

		return model;
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

}
