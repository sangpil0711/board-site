package kr.co.ymtech.bm.repository;

import java.util.List;



public interface IBoardRepository {

	List<BoardVO> findAll();
	
	Integer boardSave(BoardVO board);
	
	Integer boardUpdate(BoardVO board);
	
	Integer boardDelete(Integer index);

	List<BoardVO> indexSearch(Integer index);


	



	


	

}
