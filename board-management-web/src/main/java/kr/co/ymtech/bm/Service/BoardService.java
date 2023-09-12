package kr.co.ymtech.bm.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.repository.BoardVO;
import kr.co.ymtech.bm.repository.IBoardRepository;

/**
 * 
 */
@Service
public class BoardService implements IBoardService {
	
	private final IBoardRepository boardRepository;

	@Autowired
	private BoardService(IBoardRepository IboardRepository) {
		this.boardRepository = IboardRepository;
	}
	
	/**
	 * 
	 */
	@Override
	public List<BoardGetDTO> findAll() {
		
		List<BoardVO> list = boardRepository.findAll();
		
		List<BoardGetDTO> findAll = new ArrayList<>();   // vo -> dto
		for (BoardVO vo : list) {
			BoardGetDTO tmp = new BoardGetDTO();
			tmp.setIndex(vo.getIndex());
			tmp.setTitle(vo.getTitle());
			tmp.setText(vo.getText());
			tmp.setUserId(vo.getUserId());
			tmp.setCategory(vo.getCategory());
			tmp.setCreateDate(new Date(vo.getCreateDate()));
			findAll.add(tmp);
			}
			return findAll;
		}

	/**
	 * 
	 */
	@Override
	public Integer boardSave(BoardGetDTO board) {
		
			BoardVO vo = new BoardVO();            //dto -> vo
			vo.setIndex(board.getIndex());
			vo.setTitle(board.getTitle());
			vo.setText(board.getText());
			vo.setUserId(board.getUserId());
			vo.setCategory(board.getCategory());
			vo.setCreateDate(new Date().getTime());
		
		Integer save = boardRepository.boardSave(vo);
		
			return save;
		}	
	
	/**
	 * 
	 */
	@Override
	public Integer boardUpdate(BoardGetDTO board) {
		
			BoardVO vo = new BoardVO();           //dto -> vo
			vo.setIndex(board.getIndex());
			vo.setText(board.getText());
			
		Integer update = boardRepository.boardUpdate(vo);
			
			return update;
		}
		
	/**
	 * 
	 */
	@Override    
	public Integer boardDelete(Integer index) {
		
			return boardRepository.boardDelete(index);
		}
	
	/**
	 * 
	 */
	@Override
	public List<BoardGetDTO> indexSearch(Integer index) {
		List<BoardVO> list = boardRepository.indexSearch(index);
		
		List<BoardGetDTO> res = new ArrayList<>();       // vo -> dto
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
			return res;
		}

}
