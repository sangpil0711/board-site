package kr.co.ymtech.bm.Service;

import java.util.List;

import kr.co.ymtech.bm.controller.dto.BoardGetDTO;


public interface IBoardService {

	public List<BoardGetDTO> findAll();
	
	Integer boardSave(BoardGetDTO board);
	
	Integer boardUpdate(BoardGetDTO board);
	
	Integer boardDelete(Integer index);

	public List<BoardGetDTO> indexSearch(Integer index);

	
	

}
