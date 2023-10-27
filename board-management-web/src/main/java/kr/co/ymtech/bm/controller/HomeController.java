package kr.co.ymtech.bm.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.controller.dto.BoardDTO;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.service.BoardService;
import kr.co.ymtech.bm.service.FileService;
import kr.co.ymtech.bm.service.IBoardService;
import kr.co.ymtech.bm.service.IFileService;

@Controller
public class HomeController {

	/**
	 * Controller-Service 연결
	 * 
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@Autowired
	private final IBoardService boardService;
	private final IFileService fileService;

	public HomeController(BoardService boardService, FileService fileService) {
		this.boardService = boardService;
		this.fileService = fileService;
	}

	/**
	 * @Method homepage "/" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @return "main_display.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homepage() {

		ModelAndView model = new ModelAndView();

		model.setViewName("main");

		return model;
	}

	/**
	 * @Method mainpage "/main" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @return "main_display.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView mainpage() {

		ModelAndView model = new ModelAndView();

		model.setViewName("main_display");

		return model;
	}

	/**
	 * @Method boardpage "/board" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @return "general_board.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "board", method = RequestMethod.GET)
	public ModelAndView boardpage() {

		ModelAndView model = new ModelAndView();

		model.setViewName("general_board");

		return model;
	}

	/**
	 * 
	 * @Method writepage "/board/write" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @return "general_write.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "board/write", method = RequestMethod.GET)
	public ModelAndView writepage() {

		ModelAndView model = new ModelAndView();

		model.setViewName("general_write");

		return model;
	}

	/**
	 * @Method updatepage "/board/update/{index}" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @param index 수정할 게시글 번호
	 * 
	 * @return  model에 뷰를 "general_update.html"로 할당하여 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@RequestMapping(value = "board/update/{index}", method = RequestMethod.GET)
	public ModelAndView updatepage(@PathVariable Integer index) {

		ModelAndView model = new ModelAndView();

		model.setViewName("general_update");

		return model;
	}

	/**
	 * Method : "/board/write" 경로로 'POST' 요청이 들어오면 return 값을 반환하는 메소드
	 * 
	 * @param board : 작성할 게시글의 객체
	 * 
	 * @return : "/board" 경로로 요청하여 "general_board.html" 반환
	 * 
	 *         작성일 : 2023.09.18 작성자 : 황상필
	 */
	/**
	 * @Method writeBoard "/board/write" 경로로 'POST' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @param board 클라이언트가 저장하려고 하는 게시물 정보
	 * @param filePaths 업로드 된 파일 위치
	 * @param fileNames 업로드 된 파일 이름
	 * 
	 * @return "/board" 경로로 요청하여 "general_board.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@PostMapping(value = "board/write")
	public ModelAndView writeBoard(BoardDTO board, List<MultipartFile> files, List<String> fileNames) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/board");

		boardService.saveBoard(board);
		
		fileService.uploadFile(files, fileNames);

		return model;
	}

	/**
	 * @Method removeBoard "/board/delete/{index}" 경로로 'DELETE' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @param index 삭제할 게시글의 번호
	 * 
	 * @return "/board" 경로로 요청하여 "general_board.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@DeleteMapping("board/delete/{index}")
	public ModelAndView removeBoard(@PathVariable Integer index) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/board");
		
		boardService.deleteBoard(index);

		return model;
	}

	/**
	 * @Method updateBoard "/board/update/{index}" 경로로 'PATCH' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @param index 수정할 게시글 번호
	 * @param board 수정된 게시글 정보를 담은 객체
	 * 
	 * @return "/board/{index}" 경로로 요청하여 "general_read.html" 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@PatchMapping(value = "board/update/{index}")
	public ModelAndView updateBoard(@PathVariable Integer index, @RequestBody BoardGetDTO board) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/board/{index}");
		
		boardService.updateBoard(board);

		return model;
	}

	/**
	 * @Method readpage "/board/{index}" 경로로 'GET' 요청이 들어오면 return 값을 반환하는 메소드
	 *
	 * @param index 조회할 게시글의 번호
	 * 
	 * @return model에 뷰를 "general_read.html"로 할당하여 반환
	 *
	 * @author 황상필
	 * @since 2023. 09. 18.
	 */
	@GetMapping(value = "board/{index}")
	public ModelAndView readpage(@PathVariable Integer index) {

		ModelAndView model = new ModelAndView();

		model.setViewName("general_read");

		return model;
	}
}
